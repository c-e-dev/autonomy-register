package ru.c_energies.databases.entity.settings.backup.write;

import ru.c_energies.databases.entity.settings.AbstractSettingWrite;

import java.util.Map;

import static ru.c_energies.databases.entity.settings.MapUiToDb.BACKUP_YANDEXDISK;

public class BackupYandexDiskWrite extends AbstractSettingWrite {
    public BackupYandexDiskWrite(Map<String, Object> map) {
        super(BACKUP_YANDEXDISK, map);
    }
}
