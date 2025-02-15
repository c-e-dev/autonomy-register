package ru.c_energies.databases.entity.secured_party;

import ru.c_energies.databases.Query;
import ru.c_energies.databases.entity.TableSql;
import ru.c_energies.databases.sqlite.SqliteDataSource;
import ru.c_energies.utils.converters.DateFormat;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SecuredPartyTable implements TableSql<SecuredPartyRow> {
    private final int id;
    private final int appealId;
    public SecuredPartyTable(int id, int appealId){
        this.id = id;
        this.appealId = appealId;
    }
    @Override
    public List<SecuredPartyRow> list() throws SQLException {
        String sql = "";
        if(this.id < 1 && this.appealId < 1){
            sql = "select * from secured_partyes";
        }
        if(this.id > 0 && this.appealId < 1){
            sql = String.format("select * from secured_partyes where id = %d", this.id);
        }
        if(this.id < 1 && this.appealId > 0){
            sql = String.format("select * from secured_partyes where appeal_id = %d", this.appealId);
        }
        Query query = new Query(new SqliteDataSource(), sql);
        ResultSet resultSet = query.exec();
        List<SecuredPartyRow> securedPartyRows = new ArrayList<>();
        while(resultSet.next()){
            securedPartyRows.add(
                    new SecuredPartyRow(
                            resultSet.getInt("id"),
                            resultSet.getInt("amount"),
                            resultSet.getInt("appeal_id"),
                            resultSet.getString("name"),
                            new DateFormat(resultSet.getInt("create_date")).convert(),
                            new DateFormat(resultSet.getInt("apply_date")).convert(),
                            resultSet.getInt("used")
                    )
            );
        }
        return securedPartyRows;
    }
}
