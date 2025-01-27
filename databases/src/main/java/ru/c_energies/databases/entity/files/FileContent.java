package ru.c_energies.databases.entity.files;

import ru.c_energies.databases.Query;
import ru.c_energies.databases.sqlite.SqliteDataSource;

public class FileContent {
    private final String CONTENT = """
                select content from files where id = %d;
            """;
    private final int id;
    public FileContent(int id){
        this.id = id;
    }
    public byte[] upload(){
        Query q = new Query(new SqliteDataSource(), String.format(this.CONTENT, this.id));
        return q.content();
    }
}
