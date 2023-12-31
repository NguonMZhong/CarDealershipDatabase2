package org.yearup.data;

import org.yearup.models.Vehicle;

import java.math.BigDecimal;
import java.util.List;

public interface VehicleDao
{
    List<Vehicle> getByVin(String vehicleVin);
    List<Vehicle> getByPrice(BigDecimal minPrice, BigDecimal maxPrice);

    List<Vehicle> getByMakeModel(String make, String model);

    List<Vehicle> getByYear(int minYear, int maxYear);

    List<Vehicle> getByColor(String vehicleColor);

    List<Vehicle> getByMileage(int minMileage, int maxMileage);

    List<Vehicle> getAllVehicle();

    // create vehicle
    Vehicle create(Vehicle vehicle);

    // delete vehicle
    void delete(String vin);
    boolean update(String vin, boolean sold);


}
