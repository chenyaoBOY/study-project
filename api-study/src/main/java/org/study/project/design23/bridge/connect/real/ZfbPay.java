package org.study.project.design23.bridge.connect.real;

import java.math.BigDecimal;

/**
 * @author chenyao
 * @date 2020/11/29 15:57
 * @description
 */
public class ZfbPay extends Pay {
    public ZfbPay(int payMode) {
        super(payMode);
    }

    @Override
    public String transfer(String uId, String tradeId, BigDecimal amount) {
        logger.info("模拟⽀支付宝渠道⽀支付划账开始。uId：{} tradeId：{} amount： {}", uId, tradeId, amount);
        boolean security = payMode.security(uId);
        logger.info("模拟⽀支付宝渠道⽀支付⻛风控校验。uId：{} tradeId：{} security： {}", uId, tradeId, security);
        if (!security) {
            logger.info("模拟⽀支付宝渠道⽀支付划账拦截。uId：{} tradeId：{} amount：{}", uId, tradeId, amount);
            return "0001";
        }
        logger.info("模拟⽀支付宝渠道⽀支付划账成功。uId：{} tradeId：{} amount： {}", uId, tradeId, amount);
        return "0000";
    }
}
