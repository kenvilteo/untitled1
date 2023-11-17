package com.company.untitled1.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum BranchType implements EnumClass<String> {

    HO("HO"),
    L1("L1"),
    L2("L2");

    private final String id;

    BranchType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static BranchType fromId(String id) {
        for (BranchType at : BranchType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}