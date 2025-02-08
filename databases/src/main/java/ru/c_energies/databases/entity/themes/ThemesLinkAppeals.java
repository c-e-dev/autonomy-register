package ru.c_energies.databases.entity.themes;

import ru.c_energies.databases.Query;
import ru.c_energies.databases.entity.appeals.AppealRow;
import ru.c_energies.databases.sqlite.SqliteDataSource;
import ru.c_energies.utils.converters.DateFormat;
import ru.c_energies.utils.converters.DigitsToYesNo;
import ru.c_energies.utils.converters.ThemeStatuses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThemesLinkAppeals {
    public List<AppealRow> appeals(int themeId) throws SQLException {
        String sql = String.format("select * from appeals where id in (select appeal_id from themes_link_appeals where theme_id = %d)", themeId);
        Query query = new Query(new SqliteDataSource(), sql);
        ResultSet resultSet = query.exec();
        List<AppealRow> appealRows = new ArrayList<>();
        while(resultSet.next()){
            appealRows.add(
                    new AppealRow(resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("internal_number"),
                            resultSet.getString("register_track_number"),
                            new DateFormat(resultSet.getInt("create_date")).convert(),
                            new DateFormat(resultSet.getInt("due_date")).convert(),
                            new DigitsToYesNo(resultSet.getInt("answered")).value()
                    )
            );
        }
        return appealRows;
    }

    public ThemeRow theme(int appealId) throws SQLException {
        String sql = String.format("select * from themes t where id = (select theme_id from themes_link_appeals tla where appeal_id = %d)", appealId);
        Query query = new Query(new SqliteDataSource(), sql);
        ResultSet resultSet = query.exec();
        List<ThemeRow> themeRows = new ArrayList<>();
        while(resultSet.next()){
            themeRows.add(new ThemeRow(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        new DateFormat(resultSet.getInt("create_date")).convert(),
                        new DateFormat(resultSet.getInt("decision_date")).convert(),
                        new ThemeStatuses(resultSet.getInt("decision_status")).value(),
                        resultSet.getString("description")
                    )
            );
        }
        return themeRows.size() > 0 ? themeRows.get(0) : null;
    }
}
