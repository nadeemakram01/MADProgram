package com.example.web.madprogram.JavaBean;

/**
 * This is similar to the Movie Object class we discussed in class
 * This will store the information about each item in the list
 *
 */
public class DataType {

    private String name;
    private String definition;

    public DataType(){

    }
    public DataType(String name, String definition){
        this.definition = definition;
        this.name = name;
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

    @Override
    public String toString() {
        return this.name;
    }
}
