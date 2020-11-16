package org.cyao.ssm.dao.mapper.ssm;

import org.cyao.ssm.dao.bean.Feedback;
import org.cyao.ssm.dao.bean.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenyao
 * @Description:
 * @date 2018/6/23/14:31
 */
public interface SsmFeebackMapper {

    List<Feedback> getList();

    int insertPerson(Person person);
}
