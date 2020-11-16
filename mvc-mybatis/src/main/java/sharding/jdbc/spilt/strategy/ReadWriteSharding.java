package sharding.jdbc.spilt.strategy;

import org.apache.shardingsphere.api.config.encryptor.EncryptRuleConfiguration;
import org.apache.shardingsphere.api.config.encryptor.EncryptorRuleConfiguration;
import org.apache.shardingsphere.api.config.masterslave.MasterSlaveRuleConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.EncryptDataSourceFactory;
import org.apache.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import sharding.jdbc.spilt.DataSourceUtil;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

/**
 * @author chenyao
 * @date 2019/8/16 10:34
 * @description
 */

@Component
public class ReadWriteSharding {

    @Bean(name = "readWriteSplitDataSource")
    DataSource getMasterSlaveDataSource() throws SQLException {
        MasterSlaveRuleConfiguration masterSlaveRuleConfig =
                new MasterSlaveRuleConfiguration("ds_master_slave", "ds_master", Arrays.asList("ds_slave0"));

        return MasterSlaveDataSourceFactory.createDataSource(createDataSourceMap(), masterSlaveRuleConfig,  new Properties());
    }

    Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        result.put("ds_master", DataSourceUtil.createDataSource("write"));
        result.put("ds_slave0", DataSourceUtil.createDataSource("read"));
        return result;
    }

    DataSource getEncryptDataSource() throws SQLException {
        return EncryptDataSourceFactory.createDataSource(DataSourceUtil.createDataSource("demo_ds"), getOrderEncryptRuleConfiguration());
    }

    private static EncryptRuleConfiguration getOrderEncryptRuleConfiguration() {
        EncryptRuleConfiguration encryptRuleConfiguration = new EncryptRuleConfiguration();
        Properties properties = new Properties();
        properties.setProperty("aes.key.value", "123456");
        EncryptorRuleConfiguration encryptorRuleConfiguration = new EncryptorRuleConfiguration("AES", "t_order.order_id", properties);
        encryptRuleConfiguration.getEncryptorRuleConfigs().put("user_encryptor", encryptorRuleConfiguration);
        return encryptRuleConfiguration;
    }
}
