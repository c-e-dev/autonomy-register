package ru.c_energies.databases.entity.settings.backup.read;

import ru.c_energies.databases.entity.settings.SettingTable;

import java.sql.SQLException;
import java.util.Map;

public class BackupTotalSettings {
    private final String name = "backup-total-%";
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
        public Boolean use(){
            return this.values.get("backup-total-use").equals("true");
        }
        public Boolean zip(){
            return this.values.get("backup-total-zip").equals("true");
        }
        public String rotate(){
            return this.values.get("backup-total-rotate");
        }
        public String starttime(){
            return this.values.get("backup-total-starttime");
        }
    }
}
