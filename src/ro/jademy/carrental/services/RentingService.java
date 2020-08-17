package ro.jademy.carrental.services;

import ro.jademy.carrental.models.Customer;
import ro.jademy.carrental.models.RentedCarHistoryItem;
import ro.jademy.carrental.models.Salesman;
import ro.jademy.carrental.models.User;
import ro.jademy.carrental.models.cars.Car;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.DAYS;

public class RentingService {
    Scanner sc = new Scanner(System.in);


    public void rentSubMenu(Car car, User user, int maximumRentalDaysTotal, int maximumRentalDaysInAdvance, List<RentedCarHistoryItem> rentedCarList, List<RentedCarHistoryItem> pendingTransaction) {

        loop:
        while (car != null) {
            System.out.println("Would you like to rent this car ? [ TYPE Y/N ]");
            String choiceyn = sc.nextLine();

            if (choiceyn.equalsIgnoreCase("n")) {
                break loop;
            }

            // getting a list of days available from start (from now , to +7 days)
            List<LocalDate> availableStartDays = Operations.getDateListFromLocalDates(LocalDate.now(), LocalDate.now().plusDays(maximumRentalDaysInAdvance));
            List<LocalDate> daysAlreadyRented = new ArrayList<>();
            List<LocalDate> daysAlreadyBooked = new ArrayList<>();

            //if car is rented already
            if (car.isRented) {
                for (RentedCarHistoryItem rentedItem : rentedCarList) {
                    if (rentedItem.car.id == car.id) {
                        daysAlreadyRented.addAll(Operations.getDateListFromLocalDates(rentedItem.pickupDate, rentedItem.returnDate));
                    }
                }
            }

            //if car has a pending transaction already
            for (RentedCarHistoryItem bookedItem : pendingTransaction) {
                if (bookedItem.car.id == car.id) {
                    daysAlreadyBooked.addAll(Operations.getDateListFromLocalDates(bookedItem.pickupDate, bookedItem.returnDate));
                }
            }

            availableStartDays.removeAll(daysAlreadyRented);
            availableStartDays.removeAll(daysAlreadyBooked);

            // printing available start days
            boolean isValidInput = false;
            boolean isAvailableDate = false;
            String choiceDay = "";
            String choiceMonth = "";
            String choiceYear = "";
            LocalDate userStartDate = null;
            int maxAvailableDaysToRent = -1;

            //TODO print available days to start
//            System.out.println("( Maximum of : " + maximumRentalDaysInAdvance + " days to Rent-In-Advance )");
//            System.out.println("( Maximum of : " + maximumRentalDaysTotal + " days to rent a specific car )");
            System.out.println("Available Dates to rent: ");
            for (LocalDate date1 : availableStartDays) {
                System.out.println(date1.toString());
            }

            // INPUT THE STARTING DAY OF THE RENT

            if (availableStartDays.isEmpty()) {
                System.out.println("You can't rent this car atm, no days available in the Rent-In-Advance Frame");
                car = null;
            } else {
                do {
                    userStartDate = Operations.getDateFromInput(true);

                    if (userStartDate == null) {
                        break loop;
                    }


                    // get maximum days to rent from that date.
                    for (LocalDate availableDate : availableStartDays) {
                        if (userStartDate.isEqual(availableDate)) {
                            maxAvailableDaysToRent = getMaximumPossibleRentDays(userStartDate, maximumRentalDaysTotal, car, pendingTransaction, rentedCarList);
                            isAvailableDate = true;
                            break;
                        }
                    }
                } while (!isAvailableDate);


                // INPUT RENT DAYS
                boolean validNrDays = false;
                String choiceNrDaysToRentStr = "";
                int choiceNrDaysToRentInt = -1;
                do {
                    System.out.println("You can rent this car for a maximum of: " + maxAvailableDaysToRent + " days");
                    choiceNrDaysToRentStr = sc.nextLine();

                    if (choiceNrDaysToRentStr.equalsIgnoreCase("n")) {
                        //TODO breaks
                    } else {
                        try {
                            choiceNrDaysToRentInt = Integer.parseInt(choiceNrDaysToRentStr);
                            if (choiceNrDaysToRentInt > 0 && choiceNrDaysToRentInt <= maxAvailableDaysToRent) {
                                validNrDays = true;
                            }
                        } catch (Exception E) {
                            validNrDays = false;
                        }
                    }
                } while (!validNrDays);

                long finalPrice = (long) (((Customer) user).rentalCoeff * car.basePrice);
                createTransaction(car, user, userStartDate, choiceNrDaysToRentInt, finalPrice, pendingTransaction);
                car = null;
                break loop;
            }
        }
    }


