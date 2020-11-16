package mybatis.api.learn;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

/**
 * @author chenyao
 * @date 2020/8/20 15:31
 * @description
 */
@Configuration
public class Demo {

    private static final String msg= msg();


    public static void main(String[] args) {

//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//
//        context.register(Demo.class);
//
//        context.refresh();
//
//
//        InitializeBeanTest bean = context.getBean(InitializeBeanTest.class);
//
        String s = null;
        BigDecimal bigDecimal = new BigDecimal(s);

    }

    @Bean
    public InitializeBeanTest initializeBeanTest(){
        return new InitializeBeanTest();
    }

    public static String msg(){
        return "msg";
    }
}
