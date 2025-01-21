package ru.c_energies.web.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.c_energies.databases.Query;
import ru.c_energies.databases.sqlite.SqliteDataSource;
import ru.c_energies.web.models.themes.ThemeRow;
import ru.c_energies.web.models.themes.ThemesTable;

import java.sql.SQLException;
import java.util.List;

@Controller
public class Themes {
    @GetMapping(value = "/document/themes")
    public String themes(Model model) throws SQLException {
        Query q = new Query(new SqliteDataSource(), "select * from themes");
        List<ThemeRow> list = new ThemesTable(q.exec()).list();
        model.addAttribute("list", list);
        return "pages/themes";
    }

    @GetMapping(value = "/document/themes/{id}")
    public String fullThemeById(Model model, @PathVariable("id") String id){
        return "pages/themeById";
    }
}
