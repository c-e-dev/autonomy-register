package ru.c_energies.databases;



import ru.c_energies.databases.sqlite.Source;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query {
    private final Source<Connection> datasource;
    private final String query;
    public Query(Source<Connection> datasource, String query){
        this.datasource = datasource;
        this.query = query;
    }
    public ResultSet exec(){
        PreparedStatement p;
        ResultSet rs;
        try {
            p = this.datasource.session().prepareStatement(this.query); //"SELECT SYSDATE FROM dual"
            rs = p.executeQuery();
            return rs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
