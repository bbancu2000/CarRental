package ro.jademy.carrental.models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Client extends User {
    public String gender;

    public int age;
    public int yearsOfDriving;

    public int nrOfAccidents;
    public int daysRented;
    public double rentalCoeff;

    LocalDate birthDate;
    LocalDate driversLicenseAcquiredDate;
    public long balance;

    public ArrayList<RentedCarHistoryItem> currentRentals = new ArrayList<>();
    public ArrayList<RentedCarHistoryItem> historyRentals = new ArrayList<>();


    public Client(String userName, String firstName, String lastName, String password, String gender, String birthDate, String driversLicenseAcquiredDate, int nrOfAccidents, int daysRented, long balance) {
        super(userName, firstName, lastName, password);
        this.gender = gender;

        this.nrOfAccidents = nrOfAccidents;
        this.daysRented = daysRented;
        this.balance = balance;
        this.birthDate = LocalDate.parse(birthDate);
        this.driversLicenseAcquiredDate = LocalDate.parse(driversLicenseAcquiredDate);
    }

    public void setCalculatedAge() {
        int result = LocalDate.now().getYear() - birthDate.getYear();
        age = result;
    }

    public void setCalculatedDrivingTime() {
        int result = LocalDate.now().getYear() - driversLicenseAcquiredDate.getYear();
        yearsOfDriving = result;
    }


    public void calculateRentalCoeff() {
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
        double finalCoeff = baseCoeff + ((coeff / 100) * baseCoeff);


        rentalCoeff = finalCoeff;
    }


}
