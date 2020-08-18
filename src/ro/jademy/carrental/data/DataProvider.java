package ro.jademy.carrental.data;

import ro.jademy.carrental.models.cars.Car;
import ro.jademy.carrental.models.cars.bmw.Serie3;
import ro.jademy.carrental.models.cars.bmw.Serie5;
import ro.jademy.carrental.models.cars.bmw.Serie7;
import ro.jademy.carrental.models.cars.dacia.Duster;
import ro.jademy.carrental.models.cars.dacia.Lodgy;
import ro.jademy.carrental.models.cars.dacia.Logan;
import ro.jademy.carrental.models.Client;
import ro.jademy.carrental.models.Salesman;
import ro.jademy.carrental.models.User;
import ro.jademy.carrental.models.cars.dacia.Sandero;

import java.util.ArrayList;

public class DataProvider {

    public static ArrayList<Car> getCarList() {
        ArrayList<Car> carList = new ArrayList<>();

        carList.add(new Logan(1, "Acces", "Sedan", 2018, 1400, "Gasoline", "Manual", 4, "White", "ABC", 100));
        carList.add(new Logan(2, "Ambiance", "Break", 2019, 1600, "GPL", "Manual", 4, "Blue", "ABCD", 110));
        carList.add(new Duster(3, "Essential", "SUV", 2015, 1500, "Diesel", "Manual", 5, "Red", "ABCE", 120));
        carList.add(new Duster(4, "Comfort", "SUV", 2017, 1500, "Gasoline", "Manual", 5, "Red", "ABCEF", 120));
        carList.add(new Sandero(5, "Acces", "Hatchback", 2018, 1500, "Diesel", "Manual", 5, "Red", "ABCEFG", 120));
        carList.add(new Sandero(6, "Acces", "Hatchback", 2019, 1000, "Gasoline", "Manual", 5, "Red", "ABCEFGH", 120));
        carList.add(new Sandero(7, "Ambiance", "Hatchback", 2017, 1000, "GPL", "Manual", 5, "Red", "ABCZ", 130));
        carList.add(new Lodgy(8, "Ambiance", "MPV", 2017, 1200, "Diesel", "Manual", 5, "Red", "ABCX", 125));
        carList.add(new Lodgy(9, "Laureate", "MPV", 2019, 1500, "Diesel", "Manual", 5, "Red", "ABCG", 135));


        carList.add(new Serie3(10, "E90", "Sedan", 2006, 2000, "Gasoline", "Manual", 4, "White", "ABCZX", 100));
        carList.add(new Serie3(11, "F30", "Sedan", 2014, 1800, "Diesel", "Manual", 4, "Blue",  "ABCZ1", 110));
        carList.add(new Serie3(12, "G20", "Break", 2018, 1600, "Diesel", "Manual",5, "Red",  "ABSDLZ2", 120));
        carList.add(new Serie5(13, "520i", "Sedan", 2020, 2000, "Gasoline", "Manual",4, "Red",  "ABSDLZ3", 120));
        carList.add(new Serie5(14, "530d xDrive M", "Sedan", 2020, 3000, "Diesel", "Automatic",4, "Red",  "ABSDLZ4", 180));
        carList.add(new Serie5(15, "530e xDrive M", "Break", 2019, 2000, "Hybrid", "Automatic",5, "Red",  "ABSDLZ5", 120));
        carList.add(new Serie7(16, "730d", "Sedan", 2020, 3000, "Diesel", "Automatic",4, "Red",  "ABSDLZ6", 200));
        carList.add(new Serie7(17, "740d xDrive", "Sedan", 2019, 4000, "Diesel", "Automatic",4, "Red",  "ABSDLZ7", 220));
        carList.add(new Serie7(18, "750i XDrive", "Sedan", 2018, 5000, "Gasoline", "Automatic",4, "Red",  "ABSDLZ8", 250));






        return carList;

    }

    public static ArrayList<User> getUserList() {
        ArrayList<User> userList = new ArrayList<>();

        userList.add(new Client("floricica123", "Floricica", "Dansatoarea", "floricele", "Female", "1990-05-01", "2010-01-01", 1, 0, 10000));
        userList.add(new Client("daniel", "Daniel", "DeLaPaza", "bgs","Male", "1991-06-20", "2015-05-01", 4, 0, 10000));
        userList.add(new Salesman("bbancu", "Bogdan", "Bancu", "1234"));



        return userList;
    }

    public static long getShopBalance() {
        return 0;
    }


    // FUTURE : Get rental history from DB


}
