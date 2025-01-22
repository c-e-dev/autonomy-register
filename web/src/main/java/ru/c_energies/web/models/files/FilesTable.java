package ru.c_energies.web.models.files;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilesTable {
    private final ResultSet resultSet;
    public FilesTable(ResultSet resultSet){
        this.resultSet = resultSet;
    }
    public List<FileRow> list() throws SQLException {
        List<FileRow> list = new ArrayList<>();
        while(resultSet.next()){
            list.add(new FileRow(
                    resultSet.getString("name"),
                    resultSet.getString("extension"),
                    resultSet.getInt("size"),
                    resultSet.getInt("create_date")
            ));
        }
        return list;
    }
}
