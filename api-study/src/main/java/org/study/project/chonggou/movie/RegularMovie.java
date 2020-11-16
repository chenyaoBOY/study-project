package org.study.project.chonggou.movie;

import org.study.project.chonggou.Movie;
import org.study.project.chonggou.Price;

/**
 * @author chenyao
 * @date 2020/5/6 17:06
 * @description
 */
public class RegularMovie extends Price {
    @Override
    protected int getPriceCode() {
        return Movie.REGULAR;
    }

    @Override
    protected double getCharge(int daysRented) {
        double result = 2;
        if (daysRented > 2) {
            result += (daysRented - 2) * 1.5;
        }
        return result;
    }

}
