package org.yearup;

import org.apache.commons.dbcp2.BasicDataSource;
import org.yearup.application.DealershipApp;
import org.yearup.data.*;

public class Main
{
    public static void main(String[] args)
    {
        String baseUrl = "jdbc:mysql://localhost:3306/car_dealership";
        String username = "root";
        String password = "P@ssw0rd";

        BasicDataSource dataSource = new BasicDataSource()
        {{
            setUrl(baseUrl);
            setUsername(username);
            setPassword(password);
        }};

        VehicleDao vehicleDao = new MySqlVehicleDao(dataSource);
        SalesDao salesDao = new MySqlSalesContractDao(dataSource);
        LeaseDao leaseDao = new MySqlLeaseContractDao(dataSource);

        DealershipApp app = new DealershipApp(vehicleDao,salesDao,leaseDao);
        app.run();
    }
}