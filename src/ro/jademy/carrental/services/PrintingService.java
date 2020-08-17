package ro.jademy.carrental.services;

import ro.jademy.carrental.models.Customer;
import ro.jademy.carrental.models.RentedCarHistoryItem;
import ro.jademy.carrental.models.Salesman;
import ro.jademy.carrental.models.User;
import ro.jademy.carrental.models.cars.Car;

import java.util.List;

public class PrintingService {

    List<Car> allCarList;
    List<RentedCarHistoryItem> rentedCarList;
    String middleBar = "  |  ";

    int maxPaddingByIndex;
    int maxPaddingByBasePrice;
    int maxPaddingByMake;
    int maxPaddingByModel;
    int maxPaddingByVariant;
    int maxPaddingByCarType;
    int maxPaddingByYear;
    int maxPaddingByEngine;
    int maxPaddingByFuelType;
    int maxPaddingByTransmission;
    int maxPaddingByDoorNumber;
    int maxPaddingByColor;
    int maxPaddingIsRented;

    public PrintingService(List<Car> allCarList) {
        this.allCarList = allCarList;
        this.maxPaddingByIndex = getMaxPaddingByIndex();
        this.maxPaddingByBasePrice = getMaxPaddingByBasePrice();
        this.maxPaddingByMake = getMaxPaddingByMake();
        this.maxPaddingByModel = getMaxPaddingByModel();
        this.maxPaddingByVariant = getMaxPaddingByVariant();
        this.maxPaddingByCarType = getMaxPaddingByCarType();
        this.maxPaddingByYear = getMaxPaddingByYear();
        this.maxPaddingByEngine = getMaxPaddingByEngine();
        this.maxPaddingByFuelType = getMaxPaddingByFuelType();
        this.maxPaddingByTransmission = getMaxPaddingByTransmission();
        this.maxPaddingByDoorNumber = getMaxPaddingByDoorNumber();
        this.maxPaddingByColor = getMaxPaddingByColor();
        this.maxPaddingIsRented = getMaxPaddingIsRented();

    }

    public int getMaxPaddingByIndex() {
        //TODO tostring.size maybe?
        int counter = 5;
//        if(allCarList.size() < 10) {
//            counter = 1;
//        } else if (allCarList.size() < 100) {
//            counter = 2;
//        } else if (allCarList.size() < 1000) {
//            counter = 3;
//        }
        return counter;
    }

    public int getMaxPaddingByBasePrice() {
        //TODO
        int counter = 5;
        return counter;
    }

    public int getMaxPaddingByMake() {
        int counter = 4;
        for (Car car : allCarList) {
            if (car.make.length() > counter) {
                counter = car.make.length();
            }
        }

        return counter;
    }

    public int getMaxPaddingByModel() {
        int counter = 5;
        for (Car car : allCarList) {
            if (car.model.length() > counter) {
                counter = car.model.length();
            }
        }
        return counter;
    }

    public int getMaxPaddingByVariant() {
        int counter = 7;
        for (Car car : allCarList) {
            if (car.variant.length() > counter) {
                counter = car.variant.length();
            }
        }
        return counter;
    }

    public int getMaxPaddingByCarType() {
        int counter = 8;
        for (Car car : allCarList) {
            if (car.carType.length() > counter) {
                counter = car.carType.length();
            }
        }
        return counter;
    }

    public int getMaxPaddingByYear() {
        int counter = 4;
        return counter;
    }

    public int getMaxPaddingByEngine() {
        int counter = 6;

        return counter;
    }

    public int getMaxPaddingByFuelType() {
        int counter = 9;
        for (Car car : allCarList) {
            if (car.fuelType.length() > counter) {
                counter = car.fuelType.length();
            }
        }
        return counter;
    }

    public int getMaxPaddingByTransmission() {
        int counter = 12;
        for (Car car : allCarList) {
            if (car.transmissionType.length() > counter) {
                counter = car.transmissionType.length();
            }
        }
        return counter;
    }

