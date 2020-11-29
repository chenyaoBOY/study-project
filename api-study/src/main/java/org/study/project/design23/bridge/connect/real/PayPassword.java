package org.study.project.design23.bridge.connect.real;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenyao
 * @date 2020/11/29 16:02
 * @description
 */
public class PayPassword implements IPayMode{
    protected Logger logger = LoggerFactory.getLogger(PayPassword.class);
    public boolean security(String uId) {
        logger.info("密码⽀支付，⻛风控校验环境安全");
        return true;
    }
}
