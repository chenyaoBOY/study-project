package datasource.diff.compare.db.one;

import datasource.diff.compare.mapper.User;

/**
 * @author chenyao
 * @date 2020/1/2 15:40
 * @description
 */
public interface DB01Mapper {
    int updateUser();
    User selectData();

}
