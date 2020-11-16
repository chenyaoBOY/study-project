package org.study.project.chonggou;

import org.study.project.chonggou.movie.ChildrenMovie;
import org.study.project.chonggou.movie.NewReleaseMovie;
import org.study.project.chonggou.movie.RegularMovie;

/**
 * @author chenyao
 * @date 2020/5/6 15:57
 * @description
 */
public class Movie {

    public static final int CHILDRENS = 2;
    public static final int NEW_RELEASE = 1;
    public static final int REGULAR = 0;

    private String title;
    private Price price;

    public Movie(String title, Integer priceCode) {
        if (title == null || priceCode == null) throw new IllegalArgumentException("");
        this.title = title;
        setPriceCode(priceCode);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPriceCode() {
        return price.getPriceCode();
    }

    public void setPriceCode(Integer priceCode) {
        switch (priceCode) {
            case Movie.CHILDRENS:
                price = new ChildrenMovie();
                break;
            case Movie.REGULAR:
                price = new RegularMovie();
                break;
            case Movie.NEW_RELEASE:
                price = new NewReleaseMovie();
                break;
            default:
                throw new IllegalArgumentException("error movie priceCode:" + priceCode);
        }
    }

    public double getCharge(int daysRented) {
        return price.getCharge(daysRented);
    }

    public int getFrequentRenterPoints(int daysRented) {
        return price.getFrequentRenterPoints(daysRented);
    }

}
