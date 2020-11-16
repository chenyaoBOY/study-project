package sharding.jdbc.spilt.dao;

import org.apache.ibatis.annotations.Param;
import sharding.jdbc.spilt.bean.OrderProductInfo;
import sharding.jdbc.spilt.bean.ShopOrder;
import sharding.jdbc.spilt.bean.ShopOrderExample;
import sharding.jdbc.spilt.bean.ShopOrderProduct;

import java.util.List;

public interface ShopOrderMapper {
    int countByExample(ShopOrderExample example);

    int deleteByExample(ShopOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopOrder record);

    int insertSelective(ShopOrder record);

    List<ShopOrder> selectByExample(ShopOrderExample example);

    ShopOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopOrder record, @Param("example") ShopOrderExample example);

    int updateByExample(@Param("record") ShopOrder record, @Param("example") ShopOrderExample example);

    int updateByPrimaryKeySelective(ShopOrder record);

    int updateByPrimaryKey(ShopOrder record);





    List<ShopOrder> getShopOrderList(List<Integer> ids);

    ShopOrder getOrder(@Param("id") Integer id, @Param("userId") Integer userId, @Param("email") String email);

    List<OrderProductInfo> getProductInfos(Integer id);

    OrderProductInfo getProductInfoCrossDb(Integer id);
}