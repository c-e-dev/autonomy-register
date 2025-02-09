package ru.c_energies.databases.entity.notes;

import ru.c_energies.databases.Query;
import ru.c_energies.databases.entity.Create;
import ru.c_energies.databases.sqlite.SqliteDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class NoteCreate implements Create<NoteCreate> {
    private final int appealId;
    private final int themeId;
    private final String content;
    private final String title;
    private int id;

    /**
     * Если appealId = 0 , то заполняется для темы
     * Если themeId = 0, то заполняем для обращения
     * @param appealId
     * @param themeId
     * @param content
     */
    public NoteCreate(int appealId, int themeId, String title, String content){
        this.appealId = appealId;
        this.themeId = themeId;
        this.content = content;
        this.title = title;
    }
    @Override
    public NoteCreate insert() throws SQLException {
        String sql = "";
        if(this.themeId > 0){
            sql = String.format("""
                    INSERT INTO notes(content, appeal_id, theme_id, create_date, title) VALUES('%s', -1, %d, %d, '%s') RETURNING rowid
                """, this.content, this.themeId, (int) Instant.now().getEpochSecond(), this.title);
        }
        if(this.appealId > 0){
            sql = String.format("""
                    INSERT INTO notes(content, appeal_id, theme_id, create_date, title) VALUES('%s', %d, -1, %d, '%s') RETURNING rowid
                """, this.content, this.appealId, (int) Instant.now().getEpochSecond(), this.title);
        }
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
