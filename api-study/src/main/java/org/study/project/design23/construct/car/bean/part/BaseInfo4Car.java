package org.study.project.design23.construct.car.bean.part;

/**
 * @author chenyao
 * @date 2019/8/26 16:25
 * @description
 */
public class BaseInfo4Car {

    private String color;
    private int price;
    private String brand;
    private String sn;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {


        this.brand = brand;
    }

    @Override
    public String toString() {
        return "BaseInfo4Car{" +
                "color='" + color + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", sn='" + sn + '\'' +
                '}';
    }
}
