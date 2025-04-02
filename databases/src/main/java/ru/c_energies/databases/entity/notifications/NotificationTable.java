package ru.c_energies.databases.entity.notifications;

import ru.c_energies.databases.Query;
import ru.c_energies.databases.entity.TableSql;
import ru.c_energies.databases.sqlite.SqliteDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificationTable implements TableSql<NotificationRow> {

    public NotificationTable(){

    }

    @Override
    public List<NotificationRow> list() throws SQLException {
        String sql = "select * from notifications where is_read = 0";
        Query q = new Query(new SqliteDataSource(), sql);
        ResultSet resultSet = q.exec();
        List<NotificationRow> notificationRows = new ArrayList<>();
        while(resultSet.next()){
            notificationRows.add(new NotificationRow(
                    resultSet.getInt("id"),
                    resultSet.getInt("time"),
                    resultSet.getString("content"),
                    resultSet.getInt("is_read")
            ));
        }

        return notificationRows;
    }
}
