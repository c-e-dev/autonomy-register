package ru.c_energies.web.convert;

import java.util.HashMap;
import java.util.Map;

public class DigitsToYesNo {
    private final Map<Integer, String> mapDigits = new HashMap<>(){{
        put(1, "Нет");
        put(2, "Да");
    }};
    private int value;
    public DigitsToYesNo(int value){
        this.value = value;
    }
    public String value(){
        return this.mapDigits.get(value);
    }
    public int reverse(String value){
        Map<String, Integer> reverseMap = new HashMap<>();
        for(Map.Entry<Integer, String> entry : this.mapDigits.entrySet()){
            reverseMap.put(entry.getValue(), entry.getKey());
        }
        return reverseMap.get(value) == null ? 0 : reverseMap.get(value);
    }
}
