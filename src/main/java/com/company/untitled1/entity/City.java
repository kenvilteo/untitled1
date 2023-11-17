package com.company.untitled1.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum City implements EnumClass<String> {

    HN("HN"),
    SG("SG");

    private final String id;

    City(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static City fromId(String id) {
        for (City at : City.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}