package org.study.project.solr;

import java.io.Serializable;

/**
 * @author chenyao
 * @Description:
 * @date 2018/7/2/14:44
 */
public class SolrQueryResult implements Serializable{

    private String id;
    private String idProduct;
    private Integer idSupplier;

    private Integer idManufacturer;
    private Integer idCategoryDefault;
    private Integer idShopDefault;

    private String nameN;
    private String text;

    @Override
    public String toString() {
        return "SolrQueryResult{" +
                "id='" + id + '\'' +
                ", idProduct='" + idProduct + '\'' +
                ", idSupplier=" + idSupplier +
                ", idManufacturer=" + idManufacturer +
                ", idCategoryDefault=" + idCategoryDefault +
                ", idShopDefault=" + idShopDefault +
                ", nameN='" + nameN + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
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

    public String getNameN() {
        return nameN;
    }

    public void setNameN(String nameN) {
        this.nameN = nameN;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
