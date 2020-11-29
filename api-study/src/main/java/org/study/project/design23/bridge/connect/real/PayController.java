package org.study.project.design23.bridge.connect.real;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.project.design23.bridge.connect.PayControllerBetter;

import java.math.BigDecimal;

/**
 * @author chenyao
 * @date 2020/11/29 15:32
 * @description 多支付渠道和多支付方式 如果使用if  else的方式写代码如下
 */
public class PayController {
    private Logger logger = LoggerFactory.getLogger(PayController.class);

    public boolean doPay(String uId, String tradeId, BigDecimal amount,
                         int channelType, int modeType) {
        // 微信⽀支付
        Pay pay = getPayChannelService(channelType, modeType);
        pay.transfer(uId,tradeId,amount);
        return true;
    }
    public static Pay getPayChannelService(int channelType,int modeType){
        if(channelType == 1){
            return new WxPay(modeType);
        }
        if(channelType == 2){
            return new ZfbPay(modeType);
        }
        throw new IllegalArgumentException();
    }
}