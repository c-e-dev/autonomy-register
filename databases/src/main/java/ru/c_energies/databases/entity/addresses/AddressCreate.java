package ru.c_energies.databases.entity.addresses;

import ru.c_energies.databases.Query;
import ru.c_energies.databases.entity.Create;
import ru.c_energies.databases.sqlite.SqliteDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressCreate implements Create<AddressCreate> {
    private final String recipient;
    private final String address;
    private int id;
    public AddressCreate(String recipient, String address){
        this.recipient = recipient;
        this.address = address;
    }
    @Override
    public AddressCreate insert() throws SQLException {
        String sql = String.format("""
                    INSERT INTO addresses (recipient, address, actually) VALUES('%s', '%s', 1) RETURNING rowid
                """, this.recipient, this.address);
        Query query = new Query(new SqliteDataSource(), sql);
        ResultSet resultSet = query.exec();
        while(resultSet.next()){
            this.id = resultSet.getInt("id");
        }
        return this;
    }

    @Override
    public void update() {

    }

    public int id(){
        return this.id;
    }
}
