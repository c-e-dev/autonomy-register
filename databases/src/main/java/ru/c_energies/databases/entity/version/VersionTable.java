package ru.c_energies.databases.entity.version;

import ru.c_energies.databases.Query;
import ru.c_energies.databases.entity.TableSql;
import ru.c_energies.databases.sqlite.SqliteDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VersionTable implements TableSql<VersionRow> {
    @Override
    public List<VersionRow> list() throws SQLException {
        Query q = new Query(new SqliteDataSource(), "select value from version");
        ResultSet resultSet = q.exec();
        List<VersionRow> versionRows = new ArrayList<>();
        while(resultSet.next()){
            versionRows.add(new VersionRow(resultSet.getString("value")));
        }
        return versionRows;
    }
}
