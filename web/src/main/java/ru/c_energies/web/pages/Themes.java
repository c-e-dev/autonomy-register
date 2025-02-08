package ru.c_energies.web.pages;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.c_energies.databases.Query;
import ru.c_energies.databases.entity.addresses.AddressRow;
import ru.c_energies.databases.entity.appeals.AppealAddress;
import ru.c_energies.databases.entity.themes.ThemeChange;
import ru.c_energies.databases.entity.themes.ThemeCreate;
import ru.c_energies.databases.sqlite.SqliteDataSource;
import ru.c_energies.databases.entity.appeals.AppealRow;
import ru.c_energies.utils.converters.ThemeStatuses;
import ru.c_energies.web.models.appeals.AppealsTable;
import ru.c_energies.databases.entity.themes.ThemeRow;
import ru.c_energies.web.models.themes.ThemesTable;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class Themes {
    @GetMapping(value = "/document/themes")
    public String themes(Model model) throws SQLException{
        Query q = new Query(new SqliteDataSource(), "select * from themes");
        List<ThemeRow> list = new ThemesTable(q.exec()).list();
        model.addAttribute("list", list);
        return "pages/themes";
    }

    @PostMapping(value = "/document/themes", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Object> createTheme(String title, String description,
                                            String decisionDate, String decisionStatus) throws SQLException{
        ThemeRow themeRow = new ThemeRow(
                0,
                title,
                "",
                decisionDate+":00Z",
                decisionStatus,
                description
        );
        new ThemeCreate(themeRow).insert();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/document/themes/{id}")
    public String fullThemeById(Model model, @PathVariable("id") String id) throws SQLException{
        Query q = new Query(new SqliteDataSource(), String.format("select * from themes where id = %d", Integer.parseInt(id)));
        List<ThemeRow> list = new ThemesTable(q.exec()).list();
        Query q2 = new Query(new SqliteDataSource(),
                String.format("select * from appeals where id in (select appeal_id from themes_link_appeals where theme_id = %d)", Integer.parseInt(id)));
        List<AppealRow> listAppeal = new AppealsTable(q2.exec()).list();
        int decisionStatus = new ThemeStatuses(0).reverse(list.get(0).decisionStatus());
        Map<Integer, AddressRow> addressRowMap = new HashMap<>();
        for(AppealRow appealRow : listAppeal){
            addressRowMap.put(appealRow.id(), new AppealAddress(appealRow.id()).address());
        }
        model.addAttribute("theme", list.get(0));
        model.addAttribute("listAppeals", listAppeal);
        model.addAttribute("addressRowMap", addressRowMap);
        model.addAttribute("decisionStatus", decisionStatus);
        return "pages/theme";
    }

    @PostMapping(value = "/document/themes/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Object> editTheme(@PathVariable("id") String id, String title, String description,
                                            String decisionDate, String decisionStatus) throws SQLException{
        ThemeRow themeRow = new ThemeRow(
                Integer.parseInt(id),
                title,
                "",
                decisionDate+":00Z",
                decisionStatus,
                description
        );
        new ThemeChange(themeRow).update();
        return ResponseEntity.ok().build();
    }
}
