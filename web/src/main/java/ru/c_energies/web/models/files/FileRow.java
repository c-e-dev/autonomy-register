package ru.c_energies.web.models.files;

public record FileRow(String name, String extension,
                      long size, int createDate, String contentType) {
}
