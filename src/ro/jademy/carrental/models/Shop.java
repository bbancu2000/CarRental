package ro.jademy.carrental.models;

import ro.jademy.carrental.data.DataProvider;
import ro.jademy.carrental.models.cars.Car;
import ro.jademy.carrental.services.FilterService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shop {
    // Q: what fields and methods should this class contain?
    private Scanner sc = new Scanner(System.in);
    private int maximumRentalDays = 30;

    private ArrayList<User> userList = new ArrayList<>();
    private ArrayList<Car> allCarsList = new ArrayList<>();
    private ArrayList<RentedCarHistoryItem> rentedCarList = new ArrayList<>();
    private ArrayList<RentedCarHistoryItem> historyRentedCarList = new ArrayList<>();

    private ArrayList<RentedCarHistoryItem> pendingTransaction = new ArrayList<>();
    public User currentUser = null;

    // services
    private FilterService filterService = new FilterService();

    public void start() {
        boolean isExit = false;
        initShop();
        do {
            if (currentUser == null) {
                login();
            }
            showMenu(currentUser);

        } while (!isExit);
    }

    public void initShop() {
        userList = DataProvider.getUserList();
        allCarsList = DataProvider.getCarList();
        //future : getRentedCarList from DB;
    }

    public void login() {
        boolean loginSuccess = false;
        do {
            System.out.println("Please enter the username: ");
            String user = sc.nextLine();
            System.out.println("Please enter the password: ");
            String pass = sc.nextLine();

            for (User userItem : userList) {
                if (user.equalsIgnoreCase(userItem.userName)
                        && pass.equals(userItem.password)) {
                    System.out.println("User: " + userItem.userName + "\n");
                    loginSuccess = true;
                    currentUser = userItem;
                }
            }
            System.out.println("Incorrect Username/Password combination");
        } while (!loginSuccess);
    }


    public void showMenu(User user) {
        System.out.println(" -----------------------------------------------");
        System.out.println("|    Welcome to the BANCU Car Rental Shop   |");
        System.out.println(" -----------------------------------------------");
        System.out.println();
        System.out.println("                    MAIN MENU                   ");
        System.out.println("1. List all cars");
        System.out.println("2. List available cars");
        System.out.println("3. List rented cars");
        System.out.println("4. Check income");
        System.out.println("5. Logout");
        System.out.println("6. Exit");
        System.out.println("7. [ADMIN] Show pending rentals");

        String temp = sc.nextLine();
        Car currentCar = null;

        switch (temp) {
            case "1":
                //LIST ALL CARS
                currentCar = selectCarFromList(getAllCarList());
                // TODO NULL PROOFING ?
                rentSubMenu(currentCar, currentUser);
                break;

            case "2":
                // LIST AVAILABLE CARS
                currentCar = selectCarFromList(getAvailableCars());
                rentSubMenu(currentCar, currentUser);
                break;

            case "3":
                getRentedCars();
                break;

            case "4":

                break;
            case "5":
                //Logout
                currentUser = null;
                break;

            case "6":

                break;

            case "7":
                getPendingRentals();
                break;


        }
    }

    //TODO
    public Car showSearchMenuOptions(List<Car> carList) {
        System.out.println("Select an action from below:");
        System.out.println("1. Filter by make");
        System.out.println("2. Filter by model");
        System.out.println("3. Filter by variant");
        System.out.println("4. Filter by cartype");
        System.out.println("5. Filter by year");
        System.out.println("6. Filter by engine");
        System.out.println("7. Filter by fueltype");
        System.out.println("8. Filter by transmission-type");
        System.out.println("9. Filter by doornumber");
        System.out.println("10. Filter by color");
        System.out.println("11. Filter by VIN");
        System.out.println("12. Filter by baseprice");

        System.out.println("N. Return to previous menu");

        boolean isValidChoice = false;
        List<Car> tempCarList = new ArrayList<>();
        String choice = sc.nextLine().toUpperCase();
        Car currentCar = null;


        //TODO LABELS ?
        do {
            switch (choice) {
                //TODO
                case "1":
                    //FILTER BY MAKE
                    System.out.println("imi intra pe switch showSearchMenuOptions- case1");
                    System.out.println("Filter by Make:");
                    String make = sc.nextLine();
                    tempCarList = filterService.searchByMake(carList, make);
                    printCarList(tempCarList);

                    currentCar = selectCarFromList(tempCarList);

                    isValidChoice = true;
                    break;

                case "2":
                    //FILTER BY MODEL
                    System.out.println("imi intra pe switch showSearchMenuOptions- case2");
                    System.out.println("Filter by Model:");
                    String model = sc.nextLine();
                    tempCarList = filterService.searchByModel(carList, model);
                    printCarList(tempCarList);
                    currentCar = selectCarFromList(tempCarList);
                    isValidChoice = true;
                    break;

                case "3":
                    //Filter by variant
                    System.out.println("imi intra pe switch showSearchMenuOptions- case3");
                    System.out.println("Filter by Variant:");
                    String variant = sc.nextLine();
                    tempCarList = filterService.searchByVariant(carList, variant);
                    printCarList(tempCarList);
                    currentCar = selectCarFromList(tempCarList);
                    isValidChoice = true;

                    break;

                case "4":

                    break;

                case "5":

                    break;

                case "N":
                    return null;

                default:
                    System.out.println("Please enter a valid choice.");
                    isValidChoice = true;
                    break;
            }
        } while (!isValidChoice);

        return currentCar;
    }


    public void getPendingRentals() {
        if (currentUser instanceof Salesman) {
            int choiceInt = -1;

            for (int i = 0; i < pendingTransaction.size(); i++) {
                System.out.println(i + ". " + pendingTransaction.get(i));
            }

            System.out.println("Select the rental you want to confirm: [ Type N to return to Main Menu ]");
            String choice = sc.nextLine();
            if (choice.equalsIgnoreCase("n")) {
                // empty code block , return back
            } else {
                //TODO  TRY-CATCH for defensive coding
                choiceInt = Integer.parseInt(choice);

                RentedCarHistoryItem selectedCarItem = pendingTransaction.get(choiceInt);

                //adding salesman username to the transaction
                selectedCarItem.salesman = currentUser;
                // adding CarItem to the active rentedCarList
                rentedCarList.add(selectedCarItem);
                // adding Caritem to the current rentals of the user;
                ((Customer) selectedCarItem.user).currentRentals.add(selectedCarItem);
                // removing CarItem from pendingTransactions
                pendingTransaction.remove(selectedCarItem);

                System.out.println("Transaction confirmed , car was given to the customer.");


            }


        } else {
            System.out.println("You are not a salesman !!! ");
        }

    }


    public Car selectCarFromList(List<Car> carList) {
        boolean isValid = false;
        int choiceInt = -1;
        System.out.println("imi intra pe selectCarFromList si ");
        if (carList == null || carList.isEmpty()) {
            System.out.println("selectCarFromList received null carList");
            return null;
        } else {
            System.out.println("si printez lista DACA NU ESTE GOALA SAU NULA");
            printCarList(carList);
            System.out.println("Please select the index:      [or Type S for Search, or Type N for Main Menu");
            String choice = sc.nextLine();
            if (choice.equalsIgnoreCase("s")) {
                return showSearchMenuOptions(carList);
            } else if (choice.equalsIgnoreCase("n")) {
                return null;
                // TODO
            } else {
                // TODO
                choiceInt = Integer.parseInt(choice);
            }
        }

//
        //TODO sa anunte ca e luata masina aia

        return carList.get(choiceInt);
    }

    public ArrayList<Car> getAllCarList() {
        System.out.println("Rented cars:");
        for (int i = 0; i < allCarsList.size(); i++) {
            if (allCarsList.get(i).isRented) {
                System.out.println(i + ". " + allCarsList.get(i));
            }
        }
        System.out.println("\nAvailable cars:");
        for (int i = 0; i < allCarsList.size(); i++) {
            if (!allCarsList.get(i).isRented) {
                System.out.println(i + ". " + allCarsList.get(i));
            }
        }
        return allCarsList;
    }

    public ArrayList<Car> getAvailableCars() {
        // show only AVAILABLE CARS, not rented ones.
        ArrayList<Car> carList = new ArrayList<>();
        for (int i = 0; i < allCarsList.size(); i++) {
            if (!allCarsList.get(i).isRented) {
                carList.add(allCarsList.get(i));
                int lastIndex = carList.size() - 1;
                System.out.println(lastIndex + ". " + carList.get(lastIndex));
            }
        }
        return carList;
    }

    public void printCarList(List<Car> carList) {
        for (int i = 0; i < carList.size(); i++) {
            System.out.println(i + ". " + carList.get(i));
        }
    }

    public void rentSubMenu(Car car, User user) {
        if (car != null && user != null) {
            boolean isValid = false;
            int nrOfDays = -1;
            double rentCoeff = ((Customer) user).getRentalCoeff();

            System.out.println("Would you like to rent this car?");
            System.out.println(car);
            System.out.println("Price per day: " + (car.basePrice * rentCoeff));
            System.out.println();
            if (car.isRented) {
                System.out.println("This car is already rented !!!");
                // TODO give return date.
                System.out.print("You can rent this car: ");

                for (int i = 0; i < rentedCarList.size(); i++) {
                    if (rentedCarList.get(i).car.id == car.id) {
                        System.out.println(rentedCarList.get(i).returnDate + "\n");
                    }
                }

            } else {
                do {
                    System.out.println("Type the number of days:      [or type N to return to Main Menu]");
                    String choice = sc.nextLine();
                    if (choice.equalsIgnoreCase("n")) {
                        //empty code block
                        isValid = true;
                    } else {
                        try {
                            nrOfDays = Integer.parseInt(choice);
                            //
                            if (nrOfDays > 0 && nrOfDays <= maximumRentalDays) {
                                double rentPerDay = car.basePrice * rentCoeff;
                                double totalRent = rentPerDay * nrOfDays;
                                System.out.println("Total cost: " + totalRent + ", for " + nrOfDays + " days of rental.");

                                // ask permision  & create transaction
                                createTransaction(car, user, nrOfDays, rentPerDay);

                                isValid = true;
                                break;
                            }
                        } catch (Exception e) {
                            isValid = false;
                        }
                    }
                } while (!isValid);
            }


        } else {
            System.out.println("RentSubmenu received NULL car !!!");
        }
    }


    public void getRentedCars() {
        // show only RENTED CARS
        ArrayList<Car> carList = new ArrayList<>();
        String returnDateStr = "";

        for (Car car : allCarsList) {

            if (car.isRented) {
                carList.add(car);
                int lastIndex = carList.size() - 1;

                for (RentedCarHistoryItem carItem : rentedCarList) {
                    if (carItem.car.id == carList.get(lastIndex).id) {
                        returnDateStr = carItem.returnDate.toString();
                    }
                }
                //Displaying available date + the car
                System.out.println(lastIndex + ". Available: " + returnDateStr + ", " + carList.get(lastIndex));
            }

        }

    }


    public void createTransaction(Car car, User user, int nrOfDays, double rentPerDay) {
        System.out.println("Are you sure you want to make this transaction ?  [ Type Y/N ]");

        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("y")) {

            car.isRented = true;
            LocalDate timeNow = LocalDate.now();

            RentedCarHistoryItem rentedCar = new RentedCarHistoryItem(user, car, timeNow, nrOfDays, rentPerDay);
            pendingTransaction.add(rentedCar);
            System.out.println("Created transaction, ");

        } else if (choice.equalsIgnoreCase("n")) {
            // TODO do-while for Y/N proofing
            //empty code block
        }


    }


}
