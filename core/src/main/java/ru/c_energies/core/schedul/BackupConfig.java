package ru.c_energies.core.schedul;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.c_energies.databases.entity.settings.backup.read.BackupTotalSettings;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BackupConfig {
    private final Logger LOG = LogManager.getLogger(BackupConfig.class);
    public void start() throws SQLException, ParseException {
        LOG.trace("Start method start()");
        BackupTotalSettings.Inner backupTotalSettings = new BackupTotalSettings().get();
        LOG.trace("backupTotalSettings.use() = {}", backupTotalSettings.use());

        LOG.trace("End method start()");
    }
}
