/**
 * https://www.sqlitetutorial.net/sqlite-java/jdbc-read-write-blob/
 */

package ru.c_energies.web.pages;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.c_energies.databases.Query;
import ru.c_energies.databases.entity.files.FileContent;
import ru.c_energies.databases.sqlite.SqliteDataSource;
import ru.c_energies.web.convert.FileNameAndExtension;
import ru.c_energies.databases.entity.files.FileRow;
import ru.c_energies.databases.entity.files.FilesCreate;
import ru.c_energies.web.models.files.FilesTable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

@Controller
public class Files {
    /**
     * Вывод списка файлов по определенному appeal
     * @param model
     * @param id идентификатор appeal
     * @return
     * @throws SQLException
     */
    @GetMapping(value = "/files/appeal/{id}")
    public String listFilesByAppeal(Model model, @PathVariable("id") String id) throws SQLException {
        String sql = String.format("""
                select f.id, f.name, f.content, f.extension, f."size", f.create_date from appeals a
                join files_appeal fa on fa.appeal_id = a.id\s
                join files f on f.id = fa.file_id\s
                where a.id = %d
                """, Integer.parseInt(id));
        Query q = new Query(new SqliteDataSource(), sql);
        List<FileRow> list = new FilesTable(q.exec()).list();
        model.addAttribute("listFiles", list);
        return "pages/appeal";
    }
    public List<FileRow> listFilesSended(@PathVariable("id") String id) throws SQLException {
        String sql = String.format("""
                select f.id, f.name, f.content, f.extension, f."size", f.create_date, f.content_type, fa.appeal_type_id from appeals a
                join files_appeal fa on fa.appeal_id = a.id
                join files f on f.id = fa.file_id
                join appeal_type at2 on at2."type" = fa.appeal_type_id and at2."type" = 0 
                where a.id = %d
                """, Integer.parseInt(id));
        Query q = new Query(new SqliteDataSource(), sql);
        List<FileRow> list = new FilesTable(q.exec()).list();
        return list;
    }

    public List<FileRow> listFilesAnswered(@PathVariable("id") String id) throws SQLException {
        String sql = String.format("""
                select f.id, f.name, f.content, f.extension, f."size", f.create_date, f.content_type, fa.appeal_type_id from appeals a
                join files_appeal fa on fa.appeal_id = a.id
                join files f on f.id = fa.file_id
                join appeal_type at2 on at2."type" = fa.appeal_type_id and at2."type" = 1
                where a.id = %d
                """, Integer.parseInt(id));
        Query q = new Query(new SqliteDataSource(), sql);
        List<FileRow> list = new FilesTable(q.exec()).list();
        return list;
    }

    /**
     * Скачивание файла
     * @param model
     * @param id идентификатор файла
     * @return
     * @throws SQLException
     */
    @GetMapping(value = "/files/{id}")
    public ResponseEntity<Resource> downloadFile(Model model, @PathVariable("id") String id) throws SQLException, IOException {
        ByteArrayResource resource = new ByteArrayResource(new FileContent(Integer.parseInt(id)).upload());
        //TODO сделать отдельную единицу Row
        String sql = String.format("""
                select f.id, f.name, f.content, f.extension, f."size", f.create_date, f.content_type, fa.appeal_type_id from appeals a
                join files_appeal fa on fa.appeal_id = a.id
                join files f on f.id = fa.file_id 
                join appeal_type at2 on at2."type" = fa.appeal_type_id 
                where f.id = %d
                """, Integer.parseInt(id));
        Query q = new Query(new SqliteDataSource(), sql);
        FileRow fileRow = new FilesTable(q.exec()).list().get(0);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(fileRow.contentType()))
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename(fileRow.name())
                                .build().toString())
                .body(resource);
    }

    /**
     * Загрузка файла на сервер по идентификатору appeal
     */
    @PostMapping(value = "/files/{id}/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Object> uploadFile(Model model, @PathVariable("id") String id, @RequestPart MultipartFile fileContent, @RequestPart String fileCategory) throws SQLException, IOException {
        FileNameAndExtension fileNameAndExtension = new FileNameAndExtension(fileContent.getOriginalFilename());
        int currentTime = (int)Instant.now().getEpochSecond();
        FileRow fileRow = new FileRow(
                0,
                fileNameAndExtension.name(),
                fileNameAndExtension.extension(),
                fileContent.getSize(),
                currentTime,
                fileContent.getContentType(),
                Integer.parseInt(fileCategory)
        );
        new FilesCreate(Long.parseLong(id), fileRow).insert().update(fileContent.getBytes());
        return ResponseEntity.ok().build();
    }
}
