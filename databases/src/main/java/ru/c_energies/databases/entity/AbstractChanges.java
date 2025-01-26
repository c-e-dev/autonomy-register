package ru.c_energies.databases.entity;

import ru.c_energies.databases.Query;
import ru.c_energies.databases.sqlite.SqliteDataSource;

public abstract class AbstractChanges implements Changes{
    private final String sql;
    public AbstractChanges(String sql){
        this.sql = sql;
    }
    @Override
    public void update() {
        Query q = new Query(new SqliteDataSource(), this.sql);
        q.update();
    }
}
