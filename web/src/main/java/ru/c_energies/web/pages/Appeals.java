package ru.c_energies.web.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.c_energies.databases.Query;
import ru.c_energies.databases.sqlite.SqliteDataSource;
import ru.c_energies.web.models.appeals.AppealRow;
import ru.c_energies.web.models.appeals.AppealsTable;
import ru.c_energies.web.models.files.FileRow;

import java.sql.SQLException;
import java.util.List;

@Controller
public class Appeals {
    @GetMapping(value = "/document/appeals")
    public String request(Model model) throws SQLException {
        Query q = new Query(new SqliteDataSource(), "select * from appeals");
        List<AppealRow> list = new AppealsTable(q.exec()).list();
        model.addAttribute("list", list);
        return "pages/appeals";
    }

    @GetMapping(value = "/document/appeals/{id}")
    public String fullAppealById(Model model, @PathVariable("id") String id) throws SQLException {
        Query q = new Query(new SqliteDataSource(), String.format("select * from appeals where id = %d", Integer.parseInt(id)));
        List<AppealRow> list = new AppealsTable(q.exec()).list();
        List<FileRow> listFileRow = new Files().listFiles(id);
        model.addAttribute("appeal", list.get(0));
        model.addAttribute("listFiles", listFileRow);
        return "pages/appeal";
    }
}
