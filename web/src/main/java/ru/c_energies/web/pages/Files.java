/**
 * https://www.sqlitetutorial.net/sqlite-java/jdbc-read-write-blob/
 */

package ru.c_energies.web.pages;

import javafx.collections.transformation.FilteredList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.c_energies.databases.Query;
import ru.c_energies.databases.sqlite.SqliteDataSource;
import ru.c_energies.web.models.appeals.AppealRow;
import ru.c_energies.web.models.appeals.AppealsTable;
import ru.c_energies.web.models.files.FileRow;
import ru.c_energies.web.models.files.FilesTable;
import ru.c_energies.web.models.themes.ThemeRow;
import ru.c_energies.web.models.themes.ThemesTable;

import java.sql.SQLException;
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
                select f.name, f.content, f.extension, f."size", f.create_date from appeals a
                join files_appeal fa on fa.appeal_id = a.id\s
                join files f on f.id = fa.file_id\s
                where a.id = %d
                """, Integer.parseInt(id));
        Query q = new Query(new SqliteDataSource(), sql);
        List<FileRow> list = new FilesTable(q.exec()).list();
        model.addAttribute("listFiles", list);
        return "pages/appeal";
    }
    public List<FileRow> listFiles(@PathVariable("id") String id) throws SQLException {
        String sql = String.format("""
                select f.name, f.content, f.extension, f."size", f.create_date from appeals a
                join files_appeal fa on fa.appeal_id = a.id\s
                join files f on f.id = fa.file_id\s
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
    public String downloadFile(Model model, @PathVariable("id") String id) throws SQLException {

        return "";
    }
}
