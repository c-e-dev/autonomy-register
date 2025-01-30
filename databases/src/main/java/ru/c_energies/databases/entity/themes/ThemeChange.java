package ru.c_energies.databases.entity.themes;

import ru.c_energies.databases.Query;
import ru.c_energies.databases.entity.Changes;
import ru.c_energies.databases.sqlite.SqliteDataSource;

import java.time.Instant;

public class ThemeChange implements Changes {
    private final ThemeRow themeRow;
    public ThemeChange(ThemeRow themeRow){
        this.themeRow = themeRow;
    }
    @Override
    public int insert() {
        return 0;
    }

    @Override
    public void update() {
        String sql = """
                UPDATE themes
                SET title='%s', decision_date=%d, decision_status=%d, description='%s'
                WHERE id=%d;
                """;
        Query q = new Query(new SqliteDataSource(),
                String.format(sql,
                        this.themeRow.title(),
                        (int) Instant.parse(this.themeRow.decisionDate()).getEpochSecond(),
                        Integer.parseInt(this.themeRow.decisionStatus()),
                        this.themeRow.description(),
                        this.themeRow.id()
                        )
        );
        q.update();
    }
}
