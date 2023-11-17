package com.company.untitled1.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum Sex implements EnumClass<String> {

    M("M"),
    F("F");

    private final String id;

    Sex(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static Sex fromId(String id) {
        for (Sex at : Sex.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}