package ro.jademy.carrental.services;

import org.apache.commons.lang3.StringUtils;
import ro.jademy.carrental.models.Client;
import ro.jademy.carrental.models.Salesman;
import ro.jademy.carrental.models.User;
import ro.jademy.carrental.models.cars.Car;
// import org.apache.commons.lang3.StringUtils;


import java.util.*;


public class FilterService {
    Scanner sc = new Scanner(System.in);


    // Function to remove duplicates from an ArrayList
    public static List<Car> removeDuplicates(List<Car> list) {
        // Create a new LinkedHashSet
        Set<Car> set = new LinkedHashSet<>();

        // Add the elements to set
        set.addAll(list);

        // Clear the list
        list.clear();

        // add the elements of set
        // with no duplicates to the list
        list.addAll(set);

        return list;
    }


    public List<Car> combinedSearch(User user, List<Car> carList, String input) {
        List<Car> tempCarList = new ArrayList<>();
        List<Car> subList = new ArrayList<>();


        String[] inputList = input.split(" ");


        for (String item : inputList) {
            subList.clear();

            // if contains MARGIN CHAR "-"
            if (item.contains("-")) {
                String[] marginArray = item.split("-");
                System.out.println("Margin array is:" + marginArray.toString());

                //IF IS WHOLE NUMBER
                if (marginArray.length > 1
                        && StringUtils.isNumeric(marginArray[0])
                        && StringUtils.isNumeric(marginArray[1])) {

                    int itemNrMin = Integer.parseInt(marginArray[0]);
                    int itemNrMax = Integer.parseInt(marginArray[1]);

                    //proofing for MIN-input and MAX-input
                    if (itemNrMin > itemNrMax) {
                        int temp = 0;
                        temp = itemNrMax;
                        itemNrMax = itemNrMin;
                        itemNrMin = temp;
                    }

                    //SEARCH by YEAR with MARGIN
                    if (itemNrMin > 1900 && itemNrMax > 1900) {
                        subList = (searchByYear(carList, itemNrMin, itemNrMax));
                        if (tempCarList.isEmpty()) {
                            tempCarList.addAll(subList);
                        }
                        if (!subList.isEmpty()) {
                            tempCarList.retainAll(subList);
                        }
                    }

                    subList = (searchByPrice(user, carList, itemNrMin, itemNrMax));
                    if (tempCarList.isEmpty()) {
                        tempCarList.addAll(subList);
                    }
                    if (!subList.isEmpty()) {
                        tempCarList.retainAll(subList);
                    }

                    subList = (searchByDoorNumber(carList, (int) itemNrMin, (int) itemNrMax));
                    if (tempCarList.isEmpty()) {
                        tempCarList.addAll(subList);
                    }
                    if (!subList.isEmpty()) {
                        tempCarList.retainAll(subList);
                    }
                }

                //If MARGIN exists and contains "." and IS DOUBLE
                if (marginArray.length > 1
                        && (marginArray[0]).contains(".")
                        && (marginArray[1]).contains(".")) {

                    try {
                        double itemNrMin = Double.parseDouble(marginArray[0]);
                        double itemNrMax = Double.parseDouble(marginArray[1]);

                        //proofing for MIN-input and MAX-input
                        if (itemNrMin > itemNrMax) {
                            double temp = 0;
                            temp = itemNrMax;
                            itemNrMax = itemNrMin;
                            itemNrMin = temp;
                        }

                        //SEARCH by ENGINE
                        subList = (searchByEngine(carList, ((int) itemNrMin * 1000), ((int) itemNrMax * 1000)));
                        if (tempCarList.isEmpty()) {
                            tempCarList.addAll(subList);
                        }
                        if (!subList.isEmpty()) {
                            tempCarList.retainAll(subList);
                        }
                    } catch (Exception E) {
                    }

                }


                // if it DOESN'T contain MARGIN char "-"
            } else {
                // If DOUBLE number
                if (item.contains(".")) {
                    try {
                        double itemNr = Double.parseDouble(item);
                        //SEARCH by ENGINE
                        subList = (searchByEngine(carList, ((int) itemNr * 1000), ((int) itemNr * 1000)));
                        if (tempCarList.isEmpty()) {
                            tempCarList.addAll(subList);
                        }
                        if (!subList.isEmpty()) {
                            tempCarList.retainAll(subList);
                        }
                    } catch (Exception E) {
                    }
                }

                // If WHOLE Number
                if (StringUtils.isNumeric(item)) {
                    int itemNr = Integer.parseInt(item);

                    if (itemNr > 1900) {
                        subList = (searchByYear(carList, itemNr, itemNr));
                        if (tempCarList.isEmpty()) {
                            tempCarList.addAll(subList);
                        }
                        if (!subList.isEmpty()) {
                            tempCarList.retainAll(subList);
                        }
                    }

                    subList = (searchByPrice(user, carList, itemNr, itemNr));
                    if (tempCarList.isEmpty()) {
                        tempCarList.addAll(subList);
                    }
                    if (!subList.isEmpty()) {
                        tempCarList.retainAll(subList);
                    }

                    subList = (searchByDoorNumber(carList, itemNr, itemNr));
                    if (tempCarList.isEmpty()) {
                        tempCarList.addAll(subList);
                    }
                    if (!subList.isEmpty()) {
                        tempCarList.retainAll(subList);
                    }
                }
            }

            if (subList.isEmpty()) {
                //search by strings
                subList = (searchByMake(carList, item));
                if (tempCarList.isEmpty()) {
                    tempCarList.addAll(subList);
                }
                if (!subList.isEmpty()) {
                    tempCarList.retainAll(subList);
                }

                subList = (searchByModel(carList, item));
                if (tempCarList.isEmpty()) {
                    tempCarList.addAll(subList);
                }
                if (!subList.isEmpty()) {
                    tempCarList.retainAll(subList);
                }

                subList = (searchByVariant(carList, item));
                if (tempCarList.isEmpty()) {
                    tempCarList.addAll(subList);
                }
                if (!subList.isEmpty()) {
                    tempCarList.retainAll(subList);
                }

                subList = (searchByCarType(carList, item));
                if (tempCarList.isEmpty()) {
                    tempCarList.addAll(subList);
                }
                if (!subList.isEmpty()) {
                    tempCarList.retainAll(subList);
                }

                subList = (searchByFuelType(carList, item));
                if (tempCarList.isEmpty()) {
                    tempCarList.addAll(subList);
                }
                if (!subList.isEmpty()) {
                    tempCarList.retainAll(subList);
                }

                subList = (searchByTransmission(carList, item));
                if (tempCarList.isEmpty()) {
                    tempCarList.addAll(subList);
                }
                if (!subList.isEmpty()) {
                    tempCarList.retainAll(subList);
                }

                subList = (searchByColor(carList, item));
                if (tempCarList.isEmpty()) {
                    tempCarList.addAll(subList);
                }
                if (!subList.isEmpty()) {
                    tempCarList.retainAll(subList);
                }

                subList = (searchByVIN(carList, item));
                if (tempCarList.isEmpty()) {
                    tempCarList.addAll(subList);
                }
                if (!subList.isEmpty()) {
                    tempCarList.retainAll(subList);
                }
            }


        }
        return tempCarList;
    }

