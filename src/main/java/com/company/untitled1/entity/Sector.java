package com.company.untitled1.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum Sector implements EnumClass<String> {

    KT("001"),
    CN("002");

    private final String id;

    Sector(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static Sector fromId(String id) {
        for (Sector at : Sector.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}