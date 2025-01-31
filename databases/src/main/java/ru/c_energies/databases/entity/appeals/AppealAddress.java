package ru.c_energies.databases.entity.appeals;

import ru.c_energies.databases.Query;
import ru.c_energies.databases.entity.addresses.AddressRow;
import ru.c_energies.databases.sqlite.SqliteDataSource;

/**
 * Получение строки адреса по идентификатору обращения и идентификатору адреса
 * Запись в таблицу appeal_address
 */
public class AppealAddress {
    private final int appealId;
    private final int addressId;
    public AppealAddress(int appealId, int addressId){
        this.addressId = addressId;
        this.appealId = appealId;
    }

    /**
     * получение адреса
     * @return
     */
    public AddressRow address(){
        Query query = new Query(new SqliteDataSource(), String.format("select * from appeal_address where "));
    }

    /**
     * Вставка строки в таблицу appeal_address
     * @param addressId
     */
    public void address(int addressId){
    }
}
