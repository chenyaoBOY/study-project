package mybatis.api.learn;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author chenyao
 * @date 2020/8/20 15:29
 * @description 验证通过new的方式 实现InitializingBean接口 是否会被调用
 */
public class InitializeBeanTest implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet方法被调用了");
    }
}
