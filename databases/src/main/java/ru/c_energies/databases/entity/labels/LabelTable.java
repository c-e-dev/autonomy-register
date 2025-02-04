package ru.c_energies.databases.entity.labels;

import ru.c_energies.databases.Query;
import ru.c_energies.databases.entity.TableSql;
import ru.c_energies.databases.sqlite.SqliteDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LabelTable implements TableSql<LabelRow> {
    private final int appealId;
    public LabelTable(int appealId){
        this.appealId = appealId;
    }
    @Override
    public List<LabelRow> list() throws SQLException {
        Query query = new Query(new SqliteDataSource(), String.format(
                """
                        select * from labels where appeal_id = %d
                """, this.appealId
        ));
        ResultSet resultSet = query.exec();
        List<LabelRow> list = new ArrayList<>();
        while(resultSet.next()){
            list.add(
                    new LabelRow(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("appeal_id")
                    )
            );
        }
        return list;
    }
}
