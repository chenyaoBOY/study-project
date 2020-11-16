package org.study.project.design23.construct.car.bean;


import org.study.project.design23.construct.car.bean.part.BaseInfo4Car;
import org.study.project.design23.construct.car.bean.part.Person;

/**
 * @author chenyao
 * @date 2019/8/26 16:22
 * @description
 */
public class Car {
    private Person person;
    private BaseInfo4Car baseInfo4Car;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public BaseInfo4Car getBaseInfo4Car() {
        return baseInfo4Car;
    }

    public void setBaseInfo4Car(BaseInfo4Car baseInfo4Car) {

        this.baseInfo4Car = baseInfo4Car;
    }

    @Override
    public String toString() {
        return "Car{" +
                "person=" + person +
                ", baseInfo4Car=" + baseInfo4Car +
                '}';
    }
}
