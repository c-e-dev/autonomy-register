package ru.c_energies.databases.entity.labels;

import ru.c_energies.databases.Query;
import ru.c_energies.databases.entity.Create;
import ru.c_energies.databases.sqlite.SqliteDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LabelCreate implements Create<LabelCreate> {
    private final int appealId;
    private final String name;
    private int id;
    public LabelCreate(int appealId, String name){
        this.appealId = appealId;
        this.name = name;
    }
    @Override
    public LabelCreate insert() throws SQLException {
        String sql = String.format("""
                    INSERT into labels (name, appeal_id) values ('%s', %d) RETURNING rowid
                """, this.name, this.appealId);
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

    @Override
    public int id() {
        return this.id;
    }
}
