package ru.c_energies.web.models.appeals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
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
                            Instant.ofEpochSecond(Long.parseLong(String.valueOf(resultSet.getInt("create_date"))))
                                    .toString())
            );
        }
        return list;
    }
}
