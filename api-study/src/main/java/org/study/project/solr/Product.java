package org.study.project.solr;

import org.apache.solr.client.solrj.beans.Field;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author chenyao
 * @Description:
 * @date 2018/6/22/16:30
 */
public class Product {

    @Field
    private String id;
    @Field
    private Integer idProduct;// 产品ID
    @Field
    private Integer idSupplier;// 供应商ID
    @Field
    private Integer idManufacturer;// 制造商ID
    @Field
    private Integer idCategoryDefault;// 默认分类ID
    @Field
    private Integer idShopDefault;// 默认商店ID@Field
    @Field
    private Integer onSale;// 是否在售
    @Field
    private Integer onlineOnly;// 只线上销售
    @Field
    private String upc;// UPC
    @Field
    private Integer quantity;// 数量
    @Field
    private String price;// 价格
    @Field
    private String location;//
    @Field
    private BigDecimal width;//
    @Field
    private BigDecimal height;//
    @Field
    private BigDecimal depth;//
    @Field
    private BigDecimal weight;//
    @Field
    private String condition;//
    @Field
    private String descriptionN;// 描述
    @Field
    private String descriptionShortN;// 短描述
    @Field
    private String linkRewriteN;// 链接
    @Field
    private String metaDescriptionN;// 元数据描述
    @Field
    private String metaKeywordsN;// 元数据关键字
    @Field
    private String metaTitleN;// 元数据标题
    @Field
    private String nameN;// 名称
    @Field
    private String availableNowN;// 立刻生效
    @Field
    private String availableLaterN;// 稍后生效
    @Field
    private String tag;
    @Field
    private String shopId;
    @Field
    private String productImage;
    private Integer isShareable;// 是否可以共享
    @Field
    private String spu;
    @Field
    private List<String> orgDeptIds;
    @Field
    private String frameProtocolCode;

    public List<String> getOrgDeptIds() {
        return orgDeptIds;
    }

    public void setOrgDeptIds(List<String> orgDeptIds) {
        this.orgDeptIds = orgDeptIds;
    }

    public String getFrameProtocolCode() {
        return frameProtocolCode;
    }

    public void setFrameProtocolCode(String frameProtocolCode) {
        this.frameProtocolCode = frameProtocolCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(Integer idSupplier) {
        this.idSupplier = idSupplier;
    }

    public Integer getIdManufacturer() {
        return idManufacturer;
    }

    public void setIdManufacturer(Integer idManufacturer) {
        this.idManufacturer = idManufacturer;
    }

    public Integer getIdCategoryDefault() {
        return idCategoryDefault;
    }

    public void setIdCategoryDefault(Integer idCategoryDefault) {
        this.idCategoryDefault = idCategoryDefault;
    }

    public Integer getIdShopDefault() {
        return idShopDefault;
    }

    public void setIdShopDefault(Integer idShopDefault) {
        this.idShopDefault = idShopDefault;
    }

    public Integer getOnSale() {
        return onSale;
    }

    public void setOnSale(Integer onSale) {
        this.onSale = onSale;
    }

    public Integer getOnlineOnly() {
        return onlineOnly;
    }

    public void setOnlineOnly(Integer onlineOnly) {
        this.onlineOnly = onlineOnly;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getDepth() {
        return depth;
    }

    public void setDepth(BigDecimal depth) {
        this.depth = depth;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDescriptionN() {
        return descriptionN;
    }

    public void setDescriptionN(String descriptionN) {
        this.descriptionN = descriptionN;
    }

    public String getDescriptionShortN() {
        return descriptionShortN;
    }

    public void setDescriptionShortN(String descriptionShortN) {
        this.descriptionShortN = descriptionShortN;
    }

    public String getLinkRewriteN() {
        return linkRewriteN;
    }

    public void setLinkRewriteN(String linkRewriteN) {
        this.linkRewriteN = linkRewriteN;
    }

    public String getMetaDescriptionN() {
        return metaDescriptionN;
    }

    public void setMetaDescriptionN(String metaDescriptionN) {
        this.metaDescriptionN = metaDescriptionN;
    }

    public String getMetaKeywordsN() {
        return metaKeywordsN;
    }

    public void setMetaKeywordsN(String metaKeywordsN) {
        this.metaKeywordsN = metaKeywordsN;
    }

    public String getMetaTitleN() {
        return metaTitleN;
    }

    public void setMetaTitleN(String metaTitleN) {
        this.metaTitleN = metaTitleN;
    }

    public String getNameN() {
        return nameN;
    }

    public void setNameN(String nameN) {
        this.nameN = nameN;
    }

    public String getAvailableNowN() {
        return availableNowN;
    }

    public void setAvailableNowN(String availableNowN) {
        this.availableNowN = availableNowN;
    }

    public String getAvailableLaterN() {
        return availableLaterN;
    }

    public void setAvailableLaterN(String availableLaterN) {
        this.availableLaterN = availableLaterN;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Integer getIsShareable() {
        return isShareable;
    }

    public void setIsShareable(Integer isShareable) {
        this.isShareable = isShareable;
    }

    public String getSpu() {
        return spu;
    }

    public void setSpu(String spu) {
        this.spu = spu;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", idProduct=" + idProduct +
                ", idSupplier=" + idSupplier +
                ", idManufacturer=" + idManufacturer +
                ", idCategoryDefault=" + idCategoryDefault +
                ", idShopDefault=" + idShopDefault +
                ", onSale=" + onSale +
                ", onlineOnly=" + onlineOnly +
                ", upc='" + upc + '\'' +
                ", quantity=" + quantity +
                ", price='" + price + '\'' +
                ", location='" + location + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", depth=" + depth +
                ", weight=" + weight +
                ", condition='" + condition + '\'' +
                ", descriptionN='" + descriptionN + '\'' +
                ", descriptionShortN='" + descriptionShortN + '\'' +
                ", linkRewriteN='" + linkRewriteN + '\'' +
                ", metaDescriptionN='" + metaDescriptionN + '\'' +
                ", metaKeywordsN='" + metaKeywordsN + '\'' +
                ", metaTitleN='" + metaTitleN + '\'' +
                ", nameN='" + nameN + '\'' +
                ", availableNowN='" + availableNowN + '\'' +
                ", availableLaterN='" + availableLaterN + '\'' +
                ", tag='" + tag + '\'' +
                ", shopId='" + shopId + '\'' +
                ", productImage='" + productImage + '\'' +
                ", isShareable=" + isShareable +
                ", spu='" + spu + '\'' +
                '}';
    }
}
