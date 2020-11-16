package datasource.diff.compare.mapper;

import datasource.diff.compare.mapper.User;

/**
 * @author chenyao
 * @date 2019/5/7 eight:10
 * @description
 */
public interface UserMapper {
    User selectData();
    int updateUser();

    int insert(User user);
}
