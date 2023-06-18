package org.yearup.data;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import org.yearup.models.Vehicle;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.math.BigDecimal;
import java.sql.*;
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
    public List<Vehicle> getByMakeModel(String make, String model)
    {
        List<Vehicle> vehicles = new ArrayList<>();

        String sql = "SELECT make, model" +
                     " FROM vehicles" +
                     " WHERE make = ? AND model = ?;";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        )
        {
            statement.setString(1,make);
            statement.setString(2,model);

            ResultSet row = statement.executeQuery();

            while(row.next())
            {
                String vehicleMake = row.getString("make");
                String vehicleModel = row.getString("model");

                Vehicle vehicle = new Vehicle()
                {{
                    setMake(vehicleMake);
                    setModel(vehicleModel);
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
    public List<Vehicle> getByYear(int minYear, int maxYear)
    {
        List<Vehicle> vehicles = new ArrayList<>();

        String sql = "SELECT make, model, year" +
                     " FROM vehicles" +
                     " WHERE year BETWEEN ? AND ?;";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        )
        {
            statement.setInt(1,minYear);
            statement.setInt(2, maxYear);

            ResultSet row = statement.executeQuery();

            while(row.next())
            {
                String make = row.getString("make");
                String model = row.getString("model");
                int year = row.getInt("year");

                Vehicle vehicle = new Vehicle()
                {{
                    setMake(make);
                    setModel(model);
                    setYear(year);
                }};

                vehicles.add(vehicle);
            }

        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        return  vehicles;
    }

    @Override
    public List<Vehicle> getByColor(String vehicleColor)
    {
        List<Vehicle> vehicles = new ArrayList<>();

        String sql = " SELECT make, model, color" +
                     " FROM vehicles" +
                     " WHERE color = ?;";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        )
        {
            statement.setString(1,vehicleColor);

            ResultSet row = statement.executeQuery();

            while(row.next())
            {
                String make = row.getString("make");
                String model = row.getString("model");
                String color = row.getString("color");

                Vehicle vehicle = new Vehicle()
                {{
                    setMake(make);
                    setModel(model);
                    setColor(color);
                }};
                vehicles.add(vehicle);
            }
        }
        catch(SQLException e)
        {
            throw new RuntimeException(e);
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> getByMileage(int minMileage, int maxMileage)
    {
        List<Vehicle> vehicles = new ArrayList<>();

        String sql = " SELECT make, model, miles" +
                     " FROM vehicles" +
                     " WHERE miles BETWEEN ? AND ?;";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        )
        {
            statement.setInt(1,minMileage);
            statement.setInt(2,maxMileage);

            ResultSet row = statement.executeQuery();

            while(row.next())
            {
                String make = row.getString("make");
                String model = row.getString("model");
                int miles = row.getInt("miles");

                Vehicle vehicle = new Vehicle()
                {{
                    setMake(make);
                    setModel(model);
                    setMiles(miles);
                }};
                vehicles.add(vehicle);
            }
        }
        catch(SQLException e)
        {
            throw new RuntimeException(e);
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> getAllVehicle()
    {
        List<Vehicle> vehicles = new ArrayList<>();

        String sql = "SELECT * FROM vehicles";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        )
        {
            ResultSet row = statement.executeQuery();

            while(row.next())
            {
                String vin = row.getString("vin");
                String make = row.getString("make");
                String model = row.getString("model");
                String color = row.getString("color");
                int year = row.getInt("year");
                int miles = row.getInt("miles");
                BigDecimal price = row.getBigDecimal("price");

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
    public Vehicle create(Vehicle vehicle)
    {
        String sql = """
                INSERT INTO vehicles
                (
                    vin
                    ,make
                    ,model
                    ,color
                    ,year
                    ,miles
                    ,price
                )
                VALUES (?, ?, ?, ?, ?, ?, ?);
                """;
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
                )
        {
            statement.setString(1,vehicle.getVin());
            statement.setString(2,vehicle.getMake());
            statement.setString(3,vehicle.getModel());
            statement.setString(4, vehicle.getColor());
            statement.setInt(5,vehicle.getYear());
            statement.setInt(6,vehicle.getMiles());
            statement.setBigDecimal(7,vehicle.getPrice());

            statement.executeUpdate();

        }
        catch (SQLException e)
        {

        }
        return null;
    }

    @Override
    public void delete(String vin)
    {
        String sql = """
                DELETE FROM vehicles
                WHERE vin = ?;
                """;

        try ( Connection connection = dataSource.getConnection();
              PreparedStatement statement = connection.prepareStatement(sql)
        )
        {
            statement.setString(1,vin);

            statement.executeUpdate();
        }
        catch (SQLException e)
        {

        }

    }
}
