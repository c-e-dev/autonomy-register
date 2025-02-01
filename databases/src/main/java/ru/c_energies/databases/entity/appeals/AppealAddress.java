package ru.c_energies.databases.entity.appeals;

import ru.c_energies.databases.Query;
import ru.c_energies.databases.entity.addresses.AddressRow;
import ru.c_energies.databases.entity.addresses.AddressTable;
import ru.c_energies.databases.sqlite.SqliteDataSource;
import ru.c_energies.utils.converters.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Получение строки адреса по идентификатору обращения
 * Запись в таблицу appeal_address
 */
public class AppealAddress {
    private final int appealId;
    public AppealAddress(int appealId){
        this.appealId = appealId;
    }

    /**
     * получение адреса
     * @return
     */
    public AddressRow address() throws SQLException {
        Query query = new Query(new SqliteDataSource(), String.format("select * from appeal_address where appeal_id = %d", this.appealId));
        ResultSet resultSet = query.exec();
        int addressId = 0;
        while(resultSet.next()){
            addressId = resultSet.getInt("address_id");
        }
        String sql = String.format("select * from addresses where id = %d", addressId);
        AddressRow addressRow = new AddressTable(sql).list().get(0);
        return addressRow;
    }

    /**
     * Вставка строки в таблицу appeal_address
     * @param addressId
     */
    public void address(int addressId){
        String sql = """
                    INSERT INTO appeal_address (appeal_id, address_id) VALUES(%d, %d) RETURNING rowid
                """;
        String sqlFormat = String.format(sql, this.appealId, addressId);
        Query query = new Query(new SqliteDataSource(), sqlFormat);
        query.insert();
    }

    /**
     * Получение всех обращений по данному адресу
     * @param addressId
     */
    public List<AppealRow> appeals(int addressId) throws SQLException{
        String sql = String.format("""
                    select a.id, a.title, a.internal_number, a.register_track_number, a.create_date, a.due_date, a.answered from appeal_address aa
                    join appeals a on a.id = aa.appeal_id 
                    where aa.address_id = %d;
                """, addressId);
        Query query = new Query(new SqliteDataSource(), sql);
        ResultSet resultSet = query.exec();
        List<AppealRow> appealRows = new ArrayList<>();
        while(resultSet.next()){
            appealRows.add(
                    new AppealRow(resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("internal_number"),
                            resultSet.getString("register_track_number"),
                            new DateFormat(resultSet.getInt("create_date")).convert(),
                            new DateFormat(resultSet.getInt("due_date")).convert(),
                            new DigitsToYesNo(resultSet.getInt("answered")).value()
                    )
            );
        }
        return appealRows;
    }
}
