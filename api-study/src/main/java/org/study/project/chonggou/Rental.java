package org.study.project.chonggou;

/**
 * @author chenyao
 * @date 2020/5/6 15:59
 * @description
 */
public class Rental {

    private Movie movie;
    private Integer daysRented;

    public Rental(Movie movie, Integer daysRented) {
        if (movie == null || daysRented == null) throw new IllegalArgumentException("");
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Integer getDaysRented() {
        return daysRented;
    }

    public void setDaysRented(Integer daysRented) {
        this.daysRented = daysRented;
    }

    public int getFrequentRenterPoints() {
        return movie.getFrequentRenterPoints(daysRented);
    }


    public double getCharge() {
        return movie.getCharge(daysRented);
    }
}
