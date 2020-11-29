package org.study.project.design23.bridge.connect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * @author chenyao
 * @date 2020/11/29 15:32
 * @description 多支付渠道和多支付方式 使用策略模式
 */
public class PayControllerBetter {
    private Logger logger = LoggerFactory.getLogger(PayControllerBetter.class);

    public boolean doPay(String uId, String tradeId, BigDecimal amount,
                         int channelType, int modeType) {

        PayChannelService payChannelService = getPayChannelService(channelType);
        payChannelService.doPayByChannel(uId,tradeId,amount,modeType);
        return true;
    }


    public static PayChannelService getPayChannelService(int channelType){
        if(channelType == 1){
            return new WechatPay();
        }
        if(channelType == 2){
            return new AliPay();
        }
        throw new IllegalArgumentException();
    }
    public static PayMethodService getPayMethodService(int modeType){
        if(modeType == 1){
            return new PayMethodByPassword();
        }
        if(modeType == 2){
            return new PayMethodByFaceId();
        }
        if(modeType == 3){
            return new PayMethodByHandId();
        }
        throw new IllegalArgumentException();
    }

    interface PayChannelService{
        void doPayByChannel(String uId, String tradeId, BigDecimal amount, int modeType);
    }
    static class WechatPay implements PayChannelService{
        @Override
        public void doPayByChannel(String uId, String tradeId, BigDecimal amount, int modeType) {
            PayMethodService payMethodService = getPayMethodService(modeType);
            payMethodService.doPayByChannel(uId,tradeId,amount);
        }
    }
    static class AliPay implements PayChannelService{
        @Override
        public void doPayByChannel(String uId, String tradeId, BigDecimal amount, int modeType) {
            PayMethodService payMethodService = getPayMethodService(modeType);
            payMethodService.doPayByChannel(uId,tradeId,amount);
        }
    }

    interface PayMethodService{
        void doPayByChannel(String uId, String tradeId, BigDecimal amount);
    }

    static class PayMethodByPassword implements PayMethodService{
        @Override
        public void doPayByChannel(String uId, String tradeId, BigDecimal amount) {

        }
    }
    static class PayMethodByFaceId implements PayMethodService{
        @Override
        public void doPayByChannel(String uId, String tradeId, BigDecimal amount) {

        }
    }
    static class PayMethodByHandId implements PayMethodService{
        @Override
        public void doPayByChannel(String uId, String tradeId, BigDecimal amount) {

        }
    }
}