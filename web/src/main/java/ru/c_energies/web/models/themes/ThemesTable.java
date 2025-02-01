package ru.c_energies.web.models.themes;

import ru.c_energies.databases.entity.themes.ThemeRow;
import ru.c_energies.utils.converters.DateFormat;
import ru.c_energies.utils.converters.ThemeStatuses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThemesTable {
    private final ResultSet resultSet;
    public ThemesTable(ResultSet resultSet){
        this.resultSet = resultSet;
    }
    public List<ThemeRow> list() throws SQLException {
        List<ThemeRow> list = new ArrayList<>();
        while(resultSet.next()){
            list.add(
                    new ThemeRow(resultSet.getInt("id"),
                            resultSet.getString("title"),
                            new DateFormat(resultSet.getInt("create_date")).convert(),
                            new DateFormat(resultSet.getInt("decision_date")).convert(),
                            new ThemeStatuses(resultSet.getInt("decision_status")).value(),
                            resultSet.getString("description")
                    )
            );
        }
        return list;
    }
}
