package ru.c_energies.databases;



import ru.c_energies.databases.sqlite.Source;

import java.io.*;
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

    /**
     * Вставка строки в таблицу по запросу. В запросе INSERT должно быть обязательно прописано RETURNING rowid
     */
    public void insert(){
        PreparedStatement p;
        ResultSet rs;
        try {
            p = this.datasource.session().prepareStatement(this.query);
            rs = p.executeQuery();
            while(rs.next()){

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void update(){
        PreparedStatement p;
        try {
            p = this.datasource.session().prepareStatement(this.query);
            p.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public int updateBlob(int id, byte[] content){
        PreparedStatement p;
        ResultSet rs;
        try {
            p = this.datasource.session().prepareStatement(this.query);
            p.setBytes(1, content);
            p.setInt(2, id);
            int num = p.executeUpdate();
            return num;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public byte[] content(){
        PreparedStatement p;
        ResultSet rs;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            p = this.datasource.session().prepareStatement(this.query); //"SELECT SYSDATE FROM dual"
            rs = p.executeQuery();
              while(rs.next()){
                InputStream input = rs.getBinaryStream("content");
                byte[] buffer = new byte[1024];
                while (input.read(buffer) > 0) {
                    out.write(buffer);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return out.toByteArray();
    }
}
