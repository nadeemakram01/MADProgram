package com.example.web.madprogram.JavaBean;

public class Term {
    private String name;
    private String definition;
    int imageID;

    public Term(){

    }
    public Term(String name, String definition) {
        this.name = name;
        this.definition = definition;
    }
    public Term(String name, String definition, int imageID) {
        this.name = name;
        this.definition = definition;
        this.imageID = imageID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
