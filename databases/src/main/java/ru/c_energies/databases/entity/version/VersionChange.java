package ru.c_energies.databases.entity.version;

import ru.c_energies.databases.Query;
import ru.c_energies.databases.entity.Changes;
import ru.c_energies.databases.sqlite.SqliteDataSource;

public class VersionChange implements Changes {
    private final String version;
    public VersionChange(String version){
        this.version = version;
    }
    @Override
    public int insert() {
        return 0;
    }

    @Override
    public void update() {
        String sql = "update version set value = '%s'";
        Query q = new Query(new SqliteDataSource(),
                String.format(sql, this.version));
        q.update();
    }
}
