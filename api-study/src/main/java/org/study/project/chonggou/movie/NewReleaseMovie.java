package org.study.project.chonggou.movie;

import org.study.project.chonggou.Movie;
import org.study.project.chonggou.Price;

/**
 * @author chenyao
 * @date 2020/5/6 17:07
 * @description
 */
public class NewReleaseMovie extends Price {
    @Override
    protected int getPriceCode() {
        return Movie.NEW_RELEASE;
    }

    @Override
    protected double getCharge(int daysRented) {
        return daysRented * 3;
    }

    @Override
    protected int getFrequentRenterPoints(int daysRented) {
        return daysRented > 1 ? 2 : 1;
    }
}
