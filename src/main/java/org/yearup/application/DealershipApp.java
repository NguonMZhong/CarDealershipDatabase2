package org.yearup.application;

import org.yearup.data.LeaseDao;
import org.yearup.data.MySqlVehicleDao;
import org.yearup.data.SalesDao;
import org.yearup.data.VehicleDao;
import org.yearup.models.SalesContract;
import org.yearup.models.Vehicle;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class DealershipApp
{
    private final Scanner userInput = new Scanner(System.in);

    private VehicleDao vehicleDao;

    private SalesDao salesDao;

    private LeaseDao leaseDao;

    public DealershipApp(VehicleDao vehicleDao, SalesDao salesDao, LeaseDao leaseDao)
    {
        this.vehicleDao = vehicleDao;
        this.salesDao = salesDao;
        this.leaseDao = leaseDao;


    }
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
                case 7:
                    addVehicle();
                    break;
                case 8:
                    deleteVehicle();
                    break;
                case 9:
                    addSalesContract();
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
        System.out.println(" 10) Lease a vehicle");
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

    private void addVehicle()
    {
        System.out.println();
        System.out.print("Enter the vehicle vin: ");
        String vin = userInput.nextLine().strip();

        System.out.print("Enter the vehicle make: ");
        String make = userInput.nextLine().strip();

        System.out.print("Enter the vehicle model: ");
        String model = userInput.nextLine().strip();

        System.out.print("Enter the vehicle color: ");
        String color = userInput.nextLine().strip();

        System.out.print("Enter the vehicle year: ");
        int year = userInput.nextInt();

        System.out.print("Enter the vehicle mileage: ");
        int miles = userInput.nextInt();

        System.out.print("Enter the vehicle price: ");
        BigDecimal price = userInput.nextBigDecimal();

        Vehicle vehicle = new Vehicle()
        {{
            setVin(vin);
            setMake(make);
            setModel(model);
            setColor(color);
            setYear(year);
            setMiles(miles);
            setPrice(price);
        }};

        var newVehicle = vehicleDao.create(vehicle);

        System.out.println();
        System.out.println("Vehicle has been added.\n");


        System.out.printf("%-15s %-15s %-15s %-15s %-15d %-15d %8.2f%n", vehicle.getVin()
                    ,vehicle.getMake(),vehicle.getModel(),vehicle.getColor(),vehicle.getYear()
                    ,vehicle.getMiles(),vehicle.getPrice());

    }

    private void deleteVehicle() {
        System.out.println();
        System.out.print("Enter the vehicle VIN: ");
        String vin = userInput.nextLine().strip();

        Vehicle vehicleToDelete = null;

        // Fetch the vehicle details from the database
        List<Vehicle> vehicles = vehicleDao.getAllVehicle();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVin().equalsIgnoreCase(vin)) {
                vehicleToDelete = vehicle;
                break;
            }
        }

        if (vehicleToDelete == null) {
            System.out.println("Vehicle with VIN " + vin + " does not exist.");
        } else {
            String vehicleDetails = String.format("%-15s %-15s %-15s %-15s %-15d %8.2f%n",
                    vehicleToDelete.getVin(), vehicleToDelete.getMake(), vehicleToDelete.getModel(),
                    vehicleToDelete.getColor(), vehicleToDelete.getMiles(), vehicleToDelete.getPrice());

            System.out.println("Vehicle details: " + vehicleDetails);

            System.out.println();
            System.out.print("Are you sure you want to delete this vehicle? (y/n): ");
            String answer = userInput.nextLine().strip();

            if (answer.equalsIgnoreCase("y")) {
                vehicleDao.delete(vin);
                System.out.println(vin + " has been deleted.");
            }
        }
    }

    private void addSalesContract() {
        System.out.println();
        System.out.print("Enter the vehicle VIN: ");
        String vin = userInput.nextLine().strip();

        BigDecimal salesPrice = null;
        List<Vehicle> vehicles = vehicleDao.getByVin(vin);
        if (!vehicles.isEmpty()) {
            Vehicle selectedVehicle = vehicles.get(0);
            salesPrice = selectedVehicle.getPrice();
            System.out.println();
            System.out.println("Selected vehicle:");
            System.out.printf("%-15s %-15s %-15s %-15s %-15d %-15d %8.2f%n",
                    selectedVehicle.getVin(), selectedVehicle.getMake(), selectedVehicle.getModel(),
                    selectedVehicle.getColor(), selectedVehicle.getYear(), selectedVehicle.getMiles(),
                    salesPrice);
        } else {
            System.out.println("Vehicle with VIN " + vin + " does not exist.");
            return;
        }

        System.out.println();
        System.out.print("Enter your first and last name: ");
        String name = userInput.nextLine().strip();

        System.out.print("Enter your email: ");
        String email = userInput.nextLine().strip();

        BigDecimal recordingFee = BigDecimal.valueOf(100);
        BigDecimal processingFee = salesPrice.compareTo(BigDecimal.valueOf(10000)) < 0 ?
                BigDecimal.valueOf(295) : BigDecimal.valueOf(495);
        BigDecimal salesTax = salesPrice.multiply(BigDecimal.valueOf(0.05));

        SalesContract salesContract = new SalesContract();
        salesContract.setVin(vin);
        salesContract.setCustomerName(name);
        salesContract.setCustomerEmail(email);
        salesContract.setSalesPrice(salesPrice);
        salesContract.setRecordingFee(recordingFee);
        salesContract.setProcessingFee(processingFee);
        salesContract.setSalesTax(salesTax);

        SalesContract newContract = salesDao.create(salesContract);

        if (newContract != null) {

            boolean updated = vehicleDao.update(vin, true);

            if (updated) {
                System.out.println();
                System.out.println("Sales Contract has been added and vehicle marked as sold.");
                System.out.println();

                System.out.printf("%-15s %-15s %-15s %8.2f %8.2f %8.2f %8.2f%n",
                        newContract.getVin(), newContract.getCustomerName(),
                        newContract.getCustomerEmail(), newContract.getSalesPrice(),
                        newContract.getRecordingFee(), newContract.getProcessingFee(),
                        newContract.getSalesTax());
            } else {
                System.out.println("Failed to mark the vehicle as sold.");
            }
        } else {
            System.out.println("Failed to add the Sales Contract.");
        }
    }

}
