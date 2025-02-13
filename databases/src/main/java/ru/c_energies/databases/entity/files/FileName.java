package ru.c_energies.databases.entity.files;

import ru.c_energies.databases.Query;
import ru.c_energies.databases.sqlite.SqliteDataSource;

/**
 * Действия с именем файла
 */
public class FileName {
    private final int fileId;
    private final String newName;
    public FileName(int fileId, String newName){
        this.fileId = fileId;
        this.newName = newName;
    }
    public void change(){
        String sql = String.format(
                """
                        update files set name = '%s' where id = '%d'
                        """, this.newName, this.fileId
        );
        Query query = new Query(new SqliteDataSource(), sql);
        query.update();
    }
}
