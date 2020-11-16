package sharding.jdbc.spilt.dao;

import org.apache.ibatis.annotations.Param;
import sharding.jdbc.spilt.bean.ShopOrderProduct;
import sharding.jdbc.spilt.bean.ShopOrderProductExample;

import java.util.List;

public interface ShopOrderProductMapper {
    int countByExample(ShopOrderProductExample example);

    int deleteByExample(ShopOrderProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopOrderProduct record);

    int insertSelective(ShopOrderProduct record);

    List<ShopOrderProduct> selectByExample(ShopOrderProductExample example);

    ShopOrderProduct selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopOrderProduct record, @Param("example") ShopOrderProductExample example);

    int updateByExample(@Param("record") ShopOrderProduct record, @Param("example") ShopOrderProductExample example);

    int updateByPrimaryKeySelective(ShopOrderProduct record);

    int updateByPrimaryKey(ShopOrderProduct record);
}