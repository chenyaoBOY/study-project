package org.cyao.ssm.dao.mapper.ssm;

import org.cyao.ssm.dao.bean.Order;

public interface OrderMapper {

    int insertOrder(Order order);
    int deleteOrder(int id);
}
