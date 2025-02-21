package ru.c_energies.core.schedul;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.c_energies.databases.entity.settings.backup.read.BackupTotalSettings;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

public class BackupScheduler {
    private final Logger LOG = LogManager.getLogger(BackupScheduler.class);
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void init() throws ParseException, SQLException {
        LOG.trace("Init BackupScheduler");
        final Runnable beeper = new Runnable() {
            public void run() {
                try {
                    new BackupConfig().start();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        LocalDateTime date = LocalDateTime.now();
        int endDayTime = date.toLocalDate().atTime(LocalTime.MAX).toLocalTime().toSecondOfDay();
        LOG.trace("end day seconds = {}", endDayTime);
        int currentTime = date.toLocalTime().toSecondOfDay();
        LOG.trace("current seconds day = {}", currentTime);
        BackupTotalSettings.Inner backupTotalSettings = new BackupTotalSettings().get();
        LocalTime lt = LocalTime.parse(backupTotalSettings.starttime(), DateTimeFormatter.ofPattern("HH:mm"));
        int schedulerTime = lt.toSecondOfDay();
        LOG.trace("schedulerTime = {}", schedulerTime);
        int initialDelay = 0;
        if(currentTime > schedulerTime){
            initialDelay = (endDayTime - currentTime) + schedulerTime;
        }else{
            initialDelay = schedulerTime - currentTime;
        }
        LOG.trace("initialDelay = {}", initialDelay);
        final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, initialDelay, 86400, SECONDS);
    }
}
