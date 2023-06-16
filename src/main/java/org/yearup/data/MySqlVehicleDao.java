package org.yearup.data;

import org.yearup.models.Vehicle;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlVehicleDao implements VehicleDao
{
    private DataSource dataSource;

    public MySqlVehicleDao(DataSource dataSource) { this.dataSource = dataSource; }
    @Override
    public List<Vehicle> getByPrice(BigDecimal minPrice, BigDecimal maxPrice)
    {
        List<Vehicle> vehicles = new ArrayList<>();

        String sql = "SELECT make, model, price" +
                     " FROM vehicles " +
                     " WHERE price BETWEEN ? AND ?;";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        )
        {
            statement.setBigDecimal(1, minPrice);
            statement.setBigDecimal(2,maxPrice);

            ResultSet row = statement.executeQuery();

            while(row.next())
            {
                String make = row.getString("make");
                String model = row.getString("model");
                BigDecimal price = row.getBigDecimal("price");

                Vehicle vehicle = new Vehicle()
                {{
                    setMake(make);
                    setModel(model);
                    setPrice(price);
                }};

                vehicles.add(vehicle);

            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        return vehicles;
    }

    @Override
    public Vehicle getByMakeModel()
    {
        return null;
    }
}
