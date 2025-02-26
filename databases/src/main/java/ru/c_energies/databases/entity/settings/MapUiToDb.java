package ru.c_energies.databases.entity.settings;

import java.util.HashMap;
import java.util.Map;

public interface MapUiToDb {
    Map<String, String> BACKUP_TOTAL = new HashMap<>(){{
        put("backupTotalUse", "backup-total-use");
        put("backupTotalZip", "backup-total-zip");
        put("backupTotalRotate", "backup-total-rotate");
        put("backupTotalStartTime", "backup-total-starttime");
    }};

    Map<String, String> BACKUP_YANDEXDISK = new HashMap<>(){{
        put("backupYandexDiskUse", "backup-yandexdisk-use");
        put("backupYandexDiskToken", "backup-yandexdisk-token");
        put("backupYandexDiskDirdist", "backup-yandexdisk-dirdist");
    }};

    Map<String, String> BACKUP_GOOGLEDRIVE = new HashMap<>(){{
        put("backupGoogleDriveUse", "backup-googledrive-use");
        put("backupGoogleDriveToken", "backup-googledrive-token");
        put("backupGoogleDriveDirdist", "backup-googledrive-dirdist");
    }};
}
