package ru.c_energies.databases.entity.files;

import ru.c_energies.databases.Query;
import ru.c_energies.databases.sqlite.SqliteDataSource;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilesCreate {
    private final String INSERT = """
                INSERT INTO files
                (name, extension, "size", create_date, content_type)
                VALUES('%s', '%s', %d, %d, '%s') RETURNING rowid
            """;
    private final String UPDATE = """
                UPDATE files
                SET content=? WHERE id=?            
            """;
    private final String INSERT_FILES_APPEAL = """
                INSERT INTO files_appeal (appeal_id, file_id, appeal_type_id) VALUES(%d, %d, %d)  RETURNING rowid
            """;
    private long id; //Идентификатор вставленной записи
    private long appealId;
    private final FileRow fileRow;
    public FilesCreate(long appealId, FileRow fileRow){
        this.appealId = appealId;
        this.fileRow = fileRow;
    }
    public FilesCreate insert() throws SQLException {
        Query q = new Query(new SqliteDataSource(), String.format(this.INSERT,
                this.fileRow.name(),
                this.fileRow.extension(),
                this.fileRow.size(),
                this.fileRow.createDate(),
                this.fileRow.contentType()
        ));
        ResultSet resultSet = q.exec();
        while(resultSet.next()){
            this.id = resultSet.getInt("id");
        }
        new Query(new SqliteDataSource(), String.format(this.INSERT_FILES_APPEAL, this.appealId, this.id, this.fileRow.appealType())).insert();
        return this;
    }
    public void update(byte[] content){
        Query q = new Query(new SqliteDataSource(), this.UPDATE);
        q.updateBlob((int) this.id, content);
    }

    /**
     * Read the file and returns the byte array
     * @param file
     * @return the bytes of the file
     */
    private byte[] readFile(String file) {
        ByteArrayOutputStream bos = null;
        try {
            File f = new File(file);
            FileInputStream fis = new FileInputStream(f);
            byte[] buffer = new byte[1024];
            bos = new ByteArrayOutputStream();
            for (int len; (len = fis.read(buffer)) != -1;) {
                bos.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e2) {
            System.err.println(e2.getMessage());
        }
        return bos != null ? bos.toByteArray() : null;
    }
}
