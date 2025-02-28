package ru.c_energies.databases.entity.settings;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.c_energies.databases.Query;
import ru.c_energies.databases.sqlite.SqliteDataSource;

import java.util.Map;

public abstract class AbstractSettingWrite {
    private final Logger LOG = LogManager.getLogger(AbstractSettingWrite.class);
    private Map<String, String> mapUiToDb;
    private final Map<String, Object> map;
    public AbstractSettingWrite(Map<String, String> mapUiToDb, Map<String, Object> map){
        this.mapUiToDb = mapUiToDb;
        this.map = map;
    }
    public void update(){
        LOG.trace("Start method update");
        for(Map.Entry<String, Object> entry : this.map.entrySet()){
            String sql = String.format("update settings set value = '%s' where name = '%s';", entry.getValue(), this.mapUiToDb.get(entry.getKey()));
            LOG.trace("result sql = {}", sql);
            new Query(new SqliteDataSource(),sql).update();
        }
        LOG.trace("End method update");
    }
}
