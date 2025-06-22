package ru.c_energies.databases.entity.documents;

public record DocumentRow(int id, int documentId, int appealId, String name,
                          String internalNumber, String internalNumberRule, int fileId) {
}
