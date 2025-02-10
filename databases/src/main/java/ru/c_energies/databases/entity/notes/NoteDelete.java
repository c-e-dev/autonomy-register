package ru.c_energies.databases.entity.notes;

import ru.c_energies.databases.Query;
import ru.c_energies.databases.entity.Delete;
import ru.c_energies.databases.sqlite.SqliteDataSource;

import java.sql.SQLException;

public class NoteDelete implements Delete {
    private final int noteId;
    public NoteDelete(int noteId){
        this.noteId = noteId;
    }
    @Override
    public void delete() throws SQLException {
        Query query = new Query(new SqliteDataSource(), String.format(
                """
                        DELETE FROM notes WHERE id=%d
                """, this.noteId
        ));
        query.update();
    }
}