    public int getMaxPaddingByDoorNumber() {
        int counter = 9;

        return counter;
    }

    public int getMaxPaddingByColor() {
        int counter = 5;
        for (Car car : allCarList) {
            if (car.color.length() > counter) {
                counter = car.color.length();
            }
        }

        return counter;
    }

    public int getMaxPaddingIsRented() {
        int counter = 15;
        // yes, yyyy-mm-dd
        return counter;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////


    public void header() {
        System.out.printf("| %" + maxPaddingByIndex + "s" + middleBar, "Index");
        headerSingleCar();

    }

    public void headerSingleCar() {

        System.out.printf("%" + maxPaddingByBasePrice + "s" + middleBar, "PRICE");
        System.out.printf("%" + maxPaddingByMake + "s" + middleBar, "MAKE");
        System.out.printf("%" + maxPaddingByModel + "s" + middleBar, "MODEL");
        System.out.printf("%" + maxPaddingByVariant + "s" + middleBar, "VARIANT");
        System.out.printf("%" + maxPaddingByCarType + "s" + middleBar, "CAR TYPE");
        System.out.printf("%" + maxPaddingByYear + "s" + middleBar, "YEAR");
        System.out.printf("%" + maxPaddingByEngine + "s" + middleBar, "ENGINE");
        System.out.printf("%" + maxPaddingByFuelType + "s" + middleBar, "FUEL-TYPE");
        System.out.printf("%" + maxPaddingByTransmission + "s" + middleBar, "TRANSMISSION");
        System.out.printf("%" + maxPaddingByDoorNumber + "s" + middleBar, "DOOR NR.");
        System.out.printf("%" + maxPaddingByColor + "s" + middleBar, "COLOR");

        //TODO eventually say when it's ready to rent
        System.out.printf("%" + maxPaddingIsRented + "s" + middleBar, "IsRented?");
        System.out.println();
        System.out.println();

    }

    public void printSingleCar(Car car , User user) {
        System.out.println();
        headerSingleCar();
        printCar(car , user);
        System.out.println();
    }



    public void printCar(Car car, User user) {
        if(car != null & user != null) {
            if (user instanceof Customer) {
                long finalPrice = (long) (car.basePrice * ((Customer) user).rentalCoeff);
                System.out.printf("%" + maxPaddingByBasePrice + "d" + middleBar, finalPrice);
            } else {
                if (user instanceof Salesman) {
                    System.out.printf("%" + maxPaddingByBasePrice + "d" + middleBar, car.basePrice);
                }
            }
            System.out.printf("%" + maxPaddingByMake + "s" + middleBar, car.make);
            System.out.printf("%" + maxPaddingByModel + "s" + middleBar, car.model);
            System.out.printf("%" + maxPaddingByVariant + "s" + middleBar, car.variant);
            System.out.printf("%" + maxPaddingByCarType + "s" + middleBar, car.carType);
            System.out.printf("%" + maxPaddingByYear + "d" + middleBar, car.year);
            System.out.printf("%" + maxPaddingByEngine + "d" + middleBar, car.engine);
            System.out.printf("%" + maxPaddingByFuelType + "s" + middleBar, car.fuelType);
            System.out.printf("%" + maxPaddingByTransmission + "s" + middleBar, car.transmissionType);
            System.out.printf("%" + maxPaddingByDoorNumber + "d" + middleBar, car.doorNumber);
            System.out.printf("%" + maxPaddingByColor + "s" + middleBar, car.color);
            //TODO eventually say when it's ready to rent
            System.out.printf("%" + maxPaddingIsRented + "s" + middleBar, car.isRented);
            System.out.println();
        }

    }

    public void printCarList(List<Car> carList, User user) {
//        System.out.println("this is a header from printcarList");
       // TODO INSERT HEADER METHOD
        header();


        for (int i = 0; i < carList.size(); i++) {
            System.out.printf("| %" + maxPaddingByIndex + "d" + middleBar, i);
            printCar(carList.get(i), user);
        }

        System.out.println();
    }


}
