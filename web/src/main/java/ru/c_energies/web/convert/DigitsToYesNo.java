package ru.c_energies.web.convert;

import java.util.HashMap;
import java.util.Map;

public class DigitsToYesNo {
    private final Map<Integer, String> mapDigits = new HashMap<>(){{
        put(0, "Нет");
        put(1, "Да");
    }};
    private int value;
    public DigitsToYesNo(int value){
        this.value = value;
    }
    public String value(){
        return this.mapDigits.get(value);
    }
}
