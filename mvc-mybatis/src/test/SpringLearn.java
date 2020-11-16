import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.validate.extend.MyBean;

/**
 * @author chenyao
 * @date 2019/8/8 16:54
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext-demo.xml"})
public class SpringLearn {
    @Autowired
    private MyBean myBean;
    @Test
    public void test(){
        /**
         * BeanNameAware#name=myBean
         * beanFactory#getnBean=Tue Aug 06 00:00:00 CST 2019
         * applicationContext#getBean=Tue Aug 06 00:00:00 CST 2019
         *
         * postProcessBeforeInitialization#beanName=org.springframework.context.event.internalEventListenerProcessor
         * postProcessAfterInitialization#beanName=org.springframework.context.event.internalEventListenerProcessor
         *
         * postProcessBeforeInitialization#beanName=org.springframework.context.event.internalEventListenerFactory
         * postProcessAfterInitialization#beanName=org.springframework.context.event.internalEventListenerFactory
         *
         * postProcessBeforeInitialization#beanName=SpringLearn
         * postProcessAfterInitialization#beanName=SpringLearn
         *
         * myBean destroy~！！！！
         */
    }
}
