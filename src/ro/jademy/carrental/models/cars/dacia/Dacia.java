package ro.jademy.carrental.models.cars.dacia;
import ro.jademy.carrental.models.cars.Car;

public class Dacia extends Car {


    public Dacia(int id, String model, String variant, String carType, int year, int engine, String fuelType, String transmissionType, Integer doorNumber, String color, String VIN, long basePrice) {
        super(id, "Dacia", model, variant, carType, year, engine, fuelType, transmissionType, doorNumber, color, VIN, basePrice);
    }
}
