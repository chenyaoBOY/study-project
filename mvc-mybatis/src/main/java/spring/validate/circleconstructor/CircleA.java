package spring.validate.circleconstructor;

/**
 * @author chenyao
 * @date 2019/6/19 10:50
 * @description
 */
public class CircleA {

    private CircleB circleB;



    public CircleA(CircleB circleB) {
        this.circleB = circleB;
    }
}
