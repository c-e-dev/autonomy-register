package ru.c_energies.web.models.appeals;

public record AppealRow(int id, String title, String internalNumber,
                        String registerTrackNumber, String createDate, String dueDate,
                        String answered) {
}
