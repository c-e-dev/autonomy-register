package ru.c_energies.core.schedul;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.c_energies.databases.entity.settings.backup.read.BackupTotalSettings;

import java.sql.SQLException;

public class BackupConfig {
    private final Logger LOG = LogManager.getLogger(BackupConfig.class);
    public void start() throws SQLException {
        LOG.trace("Start method start()");
        BackupTotalSettings.Inner backupTotalSettings = new BackupTotalSettings().get();
        boolean use = backupTotalSettings.use();
        LOG.trace("backupTotalSettings.use() = {}", use);
        if(use){
            LOG.debug("Starting backup");

            LOG.debug("End backup");
        }

        LOG.trace("End method start()");
    }
}
