package ru.c_energies.update;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.c_energies.databases.Query;
import ru.c_energies.databases.sqlite.SqliteDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UpdateDatabase {
    private final Logger LOG = LogManager.getLogger(UpdateDatabase.class);
    private final String ALL_TABLE_LIST = "select name from pragma_table_list";
    public void start() throws SQLException {
        LOG.info("Обновление структуры базы данных");
        for(Map.Entry<String, List<TableField>> tables : StructureTables.DB.entrySet()){
            String tableName = tables.getKey();
            if(this.existsTable(tableName)){
                //Проверяем существование полей
                List<TableField> columns = tables.getValue();
                for(TableField column : columns){
                    if(!this.existsColumn(tableName, column.name())){
                        String sql = String.format("ALTER TABLE %s ADD COLUMN %s %s", tableName, column.name(), column.type());
                        new Query(new SqliteDataSource(), sql).update();
                    }
                }
            }else{ //создаем таблицу и всю ее структуру
                StringBuilder field = new StringBuilder();
                List<TableField> columns = tables.getValue();
                int i = columns.size()-1;
                String autoincrement = "";
                for(TableField column : columns){
                    if(column.pk() == 1){
                        autoincrement = ", PRIMARY KEY(\""+ column.name() + "\" AUTOINCREMENT) ";
                    }
                    if(i > 0){
                        field.append(column.name() + " " + column.type() + ", ");
                    }else{
                        field.append(column.name() + " " + column.type());
                        field.append(autoincrement);
                    }
                    i--;
                }
                LOG.trace("field = {}", field);

                String ddl = String.format("CREATE TABLE %s (%s)", tableName, field);
                LOG.trace("ddl = {}", ddl);
                new Query(new SqliteDataSource(), ddl).update();
            }
        }
    }

    private boolean existsTable(String tableName) throws SQLException{
        boolean res = false;
        Query query = new Query(new SqliteDataSource(), "select count(1) as c from pragma_table_list where name = '"+ tableName +"'");
        ResultSet rs = query.exec();
        int countTable = 0;
        while(rs.next()){
            countTable = rs.getInt("c");
        }
        if(countTable > 0) { res = true; }
        return res;
    }

    private boolean existsColumn(String tableName, String columnName) throws SQLException{
        boolean res = false;
        Query query = new Query(new SqliteDataSource(),
                "select count(1) as c from pragma_table_info('"+ tableName +"') where name = '"+ columnName +"'");
        ResultSet rs = query.exec();
        int count = 0;
        while(rs.next()){
            count = rs.getInt("c");
        }
        if(count > 0) { res = true; }
        return res;
    }
}
