package org.study.project.design23.construct.car.bulid;

import org.study.project.design23.construct.car.bean.Car;

/**
 * @author chenyao
 * @date 2019/8/26 16:29
 * @description
 */
public abstract class AbstractBuilder {

    protected Car car = new Car();

    public abstract void setPerson();
    public abstract void setBaseInfo();

    public Car getCar(){
        return car;
    }

}
