package sharding.jdbc.spilt.strategy;

import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.ComplexShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.HintShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.NoneShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import sharding.jdbc.spilt.algorithm.ComplexAlgorithmImpl;
import sharding.jdbc.spilt.algorithm.HintAlgorithmImpl;
import sharding.jdbc.spilt.algorithm.RangeAlgorithmImpl;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

import static sharding.jdbc.spilt.DataSourceUtil.createDataSource;

/**
 * @author chenyao
 * @date 2019/8/15 17:40
 * @description
 */
@Component
public class ShardingJDBConfig  {

    @Bean(name = "shardingDataSource")
    DataSource getShardingDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().addAll(addTableRules());

//        shardingRuleConfig.getBindingTableGroups().add("t_order, t_order_item");
//        shardingRuleConfig.getBroadcastTables().add("t_config");

        //分库策略
        //按照表达式分库
//        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("id", "ds${id % 4}"));
        //使用标准实现策略分库
//        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("id", new DbStrategy()));
        //不对库进行分库  查询的时候遍历库
//        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new NoneShardingStrategyConfiguration());
//        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new ComplexShardingStrategyConfiguration("id",new ComplexAlgorithmImpl()));
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new ComplexShardingStrategyConfiguration("id,user_id,email",new ComplexAlgorithmImpl()));
//        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new HintShardingStrategyConfiguration(new HintAlgorithmImpl()));
        //PreciseShardingAlgorithm是必选的，用于处理=和IN的分片。RangeShardingAlgorithm是可选的，用于处理BETWEEN AND分片，
        // 如果不配置RangeShardingAlgorithm，SQL中的BETWEEN AND将按照全库路由处理。
//        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("id",new DbStrategy(),new RangeAlgorithmImpl()));
        //分表策略
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("id", new TableStrategy()));

        Properties props = new Properties();
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig,props);
    }


    List<TableRuleConfiguration> addTableRules(){
        List<TableRuleConfiguration> list = new ArrayList<>();
        list.add(getTableRule("shop_order","db0${1..4}.shop_order_${1..8}",true));
        list.add(getTableRule("shop_order_product","db0$->{1..4}.shop_order_product_$->{1..8}",false));
        return list;
    }
    TableRuleConfiguration getTableRule(String logicTableName,String rule,boolean autoKey) {
        TableRuleConfiguration result = new TableRuleConfiguration(logicTableName,rule);
        if(autoKey){
            result.setKeyGeneratorConfig(new KeyGeneratorConfiguration("SNOWFLAKE","id"));
        }
        return result;
    }

    Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>(4);
        result.put("db01", createDataSource("db01"));
        result.put("db02", createDataSource("db02"));
        result.put("db03", createDataSource("db03"));
        result.put("db04", createDataSource("db04"));
        return result;
    }



    class TableStrategy implements PreciseShardingAlgorithm {

        @Override
        public String doSharding(Collection collection, PreciseShardingValue preciseShardingValue) {
            LinkedHashSet<String> set = (LinkedHashSet) collection;
            int value = (int) preciseShardingValue.getValue() % collection.size();
            for (String tableName : set) {
                int i = tableName.lastIndexOf("_");
                String s = tableName.substring(i+1);
                if(value == Integer.valueOf(s)){
                    System.out.println("orderId="+preciseShardingValue.getValue()+" 取模="+value+" 落入表="+tableName);
                    return tableName;
                }
            }
            System.out.println(preciseShardingValue.getColumnName());
            return null;
        }
    }
    class DbStrategy implements  PreciseShardingAlgorithm{
        @Override
        public String doSharding(Collection collection, PreciseShardingValue config) {
            String columnName = config.getColumnName();
            int mod=0;
            if(columnName.equals("id")){
               mod = ((int)config.getValue()) % collection.size();
            }else if(columnName.equals("order_sn")){
                mod = config.getValue().hashCode() % collection.size();
            }
            for (String dbName : (LinkedHashSet<String>) collection) {
                String s = dbName.substring(3);
                if(mod == Integer.valueOf(s)){
                    System.out.println("orderId="+ config.getValue()+" 取模="+mod+" 落入库="+dbName);
                    return dbName;
                }
            }
            return null;
        }
    }
}