    public int getMaximumPossibleRentDays(LocalDate startDate, int maximumRentalDaysTotal, Car car, List<RentedCarHistoryItem> pendingTransaction, List<RentedCarHistoryItem> rentedCarList) {
        List<LocalDate> notAvailableDays = new ArrayList<>();
        int count = maximumRentalDaysTotal;

        for (RentedCarHistoryItem item : pendingTransaction) {
            if (item.car.id == car.id) {
                notAvailableDays.addAll(Operations.getDateListFromLocalDates(item.pickupDate, item.returnDate));
            }
        }
        for (RentedCarHistoryItem item : rentedCarList) {
            if (item.car.id == car.id) {
                notAvailableDays.addAll(Operations.getDateListFromLocalDates(item.pickupDate, item.returnDate));
            }
        }

        for (LocalDate date : notAvailableDays) {
            if (DAYS.between(startDate, date) >= 0 && DAYS.between(startDate, date) < count) {
                count = (int) DAYS.between(startDate, date);
            }
        }
        return count;
    }


    public void createTransaction(Car car, User user, LocalDate userStartDate, int nrOfDays, long rentPerDay, List<RentedCarHistoryItem> pendingTransaction) {
        System.out.println("Are you sure you want to make this transaction ?  [ Type Y/N ]");

        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("y")) {
            RentedCarHistoryItem rentedCar = new RentedCarHistoryItem(user, car, userStartDate, nrOfDays, rentPerDay);
            pendingTransaction.add(rentedCar);
            System.out.println("Created transaction, ");

        } else if (choice.equalsIgnoreCase("n")) {
            // TODO do-while for Y/N proofing
            //empty code block
        }
    }


    public void confirmPendingRentals(User currentUser, List<RentedCarHistoryItem> rentedCarList, List<RentedCarHistoryItem> pendingTransaction) {
        RentedCarHistoryItem selectedCarItem = null;
        boolean isValidInput = false;
        int choiceInt = -1;

        //TODO maybe make this PRINT look better?
        for (int i = 0; i < pendingTransaction.size(); i++) {
            System.out.println(i + ". " + pendingTransaction.get(i));
        }

        if(pendingTransaction.isEmpty()) {
            System.out.println();
            System.out.println("No current PENDING RENTALS !! ");
        } else {
            do {
                System.out.println();
                System.out.println("Select the rental you want to confirm: [ Type N to return to Main Menu ]");
                String choice = sc.nextLine();
                if (choice.equalsIgnoreCase("n")) {
                    // empty code block , return back
                    break;
                } else {
                    try {
                        choiceInt = Integer.parseInt(choice);
                        if (choiceInt >= 0 && choiceInt < pendingTransaction.size()) {
                            selectedCarItem = pendingTransaction.get(choiceInt);
                            isValidInput = true;

                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input for confirmPendingRentals");
                    }

                }
            } while (!isValidInput);

            // TODO maybe a Y/N confirmation ?


            if (selectedCarItem != null) {
                // adding salesman username to the transaction
                selectedCarItem.salesmanName = currentUser.userName;
                // modify car to be rented
                selectedCarItem.car.isRented = true;
                // adding CarItem to the active rentedCarList
                rentedCarList.add(selectedCarItem);
                // adding Caritem to the current rentals of the user;
                ((Customer) selectedCarItem.user).currentRentals.add(selectedCarItem);
                // removing CarItem from pendingTransactions
                pendingTransaction.remove(selectedCarItem);

                System.out.println("Transaction confirmed , car was given to the customer.");
            }
        }




    }


    public void returnRental(List<RentedCarHistoryItem> rentedCarList, List<RentedCarHistoryItem> pendingTransaction, List<RentedCarHistoryItem> historyRentedCarList ) {
        // TODO maybe rented AND pending ?
        User targetUser = null;
        int choiceInt = -1;
        RentedCarHistoryItem selectedCarItem = null;
        boolean isValidInt = false;

        if(rentedCarList.isEmpty()) {
            System.out.println("Return Rental service has an EMPTY rentedCarList");
        } else {
            // printing with INDEX
            for (int i = 0; i < rentedCarList.size() ; i++) {
                System.out.print(i+ ". " + rentedCarList.get(i));
                System.out.println();
            }

            loop:
            do {
                System.out.println("Select the rental that was returned: [ Type N to return to Main Menu ]");
                String choice = sc.nextLine();

                if (choice.equalsIgnoreCase("n")) {
                    // empty code block , return back
                    break loop;
                } else {
                    try {
                        choiceInt = Integer.parseInt(choice);
                        if (choiceInt >= 0 && choiceInt < rentedCarList.size()) {
                            selectedCarItem = rentedCarList.get(choiceInt);
                            targetUser = rentedCarList.get(choiceInt).user;
                            isValidInt = true;
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid index for return-Rental selection");
                    }
                }
            } while (!isValidInt);


            if (targetUser != null && selectedCarItem != null) {

                // setting the car to isRented = false;
                selectedCarItem.car.isRented = false;

                // adding the car to USER's  - HISTORY RENTALS
                ((Customer) targetUser).historyRentals.add(selectedCarItem);

                // adding the car to history RENTALS
                // TODO CHECK if work
                historyRentedCarList.add(selectedCarItem);

                // removing the car from USER's - CURRENT RENTALS
                ((Customer) targetUser).currentRentals.remove(selectedCarItem);
                // removing the car from the RENTED-CAR-LIST from SHOP.
                rentedCarList.remove(selectedCarItem);


                System.out.println();
                System.out.println("Car succesfully returned to the shop !!");
            }
        }




    }


}
