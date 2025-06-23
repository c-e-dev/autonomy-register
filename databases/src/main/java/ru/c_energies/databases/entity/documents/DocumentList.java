package ru.c_energies.databases.entity.documents;

import ru.c_energies.databases.Query;
import ru.c_energies.databases.sqlite.SqliteDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocumentList {
    private final int appealId;
    public DocumentList(int appealId){
        this.appealId = appealId;
    }
    public List<DocumentRow> rows() throws SQLException {
        Query query = new Query(new SqliteDataSource(), String.format("select * from documents where appeal_id = '%s'", this.appealId));
        ResultSet resultSet = query.exec();
        List<DocumentRow> documentRows = new ArrayList<>();
        while(resultSet.next()){
            documentRows.add(
              new DocumentRow(
                      resultSet.getInt("id"),
                      resultSet.getInt("document_id"),
                      resultSet.getInt("appeal_id"),
                      resultSet.getString("name"),
                      resultSet.getString("internal_number"),
                      resultSet.getString("internal_number_rule"),
                      resultSet.getInt("file_id")
              )
            );
        }
        return documentRows;
    }
}
