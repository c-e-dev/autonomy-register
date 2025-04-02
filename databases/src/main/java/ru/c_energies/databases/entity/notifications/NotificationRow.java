package ru.c_energies.databases.entity.notifications;

public record NotificationRow(int id, int time, String content, int is_read) {
}
