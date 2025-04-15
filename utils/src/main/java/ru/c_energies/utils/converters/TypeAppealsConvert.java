package ru.c_energies.utils.converters;

import java.util.HashMap;
import java.util.Map;

public class TypeAppealsConvert {
    private final String value;
    private final Map<String, String> map = new HashMap<>(){{
        put("none", "Не выбрано");
        put("inbound", "Входящее");
        put("outbound", "Исходящее");
    }};

    public TypeAppealsConvert(String value){
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
    public String reverse(String value){
        Map<String, String> reverseMap = new HashMap<>();
        for(Map.Entry<String, String> entry : this.map.entrySet()){
            reverseMap.put(entry.getValue(), entry.getKey());
        }
        return reverseMap.get(value) == null ? "none" : reverseMap.get(value);
    }
}
