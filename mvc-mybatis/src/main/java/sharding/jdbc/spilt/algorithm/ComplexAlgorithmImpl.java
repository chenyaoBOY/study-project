package sharding.jdbc.spilt.algorithm;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.*;

/**
 * @author chenyao
 * @date 2019/8/19 10:46
 * @description
 */
public class ComplexAlgorithmImpl implements ComplexKeysShardingAlgorithm {
    @Override
    public Collection<String> doSharding(Collection collection, ComplexKeysShardingValue config) {
        LinkedHashSet<String> dbs = (LinkedHashSet<String>) collection;
        /**
         * ComplexKeysShardingValue
         *     private final String logicTableName;
         *     private final Map<String, Collection<T>> columnNameAndShardingValuesMap;
         */
        Map<String, LinkedList> map = config.getColumnNameAndShardingValuesMap();
        if (map == null || map.isEmpty()) {
            return collection;
        }

        LinkedHashSet result = new LinkedHashSet();
        for (Map.Entry<String, LinkedList> entry : map.entrySet()) {
            addDB("id", entry, collection.size(), dbs, result);
//            addDB("email", entry, collection.size(), dbs, result);

//            addDB("user_id", entry, collection.size(), dbs, result);
        }
        if(result.isEmpty()){
            return collection;
        }
        return result;
    }

    private void addDB(String column, Map.Entry<String, LinkedList> entry, int size, LinkedHashSet<String> dbs, LinkedHashSet<String> result) {
        /**
         * columnNameAndShardingValuesMap
         *      Map<String, Collection<T>>
         *             key:分片键值、
         *             collection：LinkedList 分片value
         */
        if (entry.getKey().equals(column)) {
            for (Object value : entry.getValue()) {
                int mod = value.hashCode() % size;
                if(mod == 0){
                    mod = 1;//后缀设计缺陷 下标应该从0开始 这里下标是从1开始 所以取模为0找不到数据库
                }

                for (String dbName : dbs) {
                    if (dbName.substring(dbName.length() - 1).equals(String.valueOf(mod))) {
                        result.add(dbName);
                        System.out.println("column="+column+" value="+value+" mod="+mod+" 入库="+dbName);
                        break;
                    }
                }
            }
        }
    }
}
