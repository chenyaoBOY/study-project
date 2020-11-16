package org.study.project.spi;

import java.util.ServiceLoader;

/**
 * @author chenyao
 * @date 2020/8/20 14:54
 * @description
 */
public class SpiApp {
    /**
     * 在resources目录下 新建 META-INF/services/org.study.project.spi.DemoService 文件
     * 文件内声明 该接口的具体实现类 可以有多个
     * @param args
     */
    public static void main(String[] args) {

        ServiceLoader<DemoService> services = ServiceLoader.load(DemoService.class);

        for (DemoService service : services) {
            System.out.println(service.sayHello());
        }

    }
}
