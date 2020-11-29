package org.study.project.design23.bridge.connect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * @author chenyao
 * @date 2020/11/29 15:32
 * @description 多支付渠道和多支付方式 使用策略模式
 */
public class PayControllerBest {
    private Logger logger = LoggerFactory.getLogger(PayControllerBest.class);

    public boolean doPay(String uId, String tradeId, BigDecimal amount,
                         int channelType, int modeType) {

        PayChannelService payChannelService = getPayChannelService(channelType,modeType);
        payChannelService.doPayByChannel(uId,tradeId,amount,modeType);
        return true;
    }


    public static PayChannelService getPayChannelService(int channelType, int modeType){
        if(channelType == 1){
            return new WechatPay(modeType);
        }
        if(channelType == 2){
            return new AliPay(modeType);
        }
        throw new IllegalArgumentException();
    }

    static abstract class PayChannelService{
        protected PayMethodService payMethodService;
        public PayChannelService(int modeType){
            if(modeType == 1){
               this.payMethodService = new PayMethodByPassword();
            }else if(modeType == 2){
                this.payMethodService = new PayMethodByFaceId();
            }else if(modeType == 3){
                this.payMethodService = new PayMethodByHandId();
            }else{
                throw new IllegalArgumentException();
            }
        }
        abstract void doPayByChannel(String uId, String tradeId, BigDecimal amount, int modeType);
    }
    static class WechatPay extends PayChannelService{
        public WechatPay(int modeType) {
            super(modeType);
        }

        @Override
        public void doPayByChannel(String uId, String tradeId, BigDecimal amount, int modeType) {
            payMethodService.validateParam(uId,tradeId,amount);
        }
    }
    static class AliPay extends PayChannelService{
        public AliPay(int modeType) {
            super(modeType);
        }
        @Override
        public void doPayByChannel(String uId, String tradeId, BigDecimal amount, int modeType) {
            payMethodService.validateParam(uId,tradeId,amount);
        }
    }

    interface PayMethodService{
        void validateParam(String uId, String tradeId, BigDecimal amount);
    }

    static class PayMethodByPassword implements PayMethodService{
        @Override
        public void validateParam(String uId, String tradeId, BigDecimal amount) {

        }
    }
    static class PayMethodByFaceId implements PayMethodService{
        @Override
        public void validateParam(String uId, String tradeId, BigDecimal amount) {

        }
    }
    static class PayMethodByHandId implements PayMethodService{
        @Override
        public void validateParam(String uId, String tradeId, BigDecimal amount) {

        }
    }
}