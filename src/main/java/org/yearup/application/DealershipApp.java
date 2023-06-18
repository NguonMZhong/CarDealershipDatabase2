package org.yearup.application;

import org.yearup.data.MySqlVehicleDao;
import org.yearup.data.VehicleDao;
import org.yearup.models.Vehicle;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class DealershipApp
{
    private final Scanner userInput = new Scanner(System.in);

    private VehicleDao vehicleDao;

    public DealershipApp(VehicleDao vehicleDao) { this.vehicleDao = vehicleDao; }
    public void run()
    {
        while(true)
        {
            System.out.println();
            int choice = getSelection();

            switch(choice)
            {
                case 1:
                    DisplayByPrice();
                    break;
                case 2:
                    DisplayByMakeModel();
                    break;
                case 3:
                    DisplayByYear();
                    break;
                case 4:
                    DisplayByColor();
                    break;
                case 5:
                    DisplaybyMileage();
                    break;
                case 6:
                    DisplayAllVehicles();
                    break;
                case 0:
                    System.out.println("Exit");
                    return;
                default:
                    System.out.println("Not a valid selection.");
            }
        }
    }

    private int getSelection()
    {
        System.out.println();
        System.out.println("What do you want to do?");
        System.out.println(" 1) Search by price range");
        System.out.println(" 2) Search by make/model");
        System.out.println(" 3) Search by year range");
        System.out.println(" 4) Search by color");
        System.out.println(" 5) Search by mileage range");
        System.out.println(" 6) Display all vehicles");
        System.out.println(" 7) Add a vehicle");
        System.out.println(" 8) Remove a vehicle");
        System.out.println(" 9) Sale a vehicle");
        System.out.print("Enter: ");

        int choice = userInput.nextInt();
        userInput.nextLine();

        return choice;
    }

    private void DisplayByPrice()
    {
        System.out.println();
        System.out.println("Enter the price range.");
        System.out.print("Minimum: ");
        BigDecimal minPrice = userInput.nextBigDecimal();
        System.out.print("Maximum: ");
        BigDecimal maxPrice = userInput.nextBigDecimal();

        var vehicles = vehicleDao.getByPrice(minPrice,maxPrice);
        System.out.println();

        for(var vehicle : vehicles)
        {
            System.out.printf("%-15s %-15s %8.2f%n",vehicle.getMake(),vehicle.getModel(), vehicle.getPrice());

        }
    }

    private void DisplayByMakeModel()
    {
        System.out.println();
        System.out.print("Enter the make: ");
        String make = userInput.nextLine();

        System.out.print("Enter the model: ");
        String model = userInput.nextLine();

        var vehicles = vehicleDao.getByMakeModel(make,model);
        System.out.println();

        for(var vehicle : vehicles)
        {
            System.out.printf("%-15s %-15s%n", vehicle.getMake(), vehicle.getModel());
        }

    }

    private void DisplayByYear()
    {
        System.out.println();
        System.out.print("Enter the lowest year: ");
        int minYear = userInput.nextInt();

        System.out.print("Enter the highest year: ");
        int maxYear = userInput.nextInt();

        var vehicles = vehicleDao.getByYear(minYear,maxYear);
        System.out.println();

        for(var vehicle : vehicles)
        {
            System.out.printf("%-15s %-15s %-15d%n", vehicle.getMake(),vehicle.getModel(),vehicle.getYear());
        }
    }

    private void DisplayByColor()
    {
        System.out.println();
        System.out.print("Enter color: ");
        String vehicleColor = userInput.nextLine();

        var vehicles = vehicleDao.getByColor(vehicleColor);
        System.out.println();

        for(var vehicle : vehicles)
        {
            System.out.printf("%-15s %-15s %-15s%n",vehicle.getMake(),vehicle.getModel(),vehicle.getColor());
        }

    }

    private void DisplaybyMileage()
    {
        System.out.println();
        System.out.print("Enter the lowest mileage: ");
        int minMileage = userInput.nextInt();

        System.out.print("Enter the highest mileage: ");
        int maxMileage = userInput.nextInt();

        var vehicles = vehicleDao.getByMileage(minMileage,maxMileage);
        System.out.println();

        if (vehicles.isEmpty())
        {
            System.out.println("No vehicles found for the given mileage range");
        }
        for(var vehicle : vehicles)
        {
            System.out.printf("%-15s %-15s %-15d%n",vehicle.getMake(),vehicle.getModel(),vehicle.getMiles());
        }

    }

    private void DisplayAllVehicles()
    {
        System.out.println();
        System.out.println("Displaying all vehicles...");

        var vehicles = vehicleDao.getAllVehicle();
        System.out.println();

        for(var vehicle : vehicles)
        {
            System.out.printf("%-15s %-15s %-15s %-15s %-15d %-15d %8.2f%n", vehicle.getVin()
                    ,vehicle.getMake(),vehicle.getModel(),vehicle.getColor(),vehicle.getYear()
                    ,vehicle.getMiles(),vehicle.getPrice());
        }

    }

}
