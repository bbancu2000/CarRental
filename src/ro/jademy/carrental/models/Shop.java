package ro.jademy.carrental.models;

import ro.jademy.carrental.data.DataProvider;
import ro.jademy.carrental.models.cars.Car;
import ro.jademy.carrental.services.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shop {
    // Q: what fields and methods should this class contain?
    private Scanner sc = new Scanner(System.in);
    private int maximumRentalDays = 30;
    public int maximumRentalDaysInFuture = 7;

    private ArrayList<User> userList = new ArrayList<>();
    public ArrayList<Car> allCarsList = new ArrayList<>();
    private ArrayList<RentedCarHistoryItem> rentedCarList = new ArrayList<>();
    private ArrayList<RentedCarHistoryItem> historyRentedCarList = new ArrayList<>();

    private ArrayList<RentedCarHistoryItem> pendingTransactions = new ArrayList<>();
    public User currentUser = null;

    // services
    private FilterService filterService = new FilterService();
    private RentingService rentingService = new RentingService();
    private PrintingService printingService;
    private StatsService statsService = new StatsService();


    public void start() {
        boolean isExit = false;
        initShop();
        do {
            if (currentUser == null) {
                login();
            }
            showMenu();

        } while (!isExit);
    }

    public void initShop() {
        userList = DataProvider.getUserList();
        allCarsList = DataProvider.getCarList();
        //future : getRentedCarList from DB;
        printingService = new PrintingService(allCarsList);
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
            if (currentUser == null) {
                System.out.println("Incorrect Username/Password combination");
            }
        } while (!loginSuccess);

        if (currentUser instanceof Customer) {
            ((Customer) currentUser).rentalCoeff = ((Customer) currentUser).getRentalCoeff();
        }


    }


    public void showMenu() {
        String temp;
        Car currentCar = null;

        if (currentUser instanceof Customer) {
            System.out.println(" -----------------------------------------------");
            System.out.println("|       Welcome to the BANCU Car Rental Shop    |");
            System.out.println("|                  ( Customer )                 |");
            System.out.println(" -----------------------------------------------");
            System.out.println();
            System.out.println("                    MAIN MENU                   ");
            System.out.println("1. List all cars");
            System.out.println("2. List available cars");
            System.out.println("3. List rented cars");
            System.out.println("");
            System.out.println("8. Logout");
            System.out.println("9. //Exit");

            temp = sc.nextLine();
            switch (temp) {
                case "1":
                    // LIST ALL CARS
                    printingService.printCarList(getAllCarList(), currentUser);
                    currentCar = selectCarFromList(getAllCarList());
                    printingService.printSingleCar(currentCar, currentUser);
                    rentingService.rentSubMenu(currentCar, currentUser, maximumRentalDays, maximumRentalDaysInFuture, rentedCarList, pendingTransactions);
                    break;

                case "2":
                    // LIST AVAILABLE CARS
                    printingService.printCarList(getAvailableCars(), currentUser);
                    currentCar = selectCarFromList(getAvailableCars());
                    printingService.printSingleCar(currentCar, currentUser);
                    rentingService.rentSubMenu(currentCar, currentUser, maximumRentalDays, maximumRentalDaysInFuture, rentedCarList, pendingTransactions);
                    break;

                case "3":
                    // LIST RENTED CARS
                    //TODO appointCarOnReturn
                    //TODO getrentedcars , and then do what?
                    List<Car> tempList = getRentedCars();

                    if(tempList.isEmpty()) {
                        System.out.println("No current rented cars !!");
                        System.out.println();
                    }

                    break;

                case "8":
                    // Logout
                    currentUser = null;
                    break;

                case "9":
                    // EXIT?
                    break;

                default :
                    System.out.println("Invalid index for Main Menu");
                    break;
            }

        } else if (currentUser instanceof Salesman) {
            System.out.println();
            System.out.println(" -----------------------------------------------");
            System.out.println("|       Welcome to the BANCU Car Rental Shop    |");
            System.out.println("|                 ( Salesman )                  |");
            System.out.println(" -----------------------------------------------");
            System.out.println();
            System.out.println("                    MAIN MENU                   ");
            System.out.println("1. List all cars");
            System.out.println("2. List available cars");
            System.out.println("3. List rented cars");
            System.out.println("4. Check income");
            System.out.println("5. Show pending rentals");
            System.out.println("6. Return car rentals");
            System.out.println("7. STATISTICS");
            //System.out.println("by user, by car, by shop");
            System.out.println("8. Logout");
            System.out.println("9. Exit");

            temp = sc.nextLine();
            switch (temp) {
                case "1":
                    // LIST ALL CARS
                    printingService.printCarList(getAllCarList(), currentUser);
                    currentCar = selectCarFromList(getAllCarList());
                    printingService.printSingleCar(currentCar, currentUser);
                    rentingService.rentSubMenu(currentCar, currentUser, maximumRentalDays, maximumRentalDaysInFuture, rentedCarList, pendingTransactions);
                    break;

                case "2":
                    // LIST AVAILABLE CARS
                    printingService.printCarList(getAvailableCars(), currentUser);
                    currentCar = selectCarFromList(getAvailableCars());
                    printingService.printSingleCar(currentCar, currentUser);
                    rentingService.rentSubMenu(currentCar, currentUser, maximumRentalDays, maximumRentalDaysInFuture, rentedCarList, pendingTransactions);
                    break;

                case "3":
                    // LIST RENTED CARS
                    //TODO appointCarOnReturn
                    //TODO getrentedcars , and then do what?
                    List<Car> tempList = getRentedCars();

                    if(tempList.isEmpty()) {
                        System.out.println("No current rented cars !!");
                        System.out.println();
                    }
                    break;

                case "4" :
                    //CHECK INCOME
                statsService.checkIncomeMenu(rentedCarList,pendingTransactions,historyRentedCarList);
                    break;

                case "5":
                    // SHOW PENDING RENTALS
                    rentingService.confirmPendingRentals(currentUser, rentedCarList, pendingTransactions);
                    break;

                case "6":
                    // RETURN CAR RENTALS
                    //TODO print rentedcarhistoryitem LIST;
                    rentingService.returnRental(rentedCarList,pendingTransactions,historyRentedCarList);
                    break;

                case "7":
                    // STATISTICS

                    statsService.StatisticsMenu(allCarsList,pendingTransactions,rentedCarList,historyRentedCarList);

                    break;



                case "8":
                    // Logout
                    currentUser = null;
                    break;

                case "9":
                    // EXIT?
                    break;

                default :
                    System.out.println("Invalid index for Main Menu");
                    break;
            }
        }




    }



    public Car showSearchMenuOptions(List<Car> carList) {
        System.out.println("Select an action from below:");
        System.out.println("1. Filter by price");
        System.out.println("2. Filter by make");
        System.out.println("3. Filter by model");
        System.out.println("4. Filter by variant");
        System.out.println("5. Filter by cartype");
        System.out.println("6. Filter by year");
        System.out.println("7. Filter by engine");
        System.out.println("8. Filter by fueltype");
        System.out.println("9. Filter by transmission-type");
        System.out.println("10. Filter by doornumber");
        System.out.println("11. Filter by color");
        System.out.println("12. Filter by ID [ADMIN only??]");
        System.out.println("13. Filter by VIN [ADMIN only]");

        System.out.println("N. Return to previous menu");

        boolean isValidChoice = false;
        Car currentCar = null;

        do {
            String choice = sc.nextLine().toUpperCase();
            switch (choice) {
                //TODO
                case "1":
                    //FILTER BY PRICE
                    selectFilterOption(currentUser, "price", carList);
                    isValidChoice = true;
                    break;

                case "2":
                    // FILTER BY MAKE
                    selectFilterOption(currentUser, "make", carList);
                    isValidChoice = true;
                    break;

                case "3":
                    // Filter by MODEL
                    selectFilterOption(currentUser, "model", carList);
                    isValidChoice = true;
                    break;

                case "4":
                    //Filter by VARIANT
                    selectFilterOption(currentUser, "variant", carList);
                    isValidChoice = true;
                    break;

                case "5":
                    //Filter by CAR-TYPE
                    selectFilterOption(currentUser, "cartype", carList);
                    isValidChoice = true;
                    break;

                case "6":
                    //Filter by YEAR
                    selectFilterOption(currentUser, "year", carList);
                    isValidChoice = true;
                    break;

                case "7":
                    //FILTER BY ENGINE
                    selectFilterOption(currentUser, "engine", carList);
                    isValidChoice = true;
                    break;

                case "8":
                    //FILTER BY FUELTYPE
                    selectFilterOption(currentUser, "fueltype", carList);
                    isValidChoice = true;
                    break;

                case "9":
                    //FILTER BY TRANSMISSION
                    selectFilterOption(currentUser, "transmission", carList);
                    isValidChoice = true;
                    break;

                case "10":
                    //FILTER BY DOORNUMBER
                    selectFilterOption(currentUser, "doornumber", carList);
                    isValidChoice = true;
                    break;

                case "11":
                    //FILTER BY COLOR
                    selectFilterOption(currentUser, "color", carList);
                    isValidChoice = true;
                    break;

                case "12":
                    //FILTER BY ID
                    selectFilterOption(currentUser, "ID", carList);
                    isValidChoice = true;
                    break;

                case "13":
                    //FILTER BY VIN
                    selectFilterOption(currentUser, "VIN", carList);
                    isValidChoice = true;
                    break;

                case "N":
                    return null;

                default:
                    System.out.println("Please enter a valid choice.");
                    isValidChoice = false;
                    break;
            }
        } while (!isValidChoice);
        return currentCar;
    }

    public void selectFilterOption(User currentUser, String filterType, List<Car> carList) {
        int choiceMinInt = -1;
        int choiceMaxInt = -1;
        Car currentCar = null;

        switch (filterType) {
            case "price":
                //FILTER BY PRICE
                System.out.println("Filter By Price");
                System.out.println("Insert minimum price:  [Numerical]");
                choiceMinInt = Operations.getValidNumberInput(0);
                System.out.println("Insert maximum price:  [Numerical]");
                choiceMaxInt = Operations.getValidNumberInput(0);
                carList = filterService.searchByPrice(currentUser, carList, choiceMinInt, choiceMaxInt);
                break;

            case "make":
                //FILTER BY MAKE
                System.out.println("Filter by Make:");
                String make = sc.nextLine();
                carList = filterService.searchByMake(carList, make);
                break;

            case "model":
                // Filter by MODEL
                System.out.println("Filter by Model:");
                String model = sc.nextLine();
                carList = filterService.searchByModel(carList, model);
                break;

            case "variant":
                //Filter by VARIANT
                System.out.println("Filter by Variant:");
                String variant = sc.nextLine();
                carList = filterService.searchByVariant(carList, variant);
                break;

            case "cartype":
                //Filter by CarType
                System.out.println("Filter by CarType:");
                System.out.println("CarTypes: Sedan, Hatchback, Break, SUV, Coupe, Truck, Van");
                String carType = sc.nextLine();
                carList = filterService.searchByCarType(carList, carType);
                break;

            case "year":
                //FILTER BY YEAR
                System.out.println("Filter By YEAR");
                System.out.println("Insert minimum YEAR:  [Numerical]");
                choiceMinInt = Operations.getValidNumberInput(0);
                System.out.println("Insert maximum YEAR:  [Numerical]");
                choiceMaxInt = Operations.getValidNumberInput(0);
                carList = filterService.searchByYear(carList, choiceMinInt, choiceMaxInt);
                break;

            case "engine":
                // FILTER BY ENGINE
                System.out.println("Filter By ENGINE");
                System.out.println("Insert minimum ENGINE-size:  [Numerical]");
                choiceMinInt = Operations.getValidNumberInput(0);
                System.out.println("Insert maximum ENGINE-size:  [Numerical]");
                choiceMaxInt = Operations.getValidNumberInput(0);
                carList = filterService.searchByEngine(carList, choiceMinInt, choiceMaxInt);
                break;

            case "fueltype":
                //Filter by FUELTYPE
                System.out.println("Filter by FUELTYPE:");
                System.out.println("FUELTYPE: Gasoline, Diesel, GPL, Hybrid, Electric");
                String fueltype = sc.nextLine();
                carList = filterService.searchByFuelType(carList, fueltype);
                break;

            case "transmission":
                //FILTER BY TRANSMISSION
                System.out.println("Filter by TRANSMISSION:");
                System.out.println("TRANSMISSION: Automatic, Manual");
                String transmission = sc.nextLine();
                carList = filterService.searchByTransmission(carList, transmission);
                break;

            case "doornumber":
                //FILTER BY DOORNUMBER
                System.out.println("Filter By DOORNUMBER");
                System.out.println("Insert minimum DOOR Number:  [Numerical-inclusive]");
                choiceMinInt = Operations.getValidNumberInput(0);
                System.out.println("Insert maximum DOOR Number:  [Numerical-inclusive]");
                choiceMaxInt = Operations.getValidNumberInput(0);
                carList = filterService.searchByDoorNumber(carList, choiceMinInt, choiceMaxInt);
                break;

            case "color":
                //FILTER BY COLOR
                System.out.println("Filter by COLOR:");
                System.out.println("COLOR: White, Red, Blue");
                String color = sc.nextLine();
                carList = filterService.searchByColor(carList, color);
                break;

            case "ID":
                System.out.println("Filter by ID of the car:  [only Numericals]");
                String ID = sc.nextLine();
                carList = filterService.searchByID(carList, ID);
                break;

            case "VIN":
                System.out.println("Filter by VIN of the car:");
                String VIN = sc.nextLine();
                carList = filterService.searchByVIN(carList, VIN);
                break;

            default:
                System.out.println("error at shop.selectMenuOption, invalid filterType");
                break;
        }

        printingService.printCarList(carList, currentUser);
        currentCar = selectCarFromList(carList);
        if (currentCar != null) {
            printingService.printSingleCar(currentCar, currentUser);
            rentingService.rentSubMenu(currentCar, currentUser, maximumRentalDays, maximumRentalDaysInFuture, rentedCarList, pendingTransactions);
        }
    }



    public Car selectCarFromList(List<Car> carList) {
        boolean isValid = false;
        int choiceInt = -1;

        if (carList == null || carList.isEmpty()) {
            System.out.println("selectCarFromList received null carList");
            return null;
        } else {
            do {
                System.out.println("Please select the index:      [or Type S for Search, or Type N for Main Menu ]");
                String choice = sc.nextLine();
                if (choice.equalsIgnoreCase("s")) {
                    return showSearchMenuOptions(carList);
                } else if (choice.equalsIgnoreCase("n")) {
                    return null;
                } else {
                    try {
                        choiceInt = Integer.parseInt(choice);
                        //TODO ??? <= carlist-1??
                        if (choiceInt >= 0 && choiceInt <= (carList.size() - 1)) {
                            isValid = true;
                        } else {
                            System.out.println("The index you chose doesn't exist !!");
                        }
                    } catch (Exception E) {
                        System.out.println("Error at input from selectCarFromList");
                        isValid = false;
                    }
                }
            } while (!isValid);
        }
        return carList.get(choiceInt);
    }



