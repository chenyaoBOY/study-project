package sharding.jdbc.spilt;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

/**
 * @author chenyao
 * @date 2019/8/16 10:39
 * @description
 */
public class DataSourceUtil {
    public static DataSource createDataSource(String dbName){
        DruidDataSource source = new DruidDataSource();
        source.setUrl("jdbc:mysql://localhost:3306/"+dbName+"?characterEncoding=utf-8");
        source.setDriverClassName("com.mysql.jdbc.Driver");
        source.setUsername("root");
        source.setPassword("root");
        return source;
    }
}
