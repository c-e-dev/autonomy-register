package ru.c_energies.core.schedul;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

public class BackupScheduler {
    private final Logger LOG = LogManager.getLogger(BackupScheduler.class);
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void init() {
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
        final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 10, 600, SECONDS);
        scheduler.schedule(new Runnable() {
            public void run() {
                beeperHandle.cancel(true);
            }
        }, 60 * 60, SECONDS);
    }
}
