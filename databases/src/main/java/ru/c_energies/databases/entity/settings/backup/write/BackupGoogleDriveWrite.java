package ru.c_energies.databases.entity.settings.backup.write;

import ru.c_energies.databases.entity.settings.AbstractSettingWrite;

import java.util.Map;

import static ru.c_energies.databases.entity.settings.MapUiToDb.BACKUP_GOOGLEDRIVE;

public class BackupGoogleDriveWrite extends AbstractSettingWrite {
    public BackupGoogleDriveWrite(Map<String, Object> map) {
        super(BACKUP_GOOGLEDRIVE, map);
    }
}
