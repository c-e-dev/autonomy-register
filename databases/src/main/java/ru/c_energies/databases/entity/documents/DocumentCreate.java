package ru.c_energies.databases.entity.documents;

import ru.c_energies.databases.Query;
import ru.c_energies.databases.entity.Create;
import ru.c_energies.databases.sqlite.SqliteDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentCreate implements Create<DocumentCreate> {
    private final String INSERT = """
                INSERT INTO documents
                (document_id, appeal_id, name, internal_number, internal_number_rule, file_id)
                VALUES(%d, %d, '%s', '%s', '%s', %d) RETURNING rowid
            """;
    private int id;
    private final DocumentRow documentRow;
    public DocumentCreate(DocumentRow documentRow){
        this.documentRow = documentRow;
    }

    @Override
    public DocumentCreate insert() throws SQLException {
        Query q = new Query(new SqliteDataSource(), String.format(this.INSERT,
            this.documentId(), this.documentRow.appealId(), this.documentRow.name(),
                this.documentRow.internalNumber(), this.documentRow.internalNumberRule(), this.documentRow.fileId()
                )
        );
        ResultSet resultSet = q.exec();
        while(resultSet.next()){
            this.id = resultSet.getInt("id");
        }
        return this;
    }

    @Override
    public void update() throws SQLException {

    }

    @Override
    public int id() {
        return this.id;
    }

    private int documentId() throws SQLException {
        int count = 0;
        String sql = "select count(1) c from documents where appeal_id = %d";
        Query query = new Query(new SqliteDataSource(), String.format(sql, this.documentRow.appealId()));
        ResultSet rs = query.exec();
        while(rs.next()){
            count++;
        }
        if(count == 0){
            count = 1;
        }
        return count;
    }
}
