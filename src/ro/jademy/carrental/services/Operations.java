package ro.jademy.carrental.services;

import ro.jademy.carrental.models.RentedCarHistoryItem;
import ro.jademy.carrental.models.cars.Car;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.DAYS;

public class Operations {

    private static Scanner sc = new Scanner(System.in);


    public static LocalDate getReturnDate(Car car, List<RentedCarHistoryItem> rentedCarList) {
        for (int i = 0; i < rentedCarList.size(); i++) {
            if (rentedCarList.get(i).car.id == car.id) {
                System.out.println(rentedCarList.get(i).returnDate + "\n");
                return rentedCarList.get(i).returnDate;
            }
        }
        return null;
    }


    public static Car getCarByIDorVIN(List<Car> allCarList) {
        boolean isValid = false;
        int inputInt = -1;
        String input;
        Car currentCar = null;

        do {
            System.out.println("Please enter a valid Car-ID, or Car-VIN  [ Type N to return to Main Menu ]");
            input = sc.nextLine();

            if (input.equalsIgnoreCase("n")) {
                break;
            } else {
                try {
                    inputInt = Integer.parseInt(input);
                    if (inputInt >= 0) {
                        isValid = true;
                    }
                } catch (Exception e) {

                }

            }

            if (inputInt > -1) {
                for (Car car : allCarList) {
                    if (car.id == inputInt) {
                        return car;
                    }
                }
            }

            if (inputInt == -1) {
                for (Car car : allCarList) {
                    if (car.VIN.equalsIgnoreCase(input)) {
                        return car;
                    }
                }
            }

            if(currentCar == null) {
                isValid = false;
                System.out.println("Error: Couldn't find car for the specified ID or VIN");
            }

        } while (!isValid);



        return null;
    }


    public static LocalDate getDateFromInput(boolean onlyFutureDates) {
        boolean isValidInput = false;
        String localDateInputStr;
        LocalDate selectedDate = null;
        do {
            System.out.println("Please enter a date:  [ Format: yyyy-MM-dd ]     or [ Type N to return to Main Menu ]");
            localDateInputStr = sc.nextLine();

            if (localDateInputStr.equalsIgnoreCase("n")) {
                return null;
            } else {
                try {
                    selectedDate = LocalDate.parse(localDateInputStr);
                    System.out.println("You chose starting date: " + selectedDate.toString());

                    // filter for PRESENT OR FUTURE DATES ( not past)
                    if (onlyFutureDates) {
                        if (selectedDate.isBefore(LocalDate.now())) {
                            isValidInput = false;
                        } else {
                            isValidInput = true;
                        }
                    }


                    isValidInput = true;
                } catch (Exception e) {
                    isValidInput = false;
                    System.out.println("Incorrect LocalDate format");
                }
            }
        } while (!isValidInput);

        return selectedDate;
    }

    public static List<LocalDate> getDateListFromLocalDates(LocalDate start, LocalDate end) {
        List<LocalDate> dateList = new ArrayList<>();

        int daysBetween = (int) DAYS.between(start, end);
        for (int i = 0; i <= daysBetween; i++) {
            dateList.add(start.plusDays(i));
        }
//        while (!start.isAfter(end)) {
//            dateList.add(start);
//            start = start.plusDays(1);
//        }
        return dateList;
    }


    public static int getValidNumberInput(int min, int max) {
        boolean isValidMax = false;
        int choiceInt;
        do {
            choiceInt = getValidNumberInput(min);
            if (choiceInt <= max) {
                isValidMax = true;
            } else {
                System.out.println("Your selected number is too big");
            }
        } while (!isValidMax);

        return choiceInt;
    }

    public static int getValidNumberInput(int min) {
        boolean isValid = false;
        boolean isValidMin = false;
        int choiceInt = -1;
        do {
            do {
                String choice = sc.nextLine();
                try {
                    choiceInt = Integer.parseInt(choice);
                    if (choiceInt != -1) {
                        isValid = true;
                    }
                } catch (Exception e) {
                    isValid = false;
                }
            } while (!isValid);
            if (choiceInt >= min) {
                isValidMin = true;
            }
        } while (!isValidMin);
        return choiceInt;
    }
}
