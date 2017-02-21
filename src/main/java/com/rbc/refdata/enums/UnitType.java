package com.rbc.refdata.enums;

public enum UnitType {

    GRAM("GRAM"),
    UNIT("UNIT");

    private final String name;

    UnitType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
