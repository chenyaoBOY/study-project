package org.study.project.design23.construct.car.bulid;


import org.study.project.design23.construct.car.bean.part.BaseInfo4Car;
import org.study.project.design23.construct.car.bean.part.Person;

import java.util.UUID;

/**
 * @author chenyao
 * @date 2019/8/26 16:31
 * @description
 */
public class LBJNCarBulid extends AbstractBuilder {
    @Override
    public void setPerson() {
        Person p = new Person();
        p.setId(UUID.randomUUID().toString());
        p.setName("张迪");
        car.setPerson(p);
    }

    @Override
    public void setBaseInfo() {
        BaseInfo4Car info = new BaseInfo4Car();
        info.setSn(UUID.randomUUID().toString());
        info.setBrand("兰博基尼");
        info.setColor("红色");
        info.setPrice(99999);
        car.setBaseInfo4Car(info);
    }
}
