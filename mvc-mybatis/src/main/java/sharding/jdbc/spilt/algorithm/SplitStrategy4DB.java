package sharding.jdbc.spilt.algorithm;




import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author chenyao
 * @date 2019/8/15 11:24
 * @description
 */
public class SplitStrategy4DB implements PreciseShardingAlgorithm {
    @Override
    public String doSharding(Collection collection, PreciseShardingValue preciseShardingValue) {
        String columnName = preciseShardingValue.getColumnName();
        String logicTableName = preciseShardingValue.getLogicTableName();
        Comparable value = preciseShardingValue.getValue();
        return null;
    }
}
