package ru.c_energies.web.pages;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.c_energies.databases.entity.settings.backup.write.BackupTotalWrite;

import java.sql.SQLException;
import java.util.Map;

@Controller
public class Settings {
    @GetMapping(value = "/settings")
    public String list(Model model) throws SQLException {
        /*Query q = new Query(new SqliteDataSource(), "select * from settings");
        List<AppealRow> list = new AppealsTable(q.exec()).list();
        model.addAttribute("list", list);*/
        return "pages/settings";
    }

    @PostMapping(value = "/settings/{nameForm}")
    public ResponseEntity<Object> save(@PathVariable("nameForm") String nameForm, @RequestBody Map<String, Object> body) throws SQLException {
        switch(nameForm){
            case "backupTotal":
                new BackupTotalWrite(body).update();
                break;
        }
        return ResponseEntity.ok().build();
    }
}
