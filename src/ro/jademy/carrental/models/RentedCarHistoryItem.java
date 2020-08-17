package ro.jademy.carrental.models;

import ro.jademy.carrental.models.cars.Car;

import java.time.LocalDate;

public class RentedCarHistoryItem {

    public User user;
    public Car car;
    public LocalDate pickupDate;
    private int daysToBeRented;
    public LocalDate returnDate;
    public long rentPerDay;
    public long totalRent;
    public String salesmanName;

    public RentedCarHistoryItem(User user, Car car, LocalDate pickupDate, int daysToBeRented, long rentPerDay) {
        this.user = user;
        this.car = car;
        this.pickupDate = pickupDate;
        this.daysToBeRented = daysToBeRented;
        this.returnDate = pickupDate.plusDays(daysToBeRented-1);
        this.rentPerDay = rentPerDay;
        this.totalRent = rentPerDay*daysToBeRented;
    }

    @Override
    public String toString() {
        return "[ RentedCarHistoryItem ]\n" +
                "[" + user +
                " ]\n" + car +
                " ] \n[ PickupDate: " + pickupDate +
                ", ReturnDate: " + returnDate +
                ", DaysToBeRented:" + daysToBeRented +
                ", RentPerDay=" + rentPerDay +
                ", TotalRent=" + totalRent +
                " ]";
    }
}
