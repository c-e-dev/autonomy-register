package ru.c_energies.databases.entity.settings.internalnumber.write;

import ru.c_energies.databases.entity.settings.AbstractSettingWrite;

import java.util.Map;

import static ru.c_energies.databases.entity.settings.MapUiToDb.INTERNAL_NUMBER;

public class InternalNumberSettingWrite extends AbstractSettingWrite {
    public InternalNumberSettingWrite(Map<String, Object> map){
        super(INTERNAL_NUMBER, map);
    }
}
