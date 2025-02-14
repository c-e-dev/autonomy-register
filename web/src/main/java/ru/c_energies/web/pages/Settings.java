package ru.c_energies.web.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.c_energies.databases.Query;
import ru.c_energies.databases.entity.appeals.AppealRow;
import ru.c_energies.databases.sqlite.SqliteDataSource;
import ru.c_energies.web.models.appeals.AppealsTable;

import java.sql.SQLException;
import java.util.List;

@Controller
public class Settings {
    @GetMapping(value = "/settings")
    public String list(Model model) throws SQLException {
        /*Query q = new Query(new SqliteDataSource(), "select * from settings");
        List<AppealRow> list = new AppealsTable(q.exec()).list();
        model.addAttribute("list", list);*/
        return "pages/settings";
    }
}
