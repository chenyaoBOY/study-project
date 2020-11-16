package spring.validate.extend;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import spring.validate.property.analyze.DateDIException;

/**
 * @author chenyao
 * @date 2019/8/8 16:43
 * @description
 */
public class MyBean implements BeanNameAware, BeanFactoryAware,
        ApplicationContextAware, BeanPostProcessor, DisposableBean {

    private BeanFactory beanFactory;
    private ApplicationContext applicationContext;
    private DateDIException dateDIException;
    private String beanId;

    @Override
    public void setBeanName(String name) {
        System.out.println("BeanNameAware#name=" + name);
        this.beanId = name;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        DateDIException bean = (DateDIException) beanFactory.getBean("dateDIException");
        System.out.println("beanFactory#getnBean="+bean.getDate());
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        DateDIException bean  = (DateDIException) applicationContext.getBean("dateDIException");
        System.out.println("applicationContext#getBean="+bean.getDate());
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("postProcessBeforeInitialization#beanName="+beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("postProcessAfterInitialization#beanName="+beanName);
        return bean;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println(beanId+" destroy~！！！！");
    }
}
