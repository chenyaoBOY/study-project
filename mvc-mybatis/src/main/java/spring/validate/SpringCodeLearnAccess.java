package spring.validate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author chenyao
 * @date 2019/6/13 17:20
 * @description
 */
public class SpringCodeLearnAccess {

    private String name = "Demo";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("classpath*:applicationContext-demo.xml");
//        XmlBeanFactory app = new XmlBeanFactory(new ClassPathResource("applicationContext-demo.xml"));

        SpringCodeLearnAccess bean = (SpringCodeLearnAccess) app.getBean("demo");

        System.out.println(bean.getName());
    }
}


