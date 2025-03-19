package ru.c_energies.databases.sqlite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Создание коннекта к БД SQLite
 */
public class SqliteDataSource implements Source<Connection> {
    private final Logger LOG = LogManager.getLogger(SqliteDataSource.class);
    private static Connection conn;
    @Override
    public void init() throws IOException {
        LOG.info("Initialization Oracle Connection start");
        String rootPath = Paths.get("").toAbsolutePath().toString() + "/";
        LOG.debug("rootPath = {}", rootPath);
        String appConfigPath = rootPath + "application.properties";
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(appConfigPath));
        String key = appProps.getProperty("db.key");
        try{
            String profile = System.getenv("SPRING_ENV");
            if ("prod".equals(profile)) {
                String SQL_PATH = System.getenv("SQL_PATH");
                if(SQL_PATH == null){
                    conn = DriverManager.getConnection("jdbc:sqlite:reestr.db?cipher=sqlcipher&key="+key+"&legacy=4");
                }else{
                    conn = DriverManager.getConnection("jdbc:sqlite:" + SQL_PATH + "reestr.db?cipher=sqlcipher&key="+key+"&legacy=4");
                }
            }else {
                conn = DriverManager.getConnection("jdbc:sqlite:"+rootPath+"reestr_dev.db?cipher=sqlcipher&key="+key+"&legacy=4");
            }
            if (conn != null) {
                LOG.info("Connected to the database!");
            } else {
                LOG.info("Failed to make connection!");
            }
        }catch (SQLException e){
            LOG.error("SQL State: {}\n{}", e.getSQLState(), e.getMessage());
            try {
                conn.close();
            }catch(SQLException e1) {
                LOG.error("SQL State: {}\n{}", e1.getSQLState(), e1.getMessage());
            }
        }catch (Exception e){
            LOG.error(e.getMessage());
            try {
                conn.close();
            }catch(SQLException e1) {
                LOG.error("SQL State: {}\n{}", e1.getSQLState(), e1.getMessage());
            }
        }
    }

    @Override
    public Connection session() {
        return conn;
    }
}
