package ru.c_energies.web.pages;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import ru.c_energies.databases.entity.notifications.NotificationTable;
import ru.c_energies.databases.entity.version.VersionTable;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class Notifications {
    @GetMapping(value = "/notifications/version")
    public ResponseEntity<Object> version() throws SQLException {
        VersionTable versionTable = new VersionTable();
        String currentVersion = versionTable.list().get(0).value();
        Map<String, String> response = new HashMap<>();
        response.put("version", "1.0.0");
        return ResponseEntity.ok().body(response);
    }
}
