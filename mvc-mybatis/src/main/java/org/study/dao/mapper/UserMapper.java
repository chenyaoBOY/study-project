package org.study.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.study.bean.UserBean;

import java.util.List;

/**
 * @author chenyao
 * @date 2019/7/11 14:12
 * @description
 */
public interface UserMapper {

    int updateUserById(UserBean bean);

    List<UserBean> getUserList();

    UserBean getUserBean(Integer id);

    /**
     *  使用${} 如果是单个参数 必须加@Param注解 否则获取不到参数 报参数没有getter方法 $没有占位符
     * @param id
     * @return
     */
    UserBean getUserBean2(@Param("id") Integer id);
    UserBean getUserBean3(String preFixTableName);
    UserBean getUserBean4(@Param("table") String preFixTableName);

    List<String> getOrderSnByUserId(Integer userId);
    List<String> getOrderSn(Integer userId);
    int update4Concurrent(UserBean userBean);

    int insert(UserBean bean);
}
