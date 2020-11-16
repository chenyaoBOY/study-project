package sharding.jdbc.spilt.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author chenyao
 * @date 2019/8/19 10:41
 * @description 精确分片
 */
public class PreciseAlgorithmImpl implements PreciseShardingAlgorithm {
    @Override
    public String doSharding(Collection collection, PreciseShardingValue preciseShardingValue) {
        /**
         * collection 配置的数据库列表 或 表列表
         *
         * PreciseShardingValue
         *     private final String logicTableName;
         *     private final String columnName;
         *     private final T value;
         */
        return null;
    }
}
