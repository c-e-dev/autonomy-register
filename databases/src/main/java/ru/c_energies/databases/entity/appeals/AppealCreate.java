package ru.c_energies.databases.entity.appeals;

import ru.c_energies.databases.Query;
import ru.c_energies.databases.entity.Create;
import ru.c_energies.databases.sqlite.SqliteDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class AppealCreate implements Create<AppealCreate> {
    private final String INSERT = """
                INSERT INTO appeals
                (title, internal_number, register_track_number, create_date, due_date, answered, type)
                VALUES('%s', '%s', '%s', %d, %d, %d, %s) RETURNING rowid
            """;
    private final String INSERT_INTO_THEME = """
                INSERT INTO themes_link_appeals (theme_id, appeal_id) VALUES(%d, %d)
            """;
    private final AppealRow appealRow;
    private int id;
    private final int themeId;
    public AppealCreate(int themeId, AppealRow appealRow){
        this.themeId = themeId;
        this.appealRow = appealRow;
    }
    @Override
    public AppealCreate insert() throws SQLException {
        Query q = new Query(new SqliteDataSource(), String.format(this.INSERT,
                this.appealRow.title(),
                this.appealRow.internalNumber(),
                this.appealRow.registerTrackNumber(),
                (int)Instant.now().getEpochSecond(),
                (int)Instant.parse(this.appealRow.dueDate()).getEpochSecond(),
                0,
                this.appealRow.type()
        ));
        ResultSet resultSet = q.exec();
        while(resultSet.next()){
            this.id = resultSet.getInt("id");
        }
        new Query(new SqliteDataSource(), String.format(this.INSERT_INTO_THEME, this.themeId, this.id)).update();
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
