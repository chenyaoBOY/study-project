package org.cyao.ssm.dao.bean;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author chenyao
 * @Description:
 * @date 2018/6/23/23:08
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    /**
     * 对应xml文件中数据源的id
     */
    public static final String DATA_SOURCE1="db1";
    public static final String DATA_SOURCE2="db2";
    public static final String DATA_SOURCE3="db3";
    public static final String DEFAULT_DATA_SOURCE="db1";

    private static final ThreadLocal<String> DATA_SOURCE = new ThreadLocal<String>();
    @Override
    protected Object determineCurrentLookupKey() {

        return  DATA_SOURCE.get();

    }
    public static void setDbType(String dbType) {

        DATA_SOURCE.set(dbType);
    }

    public static void clearDbType() {

        DATA_SOURCE.remove();
    }
}
