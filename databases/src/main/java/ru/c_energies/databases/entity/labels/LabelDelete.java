package ru.c_energies.databases.entity.labels;

import ru.c_energies.databases.Query;
import ru.c_energies.databases.entity.Delete;
import ru.c_energies.databases.sqlite.SqliteDataSource;

import java.sql.SQLException;

public class LabelDelete implements Delete {
    private final int labelId;
    public LabelDelete(int labelId){
        this.labelId = labelId;
    }
    @Override
    public void delete() throws SQLException {
        Query query = new Query(new SqliteDataSource(), String.format(
                """
                        DELETE FROM labels WHERE id=%d
                """, this.labelId
        ));
        query.update();
    }
}
