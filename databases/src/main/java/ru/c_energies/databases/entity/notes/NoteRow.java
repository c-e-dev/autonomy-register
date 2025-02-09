package ru.c_energies.databases.entity.notes;

public record NoteRow(int id, String title, String content, int appealId, int themeId, String createDate) {
}
