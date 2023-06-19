package org.yearup.data;

import javax.sql.DataSource;

public class MySqlLeaseContractDao implements LeaseDao
{
    private DataSource dataSource;
    public MySqlLeaseContractDao(DataSource dataSource) { this.dataSource = dataSource; }
}
