package ru.c_energies.databases.entity.settings.backup.write;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.c_energies.databases.Query;
import ru.c_energies.databases.sqlite.SqliteDataSource;

import java.util.HashMap;
import java.util.Map;

public class BackupTotalWrite {
    private final Logger LOG = LogManager.getLogger(BackupTotalWrite.class);
    private final Map<String, String> mapUiToDb = new HashMap<>(){{
        put("backupTotalUse", "backup-total-use");
        put("backupTotalZip", "backup-total-zip");
        put("backupTotalRotate", "backup-total-rotate");
        put("backupTotalStartTime", "backup-total-starttime");
    }};
    private final Map<String, Object> map;
    public BackupTotalWrite(Map<String, Object> map){
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
