package ro.jademy.carrental.services;

import ro.jademy.carrental.models.cars.Car;

import java.util.ArrayList;
import java.util.List;

public class FilterService {

    public List<Car> searchByMake(List<Car> carList, String make) {
        List<Car> tempCarList = new ArrayList<>();

        for (Car car : carList) {
            if (car.make.equalsIgnoreCase(make)) {
                tempCarList.add(car);
            }
        }

        return tempCarList;
    }

    public List<Car> searchByModel(List<Car> carList, String model) {
        List<Car> tempCarList = new ArrayList<>();

        for (Car car : carList) {
            if (car.model.equalsIgnoreCase(model)) {
                tempCarList.add(car);
            }
        }

        return tempCarList;
    }

    public List<Car> searchByVariant(List<Car> carList, String variant) {
        List<Car> tempCarList = new ArrayList<>();

        for (Car car : carList) {
            if (car.variant.equalsIgnoreCase(variant)) {
                tempCarList.add(car);
            }
        }

        return tempCarList;
    }


    public static void giveCar() {

    }

    public static void receiveCar() {

    }


}
