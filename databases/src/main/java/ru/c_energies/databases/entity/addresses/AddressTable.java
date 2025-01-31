package ru.c_energies.databases.entity.addresses;

import ru.c_energies.databases.Query;
import ru.c_energies.databases.entity.TableSql;
import ru.c_energies.databases.sqlite.SqliteDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Вывод таблицы
 */
public class AddressTable implements TableSql<AddressRow> {
    private final String sql;
    public AddressTable(String sql){
        this.sql = sql;
    }

    public List<AddressRow> list() throws SQLException {
        Query q = new Query(new SqliteDataSource(), this.sql);
        ResultSet resultSet = q.exec();
        List<AddressRow> list = new ArrayList<>();
        while(resultSet.next()){
            list.add(
                    new AddressRow(resultSet.getInt("id"),
                            resultSet.getString("recipient"),
                            resultSet.getString("address"),
                            resultSet.getInt("actually")
                    )
            );
        }
        return list;
    }

}
