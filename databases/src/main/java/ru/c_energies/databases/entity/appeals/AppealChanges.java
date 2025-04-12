package ru.c_energies.databases.entity.appeals;

import ru.c_energies.databases.Query;
import ru.c_energies.databases.entity.Changes;
import ru.c_energies.databases.sqlite.SqliteDataSource;

import java.time.Instant;

public class AppealChanges implements Changes {
    private final AppealRow appealRow;
    public AppealChanges(AppealRow appealRow){
        this.appealRow = appealRow;
    }

    @Override
    public void update() {
        String sql = """
            UPDATE appeals 
            SET title='%s', internal_number='%s', register_track_number='%s', due_date=%d, answered=%d, type='%s'
            WHERE id=%d
            """;
        Query q = new Query(new SqliteDataSource(),
                String.format(sql,
                        this.appealRow.title(),
                        this.appealRow.internalNumber(),
                        this.appealRow.registerTrackNumber(),
                        (int)Instant.parse(this.appealRow.dueDate()).getEpochSecond(),
                        Integer.parseInt(this.appealRow.answered()),
                        this.appealRow.type(),
                        this.appealRow.id()
                                ));
        q.update();
    }

    @Override
    public int insert() {
        return 0;
    }
}
