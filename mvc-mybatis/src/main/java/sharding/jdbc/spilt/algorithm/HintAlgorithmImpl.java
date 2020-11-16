package sharding.jdbc.spilt.algorithm;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.Collection;

/**
 * @author chenyao
 * @date 2019/8/19 10:46
 * @description
 */
public class HintAlgorithmImpl implements HintShardingAlgorithm {
    @Override
    public Collection<String> doSharding(Collection collection, HintShardingValue hintShardingValue) {
        /**
         * HintShardingValue
         *     private final String logicTableName;
         *     private final String columnName;
         *     private final Collection<T> values;
         */
        return null;
    }
}
