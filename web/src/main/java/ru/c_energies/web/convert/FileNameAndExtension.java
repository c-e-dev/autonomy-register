package ru.c_energies.web.convert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileNameAndExtension {
    private final String fileName;
    public FileNameAndExtension(String fileName){
        this.fileName = fileName;
    }
    public String extension(){
        String result = "";
        Pattern pattern = Pattern.compile( ".+\\.(?i)(.*)$" );
        Matcher matcher = pattern.matcher(this.fileName);
        while(matcher.find()){
            result = matcher.group(1);
        }
        return result;
    }
    public String name() {
        String result = "";
        Pattern pattern = Pattern.compile( "(.+)\\.(?i)(.*)$" );
        Matcher matcher = pattern.matcher(this.fileName);
        while(matcher.find()){
            result = matcher.group(1);
        }
        return result;
    }
}
