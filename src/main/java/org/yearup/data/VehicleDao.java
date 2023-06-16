package org.yearup.data;

import org.yearup.models.Vehicle;

import java.math.BigDecimal;
import java.util.List;

public interface VehicleDao
{
    List<Vehicle> getByPrice(BigDecimal minPrice, BigDecimal maxPrice);

    List<Vehicle> getByMakeModel(String make, String model);


}
