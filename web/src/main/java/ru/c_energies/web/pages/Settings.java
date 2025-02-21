package ru.c_energies.web.pages;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.c_energies.databases.entity.settings.backup.read.BackupGoogleDriveSettings;
import ru.c_energies.databases.entity.settings.backup.read.BackupTotalSettings;
import ru.c_energies.databases.entity.settings.backup.read.BackupYandexDiskSettings;
import ru.c_energies.databases.entity.settings.backup.write.BackupGoogleDriveWrite;
import ru.c_energies.databases.entity.settings.backup.write.BackupTotalWrite;
import ru.c_energies.databases.entity.settings.backup.write.BackupYandexDiskWrite;

import java.sql.SQLException;
import java.util.Map;

@Controller
public class Settings {
    @GetMapping(value = "/settings")
    public String list(Model model) throws SQLException {
        BackupTotalSettings.Inner backupTotalSettings = new BackupTotalSettings().get();
        BackupYandexDiskSettings.Inner backupYandexDiskSetting = new BackupYandexDiskSettings().get();
        BackupGoogleDriveSettings.Inner backupGoogleDriveSetting = new BackupGoogleDriveSettings().get();
        model.addAttribute("backupTotalSettings", backupTotalSettings);
        model.addAttribute("backupYandexDiskSetting", backupYandexDiskSetting);
        model.addAttribute("backupGoogleDriveSetting", backupGoogleDriveSetting);
        return "pages/settings";
    }

    @PostMapping(value = "/settings/{nameForm}")
    public ResponseEntity<Object> save(@PathVariable("nameForm") String nameForm, @RequestBody Map<String, Object> body) throws SQLException {
        switch(nameForm){
            case "backupTotal":
                new BackupTotalWrite(body).update();
                break;
            case "backupYandexDisk":
                new BackupYandexDiskWrite(body).update();
                break;
            case "backupGoogleDrive":
                new BackupGoogleDriveWrite(body).update();
                break;
        }
        return ResponseEntity.ok().build();
    }
}
