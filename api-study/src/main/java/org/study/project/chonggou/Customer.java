package org.study.project.chonggou;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author chenyao
 * @date 2020/5/6 16:00
 * @description
 */
public class Customer {
    private String name;
    private Vector<Rental> rentals = new Vector<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String statement() {
        String result = "Rental Record for " + getName() + "\n";
        Enumeration<Rental> elements = rentals.elements();
        while (elements.hasMoreElements()) {
            result += "\t" + elements.nextElement().getMovie().getTitle() + "\t" + elements.nextElement().getCharge() + "\n";
        }

        result += "Amount owed is " + getTotalCharge() + "\n";
        result += "You earned " + getTotalFrequents() + " frequent renter points";
        return result;
    }


    private double getTotalCharge(){
        double totalAmout = 0;

        Enumeration<Rental> elements = rentals.elements();
        while (elements.hasMoreElements()) {
            Rental each = elements.nextElement();
            totalAmout += each.getCharge();
        }
        return totalAmout;
    }

    private int getTotalFrequents(){
        int frequentRenterPoints = 0;
        Enumeration<Rental> elements = rentals.elements();
        while (elements.hasMoreElements()) {
            Rental each = elements.nextElement();
            frequentRenterPoints += each.getFrequentRenterPoints();
        }
        return frequentRenterPoints;
    }





    class RentalOrigin{
       /* public String statement() {
            double totalAmout = 0;
            int frequentRenterPoints = 0;
            String result = "Rental Record for " + getName() + "\n";

            Enumeration<Rental> elements = rentals.elements();
            while (elements.hasMoreElements()) {
                double thisAmount = 0;

                Rental each = elements.nextElement();
                switch (each.getMovie().getPriceCode()) {
                    case Movie.REGULAR://2天内2块 超出一天1.5元
                        thisAmount += 2;
                        if (each.getDaysRented() > 2) {
                            thisAmount += (each.getDaysRented() - 2) * 1.5;
                        }
                        break;
                    case Movie.NEW_RELEASE://一天3元
                        thisAmount += each.getDaysRented() * 3;
                        break;
                    case Movie.CHILDRENS://3天内1.5元 超出一天1.5元
                        thisAmount += 1.5;
                        if (each.getDaysRented() > 3) {
                            thisAmount += (each.getDaysRented() - 3) * 1.5;
                        }
                        break;
                }
                frequentRenterPoints++;
                if (each.getMovie().getPriceCode() == Movie.NEW_RELEASE && each.getDaysRented() > 1)
                    frequentRenterPoints++;
                result += "\t" + each.getMovie().getTitle() + "\t" + thisAmount + "\n";
                totalAmout += thisAmount;
            }

            result += "Amount owed is " + totalAmout + "\n";
            result += "You earned " + frequentRenterPoints + " frequent renter points";
            return result;
        }*/
    }

}
