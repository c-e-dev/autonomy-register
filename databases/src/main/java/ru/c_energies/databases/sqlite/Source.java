package ru.c_energies.databases.sqlite;

public interface Source<T> {
    void init();
    T session();
}

