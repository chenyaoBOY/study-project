package org.study.project.chonggou;

/**
 * @author chenyao
 * @date 2020/5/6 17:01
 * @description
 */
public abstract class Price {
    protected abstract int getPriceCode();
    protected abstract double getCharge(int daysRented);

    protected int getFrequentRenterPoints(int daysRented){
        return 1;
    }
}
