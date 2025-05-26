package ru.c_energies.databases.entity.internal_number;

import ru.c_energies.databases.Query;
import ru.c_energies.databases.sqlite.SqliteDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Поиск последнего internal number по заданным параметрам
 */
public class InternalNumberLast {
    private final List<String> listField = new ArrayList<>(){{
        add("type");
        add("year");
        add("month");
        add("day");
    }};
    private final String format;
    public InternalNumberLast(String format){
        this.format = format;
    }

    public int increment() throws SQLException {
        String sql = this.sql();
        Query query = new Query(new SqliteDataSource(), sql);
        ResultSet rs = query.exec();
        int i = 0;
        while(rs.next()){
            i = rs.getInt("c");
        }
        return i+1;
    }

    private String sql(){
        String[] formatArray = this.format.replaceAll("%", "").split("-");
        StringBuilder sql = new StringBuilder("""
                    select count(1) as c from internal_number WHERE 1=1 
                """);
        for(String field : this.listField){
            if(Arrays.stream(formatArray).toList().contains(field)) {
                String str = " and (\"" + field + "\" is not null and LENGTH(\"" + field + "\") > 0)";
                sql.append(str);
            }else{
                String str = " and \"" + field + "\" is null";
                sql.append(str);
            }
        }

        return sql.toString();
    }
}
