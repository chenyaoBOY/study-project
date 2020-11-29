package org.study.project.design23.bridge.connect.real;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenyao
 * @date 2020/11/29 16:01
 * @description
 */
public class PayFaceMode implements IPayMode{
    protected Logger logger = LoggerFactory.getLogger(PayFaceMode.class);
    public boolean security(String uId) {
        logger.info("⼈人脸⽀支付，⻛风控校验脸部识别");
        return true;
    }
}
