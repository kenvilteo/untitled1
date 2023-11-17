package com.company.untitled1.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum ActiveStatus implements EnumClass<String> {

    ACTIVE("A"),
    DEACTIVE("D");

    private final String id;

    ActiveStatus(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static ActiveStatus fromId(String id) {
        for (ActiveStatus at : ActiveStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}