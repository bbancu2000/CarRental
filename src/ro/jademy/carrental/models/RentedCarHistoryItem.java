package ro.jademy.carrental.models;

import ro.jademy.carrental.models.cars.Car;

import java.time.LocalDate;

public class RentedCarHistoryItem {

    public User user;
    public Car car;
    private LocalDate pickupDate;
    private int daysToBeRented;
    public LocalDate returnDate;
    private double rentPerDay;
    public double totalRent;
    boolean isCurrentlyRented;
    public User salesman;

    public RentedCarHistoryItem(User user, Car car, LocalDate pickupDate, int daysToBeRented, double rentPerDay) {
        this.user = user;
        this.car = car;
        this.pickupDate = pickupDate;
        this.daysToBeRented = daysToBeRented;
        this.returnDate = pickupDate.plusDays(daysToBeRented);
        this.rentPerDay = rentPerDay;
        this.totalRent = rentPerDay*daysToBeRented;
        this.isCurrentlyRented = true;
    }

    @Override
    public String toString() {
        return "RentedCarHistoryItem{" +
                "user=" + user +
                "\n car=" + car +
                "\n pickupDate=" + pickupDate +
                ", daysToBeRented=" + daysToBeRented +
                ", returnDate=" + returnDate +
                ", rentPerDay=" + rentPerDay +
                ", totalRent=" + totalRent +
                ", isCurrentlyRented=" + isCurrentlyRented +
                '}';
    }
}