    public List<Car> searchByPrice(User user, List<Car> carList, int minPrice, int maxPrice) {
        List<Car> tempCarList = new ArrayList<>();
        if (user instanceof Client) {
            double rentalCoeff = ((Client) user).rentalCoeff;
            for (Car car : carList) {
                // +1 , -1 is margin for double to long transform.
                if ((car.basePrice * rentalCoeff + 1) >= minPrice
                        && (car.basePrice * rentalCoeff - 1) <= maxPrice) {
                    tempCarList.add(car);
                }
            }

        }
        if (user instanceof Salesman) {
            System.out.println("Using salesman basePrice search:");
            for (Car car : carList) {
                if (car.basePrice >= minPrice
                        && car.basePrice <= maxPrice) {
                    tempCarList.add(car);

                }
            }


        }

        if (tempCarList.isEmpty()) {
            System.out.println("Couldn't find any results for your SEARCH PRICE");
            return tempCarList;
        } else {
            return tempCarList;
        }
    }

    public List<Car> searchByMake(List<Car> carList, String make) {
        List<Car> tempCarList = new ArrayList<>();

        for (Car car : carList) {
            if (car.make.toLowerCase().contains(make.toLowerCase())) {
                tempCarList.add(car);
            }
        }

        if (tempCarList.isEmpty()) {
            System.out.println("Could find any results for your SEARCH MAKE");
            return tempCarList;
        } else {
            return tempCarList;
        }
    }

    public List<Car> searchByModel(List<Car> carList, String model) {
        List<Car> tempCarList = new ArrayList<>();

        for (Car car : carList) {
            if (car.model.toLowerCase().contains(model.toLowerCase())) {
                tempCarList.add(car);
            }
        }

        if (tempCarList.isEmpty()) {
            System.out.println("Could find any results for your SEARCH MODEL");
            return tempCarList;
        } else {
            return tempCarList;
        }
    }

    public List<Car> searchByVariant(List<Car> carList, String variant) {
        List<Car> tempCarList = new ArrayList<>();

        for (Car car : carList) {
            if (car.variant.toLowerCase().contains(variant.toLowerCase())) {
                tempCarList.add(car);
            }
        }

        if (tempCarList.isEmpty()) {
            System.out.println("Could find any results for your SEARCH VARIANT");
            return tempCarList;
        } else {
            return tempCarList;
        }
    }

