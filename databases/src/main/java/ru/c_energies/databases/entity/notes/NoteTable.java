package ru.c_energies.databases.entity.notes;

import ru.c_energies.databases.Query;
import ru.c_energies.databases.entity.TableSql;
import ru.c_energies.databases.sqlite.SqliteDataSource;
import ru.c_energies.utils.converters.DateFormat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoteTable implements TableSql<NoteRow>{
    private final int themeId;
    private final int appealId;
    public NoteTable(int themeId, int appealId){
        this.appealId = appealId;
        this.themeId = themeId;
    }

    @Override
    public List<NoteRow> list() throws SQLException {
        String sql = "";
        if(this.themeId > 0){
            sql = String.format("select * from notes where theme_id = %d", this.themeId);
        }
        if(this.appealId > 0){
            sql = String.format("select * from notes where appeal_id = %d", this.appealId);
        }
        if(this.themeId < 1 && this.appealId < 1){
            sql = "select * from notes";
        }
        Query q = new Query(new SqliteDataSource(), sql);
        ResultSet resultSet = q.exec();
        List<NoteRow> noteRows = new ArrayList<>();
        while(resultSet.next()){
            noteRows.add(
                    new NoteRow(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("content"),
                            resultSet.getInt("appeal_id"),
                            resultSet.getInt("theme_id"),
                            new DateFormat(resultSet.getInt("create_date")).convert()
                    )
            );
        }
        return noteRows;
    }
}
