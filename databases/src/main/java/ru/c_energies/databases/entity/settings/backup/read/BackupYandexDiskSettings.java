package ru.c_energies.databases.entity.settings.backup.read;

import ru.c_energies.databases.entity.settings.SettingTable;

import java.sql.SQLException;
import java.util.Map;

public class BackupYandexDiskSettings {
    private final String name = "backup-yandexdisk-%";
    private Inner inner;
    public Inner get() throws SQLException {
        this.inner = new Inner(new SettingTable(this.name).values());
        return this.inner;
    }

    public class Inner{
        private Map<String, String> values;
        public Inner(Map<String, String> values){
            this.values = values;
        }
        public boolean use(){
            return this.values.get("backup-yandexdisk-use").equals("1");
        }
        public String token(){
            return this.values.get("backup-yandexdisk-token");
        }
        public String dirdist(){
            return this.values.get("backup-yandexdisk-dirdist");
        }
    }
}
