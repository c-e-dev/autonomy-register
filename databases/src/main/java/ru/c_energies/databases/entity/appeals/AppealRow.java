package ru.c_energies.databases.entity.appeals;

public record AppealRow(int id, String title, String internalNumber,
                        String registerTrackNumber, String createDate, String dueDate,
                        String answered, String type) {

}
