package ro.jademy.carrental.models.cars;


import java.util.Objects;

public class Car {

    // Q: how can we better represent the car make?
    public int id;

    public String make;
    public String model;
    public String variant;

    public String carType; // coupe, sedan, hatchback, convertible, wagon, SUV
    public int year;

    public int engine;
    public String fuelType; // diesel, gasoline, hybrid, electric
    public String transmissionType; // automatic, manual

    public Integer doorNumber;
    public String color;

    public final String VIN;
    public long basePrice;

    public boolean isRented;


    // Q: how can we better protect the car data?


    public Car(int id, String make, String model, String variant, String carType, int year, int engine, String fuelType, String transmissionType, Integer doorNumber, String color, String VIN, long basePrice) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.variant = variant;
        this.carType = carType;
        this.year = year;
        this.engine = engine;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        this.doorNumber = doorNumber;
        this.color = color;
        this.VIN = VIN;
        this.basePrice = basePrice;
    }

    @Override
    public String toString() {
        return "[ Car: "  +
                " id: " + id + '\'' +
                ", make: " + make + '\'' +
                ", model: " + model + '\'' +
                ", variant: " + variant + '\'' +
                ", carType: " + carType + '\'' +
                ", year: " + year +
                ", engine: " + engine +
                ", fuelType: " + fuelType + '\'' +
                ", transmissionType: " + transmissionType + '\'' +
                ", doorNumber: " + doorNumber +
                ", color: " + color + '\'' +
                ", basePrice: " + basePrice +
                ", isRented: " + isRented +
                " ]";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return id == car.id &&
                year == car.year &&
                engine == car.engine &&
                basePrice == car.basePrice &&
                Objects.equals(make, car.make) &&
                Objects.equals(model, car.model) &&
                Objects.equals(variant, car.variant) &&
                Objects.equals(carType, car.carType) &&
                Objects.equals(fuelType, car.fuelType) &&
                Objects.equals(transmissionType, car.transmissionType) &&
                Objects.equals(doorNumber, car.doorNumber) &&
                Objects.equals(color, car.color) &&
                Objects.equals(VIN, car.VIN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, make, model, variant, carType, year, engine, fuelType, transmissionType, doorNumber, color, VIN, basePrice);
    }
}