    public List<Car> searchByCarType(List<Car> carList, String carType) {
        List<Car> tempCarList = new ArrayList<>();

        for (Car car : carList) {
            if (car.carType.toLowerCase().contains(carType.toLowerCase())) {
                tempCarList.add(car);
            }
        }
        if (tempCarList.isEmpty()) {
            System.out.println("Could find any results for your SEARCH CAR-TYPE");
            return tempCarList;
        } else {
            return tempCarList;
        }
    }

    public List<Car> searchByYear(List<Car> carList, int choiceMinInt, int choiceMaxInt) {
        List<Car> tempCarList = new ArrayList<>();
        for (Car car : carList) {
            if (car.year >= choiceMinInt
                    && car.year <= choiceMaxInt) {
                tempCarList.add(car);
            }
        }
        if (tempCarList.isEmpty()) {
            System.out.println("Could find any results for your SEARCH YEAR");
            return tempCarList;
        } else {
            return tempCarList;
        }

    }

    public List<Car> searchByEngine(List<Car> carList, int choiceMinInt, int choiceMaxInt) {
        List<Car> tempCarList = new ArrayList<>();
        for (Car car : carList) {
            if (car.engine >= choiceMinInt
                    && car.engine <= choiceMaxInt) {
                tempCarList.add(car);
            }
        }
        if (tempCarList.isEmpty()) {
            System.out.println("Could find any results for your SEARCH ENGINE-SIZE");
            return tempCarList;
        } else {
            return tempCarList;
        }

    }

    public List<Car> searchByFuelType(List<Car> carList, String fuelType) {
        List<Car> tempCarList = new ArrayList<>();

        for (Car car : carList) {
            if (car.fuelType.toLowerCase().contains(fuelType.toLowerCase())) {
                tempCarList.add(car);
            }
        }
        if (tempCarList.isEmpty()) {
            System.out.println("Could find any results for your SEARCH FUEL-TYPE");
            return tempCarList;
        } else {
            return tempCarList;
        }

    }

    public List<Car> searchByTransmission(List<Car> carList, String transmissionType) {
        List<Car> tempCarList = new ArrayList<>();

        for (Car car : carList) {
            if (car.transmissionType.toLowerCase().contains(transmissionType.toLowerCase())) {
                tempCarList.add(car);
            }
        }
        if (tempCarList.isEmpty()) {
            System.out.println("Could find any results for your SEARCH TRANSMISSION");
            return tempCarList;
        } else {
            return tempCarList;
        }


    }

    public List<Car> searchByDoorNumber(List<Car> carList, int choiceMinInt, int choiceMaxInt) {
        List<Car> tempCarList = new ArrayList<>();
        for (Car car : carList) {
            if (car.doorNumber >= choiceMinInt
                    && car.doorNumber <= choiceMaxInt) {
                tempCarList.add(car);
            }
        }
        if (tempCarList.isEmpty()) {
            System.out.println("Could find any results for your SEARCH DOOR-NUMBER");
            return tempCarList;
        } else {
            return tempCarList;
        }

    }

    public List<Car> searchByColor(List<Car> carList, String color) {
        List<Car> tempCarList = new ArrayList<>();

        for (Car car : carList) {
            if (car.color.toLowerCase().contains(color.toLowerCase())) {
                tempCarList.add(car);
            }
        }
        if (tempCarList.isEmpty()) {
            System.out.println("Could find any results for your SEARCH COLOR");
            return tempCarList;
        } else {
            return tempCarList;
        }

    }

    public List<Car> searchByID(List<Car> carList, String ID) {
        List<Car> tempCarList = new ArrayList<>();
        int integerID = -1;
        try {
            integerID = Integer.parseInt(ID);
        } catch (Exception e) {
            System.out.println("Invalid input for ID:");
        }


        for (Car car : carList) {
            if (car.id == (integerID)) {
                tempCarList.add(car);
            }
        }
        if (tempCarList.isEmpty()) {
            System.out.println("Could find any results for your SEARCH ID");
            return tempCarList;
        } else {
            return tempCarList;
        }

    }

    public List<Car> searchByVIN(List<Car> carList, String VIN) {
        List<Car> tempCarList = new ArrayList<>();

        for (Car car : carList) {
            if (car.VIN.equals(VIN)) {
                tempCarList.add(car);
            }
        }
        if (tempCarList.isEmpty()) {
            System.out.println("Could find any results for your SEARCH VIN");
            return tempCarList;
        } else {
            return tempCarList;
        }

    }
}
