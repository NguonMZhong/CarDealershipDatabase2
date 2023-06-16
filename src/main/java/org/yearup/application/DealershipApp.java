package org.yearup.application;

import org.yearup.data.MySqlVehicleDao;
import org.yearup.data.VehicleDao;
import org.yearup.models.Vehicle;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class DealershipApp
{
    private Scanner userInput = new Scanner(System.in);

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
                case 3:
                    //getByYear();
                    break;
                case 4:
                    //getByColor();
                    break;
                case 5:
                    //getbyMileage();
                    break;
                case 6:
                    //getByType();
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
        System.out.println(" 6) Search by vehicle type");
        System.out.println(" 7) List all vehicles");
        System.out.println(" 8) Add a vehicle");
        System.out.println(" 9) Remove a vehicle");
        System.out.println(" 10) Sale a vehicle");
        System.out.print("Enter: ");

        return userInput.nextInt();
    }

    private void DisplayByPrice()
    {
        System.out.println();
        System.out.println("Enter the price range:");
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
        
    }
}
