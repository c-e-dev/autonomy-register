package ru.c_energies.databases.entity.settings;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface Setting {
    Map<String, String> values() throws SQLException;
}
