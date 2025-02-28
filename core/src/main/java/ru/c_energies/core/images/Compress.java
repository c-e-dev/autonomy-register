package ru.c_energies.core.images;

import com.itextpdf.text.DocumentException;

import java.io.IOException;

public interface Compress {
    byte[] start()  throws IOException, DocumentException;
    int size();
}
