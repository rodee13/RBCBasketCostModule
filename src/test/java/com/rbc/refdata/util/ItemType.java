package com.rbc.refdata.util;

public enum ItemType {

    BANANA(1),
    ORANGE(2),
    APPLE(3),
    LEMON(4),
    PEACH(5);

    private final int id;

    ItemType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

}
