package org.yearup.data;

import org.yearup.models.SalesContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlSalesContractDao implements SalesDao
{
    private DataSource dataSource;

    public MySqlSalesContractDao(DataSource dataSource) { this.dataSource = dataSource; }

    @Override
    public SalesContract create(SalesContract salesContract)
    {
        String sql = """
        INSERT INTO sales_contracts
        (
            vin,
            customer_name,
            customer_email,
            sales_price,
            recording_fee,
            processing_fee,
            sales_tax
        )
        VALUES (?, ?, ?, ?, ?, ?, ?)                            
        """;

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        )
        {
            statement.setString(1,salesContract.getVin());
            statement.setString(2,salesContract.getCustomerName());
            statement.setString(3, salesContract.getCustomerEmail());
            statement.setBigDecimal(4, salesContract.getSalesPrice());
            statement.setBigDecimal(5,salesContract.getRecordingFee());
            statement.setBigDecimal(6,salesContract.getProcessingFee());
            statement.setBigDecimal(7,salesContract.getSalesTax());

            statement.executeUpdate();

        }
        catch (SQLException e)
        {

        }
        return salesContract;
    }


   /* @Override
    public List<SalesContract> getByVin(String vin)
    {
        List<SalesContract> salesContracts = new ArrayList<>();

        String sql = """
                INSERT INTO sales_contracts (vin, customer_name, customer_email, sales_price, recording_fee
                                             , processing_fee, sales_tax)
                SELECT ?, ?                              
                """;
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        )
        {
            statement.setString((2,salesContracts.g));
            statement.setString(1,salesContracts.());
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

    */
}
