package org.study.project.design23.bridge.connect.real;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenyao
 * @date 2020/11/29 16:01
 * @description
 */
public class PayFingerprintMode implements IPayMode{
    protected Logger logger = LoggerFactory.getLogger(PayFingerprintMode.class);
    public boolean security(String uId) {
        logger.info("指纹⽀支付，⻛风控校验指纹信息");
        return true;
    }
}
