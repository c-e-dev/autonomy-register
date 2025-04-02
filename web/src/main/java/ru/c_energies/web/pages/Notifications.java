package ru.c_energies.web.pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.c_energies.databases.entity.notifications.NotificationRow;
import ru.c_energies.databases.entity.version.VersionTable;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class Notifications {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping(value = "/notifications/version")
    public ResponseEntity<Object> version() throws SQLException {
        VersionTable versionTable = new VersionTable();
        String currentVersion = versionTable.list().get(0).value();
        Map<String, String> response = new HashMap<>();
        response.put("version", "1.0.0");
        return ResponseEntity.ok().body(response);
    }

    @MessageMapping("/notifications")
    public void processMessage(@Payload NotificationRow notificationRow) {
        messagingTemplate.convertAndSendToUser(
                String.valueOf(notificationRow.id()),"/queue/messages",
                notificationRow);
    }
}
