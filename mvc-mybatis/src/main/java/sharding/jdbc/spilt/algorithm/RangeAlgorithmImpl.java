package sharding.jdbc.spilt.algorithm;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;

/**
 * @author chenyao
 * @date 2019/8/19 10:44
 * @description
 */
public class RangeAlgorithmImpl implements RangeShardingAlgorithm {
    @Override
    public Collection<String> doSharding(Collection collection, RangeShardingValue rangeShardingValue) {
        /**
         * RangeShardingValue
         *     private final String logicTableName;
         *     private final String columnName;
         *     private final Range<T> valueRange;
         */

        return null;
    }
}
