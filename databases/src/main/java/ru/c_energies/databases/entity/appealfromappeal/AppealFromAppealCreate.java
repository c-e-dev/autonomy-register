package ru.c_energies.databases.entity.appealfromappeal;

import ru.c_energies.databases.Query;
import ru.c_energies.databases.entity.Create;
import ru.c_energies.databases.sqlite.SqliteDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Вызываем после создания сущности Appeal
 */
public class AppealFromAppealCreate  implements Create<AppealFromAppealCreate> {
    private final int themeId;
    private final int parentAppealId;
    private final int appealId;
    private int id;

    public AppealFromAppealCreate(int appealId, int parentAppealId, int themeId){
        this.themeId = themeId;
        this.parentAppealId = parentAppealId;
        this.appealId = appealId;
    }
    @Override
    public AppealFromAppealCreate insert() throws SQLException {
        String sql = String.format("INSERT INTO appeal_from_appeal (appeal_id, parent_appeal_id, theme_id) VALUES(%d, %d, %d) RETURNING rowid",
                this.appealId, this.parentAppealId, this.themeId);
        Query q = new Query(new SqliteDataSource(), sql);
        ResultSet resultSet = q.exec();
        while(resultSet.next()){
            this.id = resultSet.getInt("id");
        }
        return this;
    }

    @Override
    public void update() {

    }

    @Override
    public int id() {
        return this.id;
    }
}
