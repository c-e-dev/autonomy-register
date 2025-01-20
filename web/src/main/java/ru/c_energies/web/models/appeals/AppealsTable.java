package ru.c_energies.web.models.appeals;

import java.math.BigInteger;

public record AppealsTable(int number, String title, String internalNumber,
                           String registerTrackNumber) {
}
