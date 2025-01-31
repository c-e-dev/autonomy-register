package ru.c_energies.databases.entity;

import java.sql.SQLException;
import java.util.List;

/**
 * Формирует таблицу
 * @param <T>
 */
public interface TableSql<T> {
    List<T> list()  throws SQLException;

}
