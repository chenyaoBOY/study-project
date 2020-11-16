package org.study.project.spi;

/**
 * @author chenyao
 * @date 2020/8/20 14:43
 * @description
 */
public class GeneralUser implements DemoService {
    @Override
    public int getCountById(Integer id) {
        return 9900;
    }

    @Override
    public String sayHello() {
        return "I am general user";
    }
}
