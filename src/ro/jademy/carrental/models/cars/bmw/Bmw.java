package ro.jademy.carrental.models.cars.bmw;
import ro.jademy.carrental.models.cars.Car;

public class Bmw extends Car {

    public Bmw(int id, String model, String variant, String carType, int year, int engine, String fuelType, String transmissionType, Integer doorNumber, String color, String VIN, int basePrice) {
        super(id, "Bmw", model, variant, carType, year, engine, fuelType, transmissionType, doorNumber, color, VIN, basePrice);
    }
}
