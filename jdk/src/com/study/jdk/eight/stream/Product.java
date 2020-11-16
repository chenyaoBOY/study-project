package com.study.jdk.eight.stream;

/**
 * @author chenyao
 * @date 2019/7/19 14:42
 * @description
 */
public class Product{

    /**
     * 序列号
     */
    private static final long serialVersionUID = -4859456872988505494L;

    /**
     * 自增id
     */
    private Integer purchaseProductId;

    /**
     * 采购单id
     */
    private Integer purchaseId;

    /**
     * 商品id
     */
    private Integer productId;

    /**
     * 进价
     */
    private Integer inPrice;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品编号
     */
    private String productCode;

    /**
     * 国条码
     */
    private String barCode;

    /**
     * 规格
     */
    private String specification;

    /**
     * 单位
     */
    private String saleUnit;

    /**
     * 箱规
     */
    private String boxSpecification;

    /**
     * 备注
     */
    private String purchaseRemark;

    /**
     * 定量
     */
    private Double purchaseSum;

    /**
     * 到量
     */
    private Double arrivalSum;


    /*
     * 上面是表基本信息
     *
     *
     *
     *
     * 下面是其他地方用到的属性
     */
    private Integer warehouseId;

    /**
     * 供应商id
     */
    private Integer supplierId;
    /**
     * 预计订货数量
     */
    private double predictSum;

    /**
     * @return the supplierId
     */
    public Integer getSupplierId() {
        return supplierId;
    }

    /**
     * @param supplierId the supplierId to set
     */
    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * @return the warehouseId
     */
    public Integer getWarehouseId() {
        return warehouseId;
    }

    /**
     * @param warehouseId the warehouseId to set
     */
    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    /**
     * @return the purchaseProductId
     */
    public Integer getPurchaseProductId() {
        return purchaseProductId;
    }

    /**
     * @param purchaseProductId the purchaseProductId to set
     */
    public void setPurchaseProductId(Integer purchaseProductId) {
        this.purchaseProductId = purchaseProductId;
    }

    /**
     * @return the purchaseId
     */
    public Integer getPurchaseId() {
        return purchaseId;
    }

    /**
     * @param purchaseId the purchaseId to set
     */
    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    /**
     * @return the productId
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * @return the inPrice
     */
    public Integer getInPrice() {
        return inPrice;
    }

    /**
     * @param inPrice the inPrice to set
     */
    public void setInPrice(Integer inPrice) {
        this.inPrice = inPrice;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the productCode
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * @param productCode the productCode to set
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * @return the barCode
     */
    public String getBarCode() {
        return barCode;
    }

    /**
     * @param barCode the barCode to set
     */
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    /**
     * @return the specification
     */
    public String getSpecification() {
        return specification;
    }

    /**
     * @param specification the specification to set
     */
    public void setSpecification(String specification) {
        this.specification = specification;
    }

    /**
     * @return the saleUnit
     */
    public String getSaleUnit() {
        return saleUnit;
    }

    /**
     * @param saleUnit the saleUnit to set
     */
    public void setSaleUnit(String saleUnit) {
        this.saleUnit = saleUnit;
    }

    /**
     * @return the boxSpecification
     */
    public String getBoxSpecification() {
        return boxSpecification;
    }

    /**
     * @param boxSpecification the boxSpecification to set
     */
    public void setBoxSpecification(String boxSpecification) {
        this.boxSpecification = boxSpecification;
    }

    /**
     * @return the purchaseRemark
     */
    public String getPurchaseRemark() {
        return purchaseRemark;
    }

    /**
     * @param purchaseRemark the purchaseRemark to set
     */
    public void setPurchaseRemark(String purchaseRemark) {
        this.purchaseRemark = purchaseRemark;
    }

    /**
     * @return the purchaseSum
     */
    public Double getPurchaseSum() {
        return purchaseSum;
    }

    /**
     * @param purchaseSum the purchaseSum to set
     */
    public void setPurchaseSum(Double purchaseSum) {
        this.purchaseSum = purchaseSum;
    }

    /**
     * @return the arrivalSum
     */
    public Double getArrivalSum() {
        return arrivalSum;
    }

    /**
     * @param arrivalSum the arrivalSum to set
     */
    public void setArrivalSum(Double arrivalSum) {
        this.arrivalSum = arrivalSum;
    }

    public double getPredictSum() {
        return predictSum;
    }

    public void setPredictSum(double predictSum) {
        this.predictSum = predictSum;
    }

    @Override
    public String toString() {
        return "Product{" +
                "purchaseProductId=" + purchaseProductId +
                ", purchaseId=" + purchaseId +
                ", productId=" + productId +
                ", inPrice=" + inPrice +
                ", productName='" + productName + '\'' +
                ", productCode='" + productCode + '\'' +
                ", barCode='" + barCode + '\'' +
                ", specification='" + specification + '\'' +
                ", saleUnit='" + saleUnit + '\'' +
                ", boxSpecification='" + boxSpecification + '\'' +
                ", purchaseRemark='" + purchaseRemark + '\'' +
                ", purchaseSum=" + purchaseSum +
                ", arrivalSum=" + arrivalSum +
                ", warehouseId=" + warehouseId +
                ", supplierId=" + supplierId +
                ", predictSum=" + predictSum +
                '}';
    }
}
