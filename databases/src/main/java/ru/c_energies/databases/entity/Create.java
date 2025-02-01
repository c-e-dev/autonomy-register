package ru.c_energies.databases.entity;

import java.sql.SQLException;

public interface Create<T> {
    T insert() throws SQLException;
    void update();
    int id();
}
