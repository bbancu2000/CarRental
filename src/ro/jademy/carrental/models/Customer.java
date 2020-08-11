package ro.jademy.carrental.models;

import java.util.ArrayList;

public class Customer extends User {

    public Customer(String userName, String firstName, String lastName, String password, String gender, int age, int nrOfAccidents, int yearsOfDriving, int daysRented) {
        super(userName, firstName, lastName, password);
        this.gender = gender;
        this.age = age;
        this.nrOfAccidents = nrOfAccidents;
        this.yearsOfDriving = yearsOfDriving;
        this.daysRented = daysRented;
    }

    public String gender;
    public int age;
    public int nrOfAccidents;
    public int yearsOfDriving;

    public int daysRented;


    ArrayList<RentedCarHistoryItem> currentRentals = new ArrayList<>();
    ArrayList<RentedCarHistoryItem> historyRentals = new ArrayList<>();


    public double getRentalCoeff() {
        double baseCoeff = 1;
        double coeff = 0;

        int ageLowerBreakGap = 25;  // before ageLowerBreakGap , no discount on age

        double ageRatio = 0.5;      // after ageLowerBreakGap, ageRatio discount% per year.
        double accidentRatio = 40; // 40% more rent for each accident.
        double yearsOfDrivingRatio = 1; // 1% discount per year driven.
        double daysRentedRatio = 0.1; // 1% discount per 10 days rented

        //TODO insert mega-complex rip-off RentalCoeff
        if (gender.equalsIgnoreCase("male")) {
            baseCoeff = baseCoeff * 1.2;
        } else if (gender.equalsIgnoreCase("female")) {
            baseCoeff = baseCoeff * 0.8;
        } else {
            System.out.println("no attack helicopters");
            return -1;
        }

        //percentage increase additive. (increment)
        //accident
        double accidentCoeff = nrOfAccidents * accidentRatio;

        //percentage decrease additive (discount)
        // age
        double ageCoeff = 1;
        if (age <= ageLowerBreakGap) {
            ageCoeff = 0;
        } else {
            ageCoeff = -age * ageRatio;
        }

        //percentage decrease additive (discount)
        //yearsOfDriving
        double yearsOfDrivingCoeff = -yearsOfDriving * yearsOfDrivingRatio;

        //percentage decrease additive (discount)
        //daysRented
        double daysRentedCoeff = -daysRented * daysRentedRatio;

        //complex algorithm

        coeff = accidentCoeff + ageCoeff + yearsOfDrivingCoeff + daysRentedCoeff;
        double finalCoeff = baseCoeff + ((coeff/100) * baseCoeff);


        return finalCoeff;
    }


}
