package ru.c_energies.utils.converters;

import java.util.HashMap;
import java.util.Map;

public class ThemeStatuses {
    private final int value;
    private final Map<Integer, String> map = new HashMap<>(){{
        put(1, "Не решено");
        put(2, "Решено");
        put(3, "Отложено");
        put(4, "В работе");
    }};
    public ThemeStatuses(int value){
        this.value = value;
    }

    /**
     * Из базы в html
     * @return
     */
    public String value(){
        return this.map.get(this.value);
    }
    /**
     * Из html в базу
     * @return
     */
    public int reverse(String value){
        Map<String, Integer> reverseMap = new HashMap<>();
        for(Map.Entry<Integer, String> entry : this.map.entrySet()){
            reverseMap.put(entry.getValue(), entry.getKey());
        }
        return reverseMap.get(value) == null ? 0 : reverseMap.get(value);
    }

}
