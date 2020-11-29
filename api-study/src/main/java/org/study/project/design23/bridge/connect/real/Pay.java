package org.study.project.design23.bridge.connect.real;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.project.design23.bridge.connect.PayControllerBetter;

import java.math.BigDecimal;

/**
 * @author chenyao
 * @date 2020/11/29 15:55
 * @description
 */
public abstract class Pay {

    protected Logger logger = LoggerFactory.getLogger(Pay.class);
    protected IPayMode payMode;

    public Pay(int  modeType) {
        IPayMode iPayMode2;
        if(modeType == 1){
            iPayMode2 = new PayFaceMode();
        }else if(modeType == 2){
            iPayMode2 = new PayPassword();
        }else if(modeType == 3){
            iPayMode2 = new PayFingerprintMode();
        }else{
            throw new IllegalArgumentException();
        }
        this.payMode = iPayMode2;
    }
    public abstract String transfer(String uId, String tradeId, BigDecimal amount);
}
