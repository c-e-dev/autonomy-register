package ru.c_energies.web.models.files;

public record FileRow(String name, String extension,
                      int size, int createDate) {
}
