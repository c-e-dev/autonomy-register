package ru.c_energies.databases.entity.internal_number;

import ru.c_energies.databases.Query;
import ru.c_energies.databases.entity.Create;
import ru.c_energies.databases.sqlite.SqliteDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InternalNumberCreate implements Create<InternalNumberCreate> {
    private final InternalNumberRow internalNumberRow;
    private int id;

    public InternalNumberCreate(InternalNumberRow internalNumberRow){
        this.internalNumberRow = internalNumberRow;
    }
    @Override
    public InternalNumberCreate insert() throws SQLException {
        String query = """
                    INSERT INTO internal_number("type", "year", "month", "day", "increment") 
                    values ('%s', %d, %d, %d, %d);
                """;
        Query q = new Query(new SqliteDataSource(), String.format(query,
                this.internalNumberRow.type(),
                this.internalNumberRow.year(),
                this.internalNumberRow.month(),
                this.internalNumberRow.day(),
                this.internalNumberRow.increment()
        ));
        ResultSet resultSet = q.exec();
        while(resultSet.next()){
            this.id = resultSet.getInt("id");
        }
        return this;
    }

    @Override
    public void update() throws SQLException {

    }

    @Override
    public int id() {
        return this.id;
    }
}
