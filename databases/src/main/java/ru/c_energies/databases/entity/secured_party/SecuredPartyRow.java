package ru.c_energies.databases.entity.secured_party;

public record SecuredPartyRow(int id, int amount, int appealId, String name, String createDate, String applyDate, int used ) {
}
