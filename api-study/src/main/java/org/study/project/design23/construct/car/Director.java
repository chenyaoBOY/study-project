package org.study.project.design23.construct.car;

import org.study.project.design23.construct.car.bean.Car;
import org.study.project.design23.construct.car.bulid.AbstractBuilder;

/**
 * @author chenyao
 * @date 2019/8/26 16:32
 * @description
 */
public class Director {
    private AbstractBuilder bulider ;

    public Director(AbstractBuilder bulider) {
        this.bulider = bulider;
    }
    public Car getCar(){
        bulider.setBaseInfo();
        bulider.setPerson();
        return bulider.getCar();
    }
}
