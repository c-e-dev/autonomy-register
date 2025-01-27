package ru.c_energies.databases.entity.files;

public record FileRow(String name, String extension, long size,
                       int createDate, String contentType, int appealType) {
}
