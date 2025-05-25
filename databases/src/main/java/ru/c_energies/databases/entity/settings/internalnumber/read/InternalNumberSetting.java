package ru.c_energies.databases.entity.settings.internalnumber.read;

import ru.c_energies.databases.entity.settings.SettingTable;

import java.sql.SQLException;
import java.util.Map;

public class InternalNumberSetting {
    private final String name = "internalnumber-%";
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
            return this.values.get("internalnumber-use").equals("true");
        }
        public String format(){
            return this.values.get("internalnumber-format");
        }
    }
}
