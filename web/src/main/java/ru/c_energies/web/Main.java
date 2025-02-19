package ru.c_energies.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.c_energies.core.schedul.BackupScheduler;
import ru.c_energies.databases.sqlite.Source;
import ru.c_energies.databases.sqlite.SqliteDataSource;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
        Source source = new SqliteDataSource();
        source.init();

        BackupScheduler scheduler = new BackupScheduler();
        scheduler.init();
    }
}
