package ru.c_energies.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.c_energies.core.schedul.BackupScheduler;
import ru.c_energies.databases.sqlite.Source;
import ru.c_energies.databases.sqlite.SqliteDataSource;
import ru.c_energies.update.UpdateApp;
import ru.c_energies.update.UpdateDatabase;
import ru.c_energies.update.WorkGit;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws ParseException, SQLException, IOException {
        SpringApplication.run(Main.class);
        Source source = new SqliteDataSource();
        source.init();

        BackupScheduler scheduler = new BackupScheduler();
        scheduler.init();
        UpdateDatabase update = new UpdateDatabase();
        update.start();
        //WorkGit workGit = new WorkGit("https://gitlab.com/autonomy-register/autonomy-register-v1.git");
        //workGit.lastTag();
        UpdateApp updateApp = new UpdateApp();
        updateApp.start();
    }
}
