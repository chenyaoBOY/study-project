package org.cyao.ssm.dao.mapper.data;

import org.cyao.ssm.dao.bean.Feedback;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenyao
 * @Description:
 * @date 2018/6/23/14:31
 */
public interface DataFeebackMapper {

    List<Feedback> getList();

    void insert();

    void update();
}
