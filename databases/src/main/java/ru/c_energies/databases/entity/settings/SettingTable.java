package ru.c_energies.databases.entity.settings;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.c_energies.databases.Query;
import ru.c_energies.databases.entity.secured_party.SecuredPartyRow;
import ru.c_energies.databases.sqlite.SqliteDataSource;
import ru.c_energies.utils.converters.DateFormat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SettingTable implements Setting {
    private final Logger LOG = LogManager.getLogger(SettingTable.class);
    private final String name;
    public SettingTable(String name){
        this.name = name;
    }

    @Override
    public Map<String, String> values() throws SQLException {
        String sql = String.format("select name, value from settings where name like '%s'", this.name);
        LOG.trace("sql = {}", sql);
        Query query = new Query(new SqliteDataSource(), sql);
        ResultSet resultSet = query.exec();
        Map<String, String> res = new HashMap<>();
        while(resultSet.next()){
            res.put(resultSet.getString("name"), resultSet.getString("value"));
        }
        return res;
    }
}
