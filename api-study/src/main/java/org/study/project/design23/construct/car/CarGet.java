package org.study.project.design23.construct.car;

import com.alibaba.fastjson.JSONObject;
import org.study.project.design23.construct.car.bulid.LBJNCarBulid;
import org.study.project.design23.construct.car.bulid.LKCarBulid;

/**
 * @author chenyao
 * @date 2019/8/26 16:33
 * @description 建造者模式
 *
 *      通过定义抽象父类 AbstractBuilder 定义Car的零部件set方法 是为抽象建造者
 *      这样不同类型的car通过实现AbstractBuilder 即可自定义Car的零部件设置 是为具体建造者
 *      Director 封装了Car的获取 隐藏内部构建的过程 还可以封装其他产品的获取 是为指挥者
 *
 */
public class CarGet {
    public static void main(String[] args) {
        Director director = new Director(new LBJNCarBulid());
        System.out.println(JSONObject.toJSONString(director.getCar()));
        Director director1 = new Director(new LKCarBulid());
        System.out.println(JSONObject.toJSONString(director1.getCar()));
    }
}
