package ro.jademy.carrental.services;

import ro.jademy.carrental.models.Customer;
import ro.jademy.carrental.models.Salesman;
import ro.jademy.carrental.models.User;
import ro.jademy.carrental.models.cars.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilterService {
    Scanner sc = new Scanner(System.in);




    public List<Car> searchByPrice(User user, List<Car> carList, int minPrice, int maxPrice) {
        List<Car> tempCarList = new ArrayList<>();
        if(user instanceof Customer) {
            double rentalCoeff = ((Customer)user).rentalCoeff;
            for (Car car : carList) {
                // +1 , -1 is margin for double to long transform.
                if ((car.basePrice*rentalCoeff + 1) >= minPrice
                        && (car.basePrice*rentalCoeff-1) <= maxPrice) {
                    tempCarList.add(car);
                }
            }

        } if (user instanceof Salesman) {
            System.out.println("Using salesman basePrice search:");
            for (Car car : carList) {
                if (car.basePrice >= minPrice
                        && car.basePrice <= maxPrice) {
                    tempCarList.add(car);

                }
            }


        }

        if(tempCarList.isEmpty()) {
            System.out.println("Could find any results for your SEARCH CRITERIA");
            return carList;
        } else {
            return tempCarList;
        }
    }

    public List<Car> searchByMake(List<Car> carList, String make) {
        List<Car> tempCarList = new ArrayList<>();

        for (Car car : carList) {
            if (car.make.toLowerCase().contains(make.toLowerCase())) {
                tempCarList.add(car);
            }


        }

        if(tempCarList.isEmpty()) {
            System.out.println("Could find any results for your SEARCH CRITERIA");
            return carList;
        } else {
            return tempCarList;
        }
    }

    public List<Car> searchByModel(List<Car> carList, String model) {
        List<Car> tempCarList = new ArrayList<>();

        for (Car car : carList) {
            if (car.model.toLowerCase().contains(model.toLowerCase())) {
                tempCarList.add(car);
            }
        }

        if(tempCarList.isEmpty()) {
            System.out.println("Could find any results for your SEARCH CRITERIA");
            return carList;
        } else {
            return tempCarList;
        }
    }

    public List<Car> searchByVariant(List<Car> carList, String variant) {
        List<Car> tempCarList = new ArrayList<>();

        for (Car car : carList) {
            if (car.variant.toLowerCase().contains(variant.toLowerCase())) {
                tempCarList.add(car);
            }
        }

        if(tempCarList.isEmpty()) {
            System.out.println("Could find any results for your SEARCH CRITERIA");
            return carList;
        } else {
            return tempCarList;
        }
    }

    public List<Car> searchByCarType(List<Car> carList, String carType) {
        List<Car> tempCarList = new ArrayList<>();

        for (Car car : carList) {
            if (car.carType.toLowerCase().contains(carType.toLowerCase())) {
                tempCarList.add(car);
            }
        }
        if(tempCarList.isEmpty()) {
            System.out.println("Could find any results for your SEARCH CRITERIA");
            return carList;
        } else {
            return tempCarList;
        }
    }

    public List<Car> searchByYear(List<Car> carList, int choiceMinInt, int choiceMaxInt) {
        List<Car> tempCarList = new ArrayList<>();
        for (Car car : carList) {
                if (car.year >= choiceMinInt
                        && car.year <= choiceMaxInt) {
                    tempCarList.add(car);
                }
            }
        if(tempCarList.isEmpty()) {
            System.out.println("Could find any results for your SEARCH CRITERIA");
            return carList;
        } else {
            return tempCarList;
        }

    }

    public List<Car> searchByEngine(List<Car> carList, int choiceMinInt, int choiceMaxInt) {
        List<Car> tempCarList = new ArrayList<>();
        for (Car car : carList) {
            if (car.engine >= choiceMinInt
                    && car.engine <= choiceMaxInt) {
                tempCarList.add(car);
            }
        }
        if(tempCarList.isEmpty()) {
            System.out.println("Could find any results for your SEARCH CRITERIA");
            return carList;
        } else {
            return tempCarList;
        }
        
    }

    public List<Car> searchByFuelType(List<Car> carList, String fuelType) {
        List<Car> tempCarList = new ArrayList<>();

        for (Car car : carList) {
            if (car.fuelType.toLowerCase().contains(fuelType.toLowerCase())) {
                tempCarList.add(car);
            }
        }
        if(tempCarList.isEmpty()) {
            System.out.println("Could find any results for your SEARCH CRITERIA");
            return carList;
        } else {
            return tempCarList;
        }
   
    }

    public List<Car> searchByTransmission(List<Car> carList, String transmissionType) {
        List<Car> tempCarList = new ArrayList<>();

        for (Car car : carList) {
            if (car.transmissionType.toLowerCase().contains(transmissionType.toLowerCase())) {
                tempCarList.add(car);
            }
        }
        if(tempCarList.isEmpty()) {
            System.out.println("Could find any results for your SEARCH CRITERIA");
            return carList;
        } else {
            return tempCarList;
        }
   
   
    }

    public List<Car> searchByDoorNumber(List<Car> carList, int choiceMinInt, int choiceMaxInt) {
        List<Car> tempCarList = new ArrayList<>();
        for (Car car : carList) {
            if (car.doorNumber >= choiceMinInt
                    && car.doorNumber <= choiceMaxInt) {
                tempCarList.add(car);
            }
        }
        if(tempCarList.isEmpty()) {
            System.out.println("Could find any results for your SEARCH CRITERIA");
            return carList;
        } else {
            return tempCarList;
        }
   
    }

    public List<Car> searchByColor(List<Car> carList, String color) {
        List<Car> tempCarList = new ArrayList<>();

        for (Car car : carList) {
            if (car.color.toLowerCase().contains(color.toLowerCase())) {
                tempCarList.add(car);
            }
        }
        if(tempCarList.isEmpty()) {
            System.out.println("Could find any results for your SEARCH CRITERIA");
            return carList;
        } else {
            return tempCarList;
        }
    
    }

    public List<Car> searchByID(List<Car> carList, String ID) {
        List<Car> tempCarList = new ArrayList<>();
        int integerID = -1;
        try{
            integerID = Integer.parseInt(ID);
        }catch (Exception e) {
            System.out.println("Invalid input for ID:");
        }


        for (Car car : carList) {
            if (car.id == (integerID)) {
                tempCarList.add(car);
            }
        }
        if(tempCarList.isEmpty()) {
            System.out.println("Could find any results for your SEARCH CRITERIA");
            return carList;
        } else {
            return tempCarList;
        }

    }

    public List<Car> searchByVIN(List<Car> carList, String VIN) {
        List<Car> tempCarList = new ArrayList<>();

        for (Car car : carList) {
            if (car.VIN.equals(VIN)) {
                tempCarList.add(car);
            }
        }
        if(tempCarList.isEmpty()) {
            System.out.println("Could find any results for your SEARCH CRITERIA");
            return carList;
        } else {
            return tempCarList;
        }

    }
}
