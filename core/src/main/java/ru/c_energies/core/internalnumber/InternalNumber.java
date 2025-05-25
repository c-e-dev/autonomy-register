package ru.c_energies.core.internalnumber;

import ru.c_energies.databases.entity.settings.internalnumber.read.InternalNumberSetting;

import java.sql.SQLException;
import java.time.Year;

/**
 * Присваивание внутренного номера обращения (входящее, исходящее) при создании объекта в системе
 */
public class InternalNumber {
    private final String typeAppeal;
    public InternalNumber(String typeAppeal){
        this.typeAppeal = typeAppeal;
    }
    public void save() throws SQLException {
        InternalNumberSetting.Inner internalNumber = new InternalNumberSetting().get();
        String format = internalNumber.format();
        String[] formatArray = format.split("-");
        String intNumber = "";
        for(String a : formatArray){
            if(a.equals("%type")){
                intNumber += intNumber + this.typeAppeal + "-";
            }
            if(a.equals("%year")){
                intNumber += intNumber + String.valueOf(Year.now().getValue());
            }
            if(a.equals("%increment")){
                intNumber += intNumber + String.valueOf(Year.now().getValue());
            }
        }
    }

    private String increment(){

        return "";
    }
}
