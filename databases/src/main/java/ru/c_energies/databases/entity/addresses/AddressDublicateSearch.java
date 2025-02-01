package ru.c_energies.databases.entity.addresses;

import ru.c_energies.databases.Query;
import ru.c_energies.databases.sqlite.SqliteDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDublicateSearch {
    private final String recipient;
    private final String address;
    private int addressId;
    public AddressDublicateSearch(String recipient, String address){
        this.recipient = recipient;
        this.address = address;
    }
    public boolean foundable()  throws SQLException {
        String sql = String.format("""
                    select id from addresses a where recipient = '%s' AND address = '%s'
                """, this.recipient, this.address);
        ResultSet resultSet = new Query(new SqliteDataSource(), sql).exec();
        int result = 0;
        while(resultSet.next()){
            result++;
            this.addressId = resultSet.getInt("id");
        }
        if(result > 0){
            return true;
        }
        return false;
    }
    public int id(){
        return this.addressId;
    }
}
