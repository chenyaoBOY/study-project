package org.study.project.spi;

/**
 * @author chenyao
 * @date 2020/8/20 14:44
 * @description
 */
public class VipUser implements DemoService {
    @Override
    public int getCountById(Integer id) {
        return 100;
    }

    @Override
    public String sayHello() {
        return "I am VIP";
    }
}
