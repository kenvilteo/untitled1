package com.company.untitled1.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum DirectMenber implements EnumClass<String> {

    DIRECT("D"),
    INDIRECT("ID"),
    STOP("S");

    private final String id;

    DirectMenber(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static DirectMenber fromId(String id) {
        for (DirectMenber at : DirectMenber.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}