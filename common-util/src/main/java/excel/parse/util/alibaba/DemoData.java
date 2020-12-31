//package excel.parse.util.alibaba;
//
//import com.alibaba.excel.annotation.format.NumberFormat;
//
//import java.math.BigDecimal;
//import java.util.Date;
//
///**
// * @author chenyao
// * @date 2020/7/13 11:15
// * @description
// */
//public class DemoData {
//    /**订单号*/
//    private String merchantOrderNo;
//    /**提交渠道单号*/
//    private String commitChannelNo;
//    /**渠道单号*/
//    private String channelNo;
//    /**实际支付币种（非下单币种）/实际退款币种（非下单币种）*/
//    private String currencyCode;
//    /**实际支付金额（非下单币种金额）/实际退款金额（非下单币种退款金额）*/
//    @NumberFormat("#.##")
//    private String amount;
//    /**状态*/
//    private Integer channelBillStatus;
//
//    public String getMerchantOrderNo() {
//        return merchantOrderNo;
//    }
//
//    public void setMerchantOrderNo(String merchantOrderNo) {
//        this.merchantOrderNo = merchantOrderNo;
//    }
//
//    public String getCommitChannelNo() {
//        return commitChannelNo;
//    }
//
//    public void setCommitChannelNo(String commitChannelNo) {
//        this.commitChannelNo = commitChannelNo;
//    }
//
//    public String getChannelNo() {
//        return channelNo;
//    }
//
//    public void setChannelNo(String channelNo) {
//        this.channelNo = channelNo;
//    }
//
//    public String getCurrencyCode() {
//        return currencyCode;
//    }
//
//    public void setCurrencyCode(String currencyCode) {
//        this.currencyCode = currencyCode;
//    }
//
//    public String getAmount() {
//        return amount;
//    }
//
//    public void setAmount(String amount) {
//        this.amount = amount;
//    }
//
//    public Integer getChannelBillStatus() {
//        return channelBillStatus;
//    }
//
//    public void setChannelBillStatus(Integer channelBillStatus) {
//        this.channelBillStatus = channelBillStatus;
//    }
//}
