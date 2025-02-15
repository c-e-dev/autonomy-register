package ru.c_energies.databases.entity.secured_party;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.c_energies.databases.Query;
import ru.c_energies.databases.entity.Create;
import ru.c_energies.databases.sqlite.SqliteDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class SecuredPartyCreate implements Create<SecuredPartyCreate> {
    private Logger LOG = LogManager.getLogger(SecuredPartyCreate.class);
    private int id;
    private final SecuredPartyRow securedPartyRow;
    public SecuredPartyCreate(SecuredPartyRow securedPartyRow){
        this.securedPartyRow = securedPartyRow;
    }
    @Override
    public SecuredPartyCreate insert() throws SQLException {
        String sql = String.format(
                """
                        INSERT INTO secured_partyes (amount, appeal_id, name, create_date, apply_date)
                        VALUES(%d, %d, '%s', %d, %d) RETURNING rowid
                        """, this.securedPartyRow.amount(), this.securedPartyRow.appealId(), this.securedPartyRow.name(),
                (int) Instant.now().getEpochSecond(), (int) Instant.now().getEpochSecond()
        );
        LOG.trace("sql = {}", sql);
        Query query = new Query(new SqliteDataSource(), sql);
        ResultSet resultSet = query.exec();
        while(resultSet.next()){
            this.id = resultSet.getInt("id");
        }
        LOG.trace("created id = {}", this.id);
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
