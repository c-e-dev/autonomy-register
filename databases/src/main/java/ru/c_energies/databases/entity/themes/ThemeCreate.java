package ru.c_energies.databases.entity.themes;

import ru.c_energies.databases.Query;
import ru.c_energies.databases.entity.Create;
import ru.c_energies.databases.sqlite.SqliteDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class ThemeCreate implements Create<ThemeCreate> {
    private final String INSERT = """
               INSERT INTO themes
               (title, create_date, decision_date, decision_status, description)
               VALUES('%s', %d, %d, %d, '%s') RETURNING rowid 
            """;
    private final ThemeRow themeRow;
    private int id;
    public ThemeCreate(ThemeRow themeRow){
        this.themeRow = themeRow;
    }
    @Override
    public ThemeCreate insert() throws SQLException {
        Query q = new Query(new SqliteDataSource(), String.format(this.INSERT,
                this.themeRow.title(),
                (int) Instant.now().getEpochSecond(),
                (int)Instant.parse(this.themeRow.decisionDate()).getEpochSecond(),
                0,
                this.themeRow.description()
        ));
        ResultSet resultSet = q.exec();
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
