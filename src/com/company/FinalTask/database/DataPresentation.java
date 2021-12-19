package com.company.FinalTask.database;

import java.io.File;

public class DataPresentation {
    public static String getPathXML(String path){
        return "src"+ File.separator+"com"+File.separator+"company"
                +File.separator+"FinalTask"+File.separator+"xml"+File.separator+path;
    }

    public static String getPathXSD(String path){
        return "src"+File.separator+"com"+File.separator+"company"
                +File.separator+"FinalTask"+File.separator+"xsd"+File.separator+path;
    }
}
