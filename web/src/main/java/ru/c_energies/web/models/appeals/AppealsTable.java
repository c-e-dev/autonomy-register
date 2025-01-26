package ru.c_energies.web.models.appeals;

import ru.c_energies.databases.entity.appeals.AppealRow;
import ru.c_energies.web.convert.DateFormat;
import ru.c_energies.web.convert.DigitsToYesNo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppealsTable{
    private final ResultSet resultSet;
    public AppealsTable(ResultSet resultSet){
        this.resultSet = resultSet;
    }
    public List<AppealRow> list() throws SQLException {
        List<AppealRow> list = new ArrayList<>();
        while(resultSet.next()){
            list.add(
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
        return list;
    }
}
