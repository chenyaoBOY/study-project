package org.cyao.ssm.dao.mapper.dynamic;

import org.cyao.ssm.dao.bean.Feedback;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenyao
 * @Description:
 * @date 2018/6/23/14:31
 */
public interface DynamicFeebackMapper {

    List<Feedback> getList();
}
