package org.study.project.design23.construct.car.bulid;

import org.study.project.design23.construct.car.bean.part.BaseInfo4Car;
import org.study.project.design23.construct.car.bean.part.Person;

import java.util.UUID;

/**
 * @author chenyao
 * @date 2019/8/26 16:35
 * @description
 */
public class LKCarBulid extends AbstractBuilder {
    @Override
    public void setPerson() {
        Person p = new Person();
        p.setId(UUID.randomUUID().toString());
        p.setName("尹家富");
        car.setPerson(p);
    }

    @Override
    public void setBaseInfo() {
        BaseInfo4Car info = new BaseInfo4Car();
        info.setSn(UUID.randomUUID().toString());
        info.setBrand("林肯");
        info.setColor("黑色");
        info.setPrice(88888);
        car.setBaseInfo4Car(info);
    }
}