//


    public ArrayList<Car> getAllCarList() {
//        System.out.println("Rented cars:");
//        for (int i = 0; i < allCarsList.size(); i++) {
//            if (allCarsList.get(i).isRented) {
//                System.out.println(i + ". " + allCarsList.get(i));
//            }
//        }
//        System.out.println("\nAvailable cars:");
//        for (int i = 0; i < allCarsList.size(); i++) {
//            if (!allCarsList.get(i).isRented) {
//                System.out.println(i + ". " + allCarsList.get(i));
//            }
//        }
        return allCarsList;
    }

    public ArrayList<Car> getAvailableCars() {
        // show only AVAILABLE CARS, not rented ones.
        ArrayList<Car> carList = new ArrayList<>();
        for (int i = 0; i < allCarsList.size(); i++) {
            if (!allCarsList.get(i).isRented) {
                carList.add(allCarsList.get(i));
            }
        }
        return carList;
    }

    //TODO what to do after?
    public List<Car> getRentedCars() {
        // show only RENTED CARS
        ArrayList<Car> carList = new ArrayList<>();
        LocalDate returnDate = null;

        for (Car car : allCarsList) {
            if (car.isRented) {
                carList.add(car);
                int lastIndex = carList.size() - 1;
                for (RentedCarHistoryItem carItem : rentedCarList) {
                    if (carItem.car.id == carList.get(lastIndex).id) {
                        returnDate = carItem.returnDate;
                    }
                }
                //Displaying available date + the car
                System.out.println(lastIndex + ". Available: " + returnDate.plusDays(1) + ", " + carList.get(lastIndex));
            }

        }
        return carList;
    }


}
