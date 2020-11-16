package org.study.project.chonggou.movie;


import org.study.project.chonggou.Movie;
import org.study.project.chonggou.Price;

/**
 * @author chenyao
 * @date 2020/5/6 17:03
 * @description
 */
public class ChildrenMovie extends Price {

    @Override
    protected int getPriceCode() {
        return Movie.CHILDRENS;
    }

    @Override
    protected double getCharge(int daysRented) {
        double  result = 1.5;

        if (daysRented > 3) {
            result += (daysRented - 3) * 1.5;
        }
        return result;
    }

}
