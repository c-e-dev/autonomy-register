package ru.c_energies.databases.sqlite;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Source<T> {
    void init() throws IOException;
    T session();
}

