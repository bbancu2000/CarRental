package ro.jademy.carrental.services;

import ro.jademy.carrental.models.RentedCarHistoryItem;
import ro.jademy.carrental.models.cars.Car;

import javax.print.PrintService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class StatsService {
    Scanner sc = new Scanner(System.in);


    public void checkIncomeMenu(List<RentedCarHistoryItem> rentedCarList, List<RentedCarHistoryItem> pendingTransactions, List<RentedCarHistoryItem> historyRentedCarList) {

        LocalDate startDate;
        LocalDate endDate;
        List<LocalDate> timeFrame;
        long result = 0;

        System.out.println(" -- Check Income Sub-Menu -- ");
        System.out.println("1. Show Income by Time-Frame");
        System.out.println("2. Show Income by Day");
        System.out.println();
        System.out.println("N. To return to Main Menu");

        boolean isValidInput = false;
        loop:
        do {
            String choice = sc.nextLine().toLowerCase();
            switch (choice) {
                case "1":
                    // Show Income by Time-Frame
                    System.out.println("");
                    System.out.println("Income by TIME-FRAME: ");
                    System.out.println("Choose your STARTING date [inclusive]");
                    startDate = Operations.getDateFromInput(false);
                    if (startDate == null) {
                        break loop;
                    }
                    System.out.println("Choose your END date [inclusive");
                    endDate = Operations.getDateFromInput(false);
                    if (endDate == null) {
                        break loop;
                    }
                    System.out.println();
                    System.out.println("Income by TIME-FRAME: ");
                    timeFrame = Operations.getDateListFromLocalDates(startDate, endDate);

                    for (LocalDate day : timeFrame) {
                        result = getAllIncomePerDay(rentedCarList, pendingTransactions, historyRentedCarList, day);
                        System.out.println(day.toString() + " : " + result);

                    }

                    isValidInput = true;
                    break;


                case "2":
                    // Show Income by Day
                    LocalDate selectDay = Operations.getDateFromInput(false);

                    if (selectDay != null) {
                        result = getAllIncomePerDay(rentedCarList, pendingTransactions, historyRentedCarList, selectDay);
                        System.out.println("");
                        System.out.println("Income by DAY: ");
                        System.out.println(selectDay.toString() + " : " + result);
                    }
                    isValidInput = true;
                    break;

                case "n":
                    // return to Main Menu
                    break loop;

                default:
                    System.out.println("Invalid input for CheckIncome-Menu");
                    break;

            }
        } while (!isValidInput);


    }

    public long getAllIncomePerDay(List<RentedCarHistoryItem> rentedCarList, List<RentedCarHistoryItem> pendingTransactions, List<RentedCarHistoryItem> historyRentedCarList, LocalDate date) {
        List<LocalDate> dateList = new ArrayList<>();
        LocalDate start;
        LocalDate end;
        long sum = 0;

        if (rentedCarList != null) {
            for (RentedCarHistoryItem item : rentedCarList) {
                start = item.pickupDate;
                end = item.returnDate;

                dateList = Operations.getDateListFromLocalDates(start, end);
                if (dateList.contains(date)) {
                    sum += item.rentPerDay;
                }
            }
            dateList.clear();
        }

        if (pendingTransactions != null) {
            for (RentedCarHistoryItem item : pendingTransactions) {
                start = item.pickupDate;
                end = item.returnDate;

                dateList = Operations.getDateListFromLocalDates(start, end);
                if (dateList.contains(date)) {
                    sum += item.rentPerDay;
                }
            }
        }

        if (historyRentedCarList != null) {
            for (RentedCarHistoryItem item : historyRentedCarList) {
                start = item.pickupDate;
                end = item.returnDate;

                dateList = Operations.getDateListFromLocalDates(start, end);
                if (dateList.contains(date)) {
                    sum += item.rentPerDay;
                }
            }
        }

        return sum;
    }


    public void StatisticsMenu(List<Car> allCarList,
                               List<RentedCarHistoryItem> pendingTransactions,
                               List<RentedCarHistoryItem> rentedCarList,
                               List<RentedCarHistoryItem> historyRentedCarList) {
        System.out.println(" -- Statistics Sub-Menu -- ");
        System.out.println("1. Car: All stats ");
        System.out.println("2. // ");
        System.out.println("3. // ");
        System.out.println("4. ///Most preferred car:");
        System.out.println();
        System.out.println("N. To return to Main Menu");

        boolean isValidInput = false;
        String choice = "";
        Car currentCar = null;
        int result = 0;
        long income = 0;

        loop:
        do {
            choice = sc.nextLine().toLowerCase();

            switch (choice) {
                case "1":
                    //TODO
                    //Car: STATS by
                    currentCar = Operations.getCarByIDorVIN(allCarList);
                    if (currentCar != null) {
                        System.out.println(currentCar);
                        result = howManyTimesCarWasRented(currentCar, pendingTransactions, rentedCarList, historyRentedCarList);
                        System.out.println("Car was rented: " + result + " times.");
                        result = howManyDaysCarWasRented(currentCar, pendingTransactions, rentedCarList, historyRentedCarList);
                        System.out.println("Car was rented for a total of: " + result + " days.");
                        income = howMuchIncomeCarMade(currentCar, pendingTransactions, rentedCarList, historyRentedCarList);
                        System.out.println("Car made a total of: " + income + " income.");
                    }
                    break loop;

                case "2":

                    break loop;

                case "3":

                    break loop;


                case "n":
                    break loop;

                default:
                    System.out.println("Invalid input for Statistics-Menu");
                    break;
            }

        } while (!isValidInput);


    }


    public int howManyTimesCarWasRented(Car car, List<RentedCarHistoryItem> pendingTransactions, List<RentedCarHistoryItem> rentedCarList, List<RentedCarHistoryItem> historyRentedCarList) {
        int counter = 0;
        if (car == null) {
            return -1;
        } else {
            for (RentedCarHistoryItem item : pendingTransactions) {
                if (car.id == item.car.id) {
                    counter++;
                }
            }

            for (RentedCarHistoryItem item : rentedCarList) {
                if (car.id == item.car.id) {
                    counter++;
                }
            }

            for (RentedCarHistoryItem item : historyRentedCarList) {
                if (car.id == item.car.id) {
                    counter++;
                }
            }
            return counter;
        }
    }

    public int howManyDaysCarWasRented(Car car, List<RentedCarHistoryItem> pendingTransactions, List<RentedCarHistoryItem> rentedCarList, List<RentedCarHistoryItem> historyRentedCarList) {
        List<LocalDate> dateList = new ArrayList<>();
        for (RentedCarHistoryItem item : pendingTransactions) {
            if (car.id == item.car.id) {
                dateList.addAll(Operations.getDateListFromLocalDates(item.pickupDate, item.returnDate));
            }
        }

        for (RentedCarHistoryItem item : rentedCarList) {
            if (car.id == item.car.id) {
                dateList.addAll(Operations.getDateListFromLocalDates(item.pickupDate, item.returnDate));
            }
        }

        for (RentedCarHistoryItem item : historyRentedCarList) {
            if (car.id == item.car.id) {
                dateList.addAll(Operations.getDateListFromLocalDates(item.pickupDate, item.returnDate));
            }
        }
        return dateList.size();
    }


    public long howMuchIncomeCarMade(Car car, List<RentedCarHistoryItem> pendingTransactions, List<RentedCarHistoryItem> rentedCarList, List<RentedCarHistoryItem> historyRentedCarList) {
        long totalIncomeMadeByCar = 0;
        for (RentedCarHistoryItem item : pendingTransactions) {
            if (car.id == item.car.id) {
                totalIncomeMadeByCar += item.totalRent;
            }
        }

        for (RentedCarHistoryItem item : rentedCarList) {
            if (car.id == item.car.id) {
                totalIncomeMadeByCar += item.totalRent;
            }
        }

        for (RentedCarHistoryItem item : historyRentedCarList) {
            if (car.id == item.car.id) {
                totalIncomeMadeByCar += item.totalRent;
            }
        }
        return totalIncomeMadeByCar;
    }


}
