package ru.c_energies.databases.entity.settings.backup.write;

import ru.c_energies.databases.entity.settings.AbstractSettingWrite;

import java.util.Map;

import static ru.c_energies.databases.entity.settings.MapUiToDb.BACKUP_TOTAL;

public class BackupTotalWrite extends AbstractSettingWrite {
    public BackupTotalWrite(Map<String, Object> map){
        super(BACKUP_TOTAL, map);
    }

}
