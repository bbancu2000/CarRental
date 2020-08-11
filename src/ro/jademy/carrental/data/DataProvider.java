package ro.jademy.carrental.data;

import ro.jademy.carrental.models.cars.Car;
import ro.jademy.carrental.models.cars.bmw.Serie3;
import ro.jademy.carrental.models.cars.dacia.Logan;
import ro.jademy.carrental.models.Customer;
import ro.jademy.carrental.models.Salesman;
import ro.jademy.carrental.models.User;

import java.util.ArrayList;

public class DataProvider {

    public static ArrayList<Car> getCarList() {
        ArrayList<Car> carList = new ArrayList<>();

        carList.add(new Logan(10, "Laureat", "Sedan", 2009, 1400, "Gasoline", "Manual", 4, "White", "ABC", 100));
        carList.add(new Logan(11, "Clima", "Sedan", 2007, 1600, "GPL", "Manual", 4, "Blue", "ABC", 110));
        carList.add(new Logan(12, "MCV", "Break", 2008, 1500, "Diesel", "Manual", 5, "Red", "ABC", 120));

        carList.add(new Serie3(13, "E90", "Sedan", 2006, 2000, "Gasoline", "Manual", 4, "White", "ABC", 100));
        carList.add(new Serie3(14, "F30", "Sedan", 2014, 1800, "Diesel", "Manual", 4, "Blue",  "ABC", 110));
        carList.add(new Serie3(15, "G20", "Break", 2018, 1600, "Diesel", "Manual",5, "Red",  "ABSDL", 120));

        carList.get(2).isRented = true;




        return carList;

    }

    public static ArrayList<User> getUserList() {
        ArrayList<User> userList = new ArrayList<>();

        userList.add(new Customer("floricica123", "Floricica", "Dansatoarea", "floricele", "Female", 25, 1, 1, 0));
        userList.add(new Customer("daniel", "Daniel", "DeLaPaza", "bgs","Male", 30, 5, 4, 0));
        userList.add(new Salesman("bbancu", "Bogdan", "Bancu", "1234"));



        return userList;
    }


    // FUTURE : Get rental history from DB


}
