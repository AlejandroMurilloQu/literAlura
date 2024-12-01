package com.alura.literAlura.shared;

public enum Languages {
    EN("English"),
    ES("Español"),
    FR("Frances"),
    FI("Finlandés"),
    CA("Catalán"),
    ZH("Chino");

    private String name;

    public String getName() {
        return name;
    }

    Languages(String name){
        this.name = name;
    }
    public static Languages getFromString(String language){
        for (Languages l : Languages.values()){
            if(l.toString().equalsIgnoreCase(language)) return l;
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + language);
    }
}
