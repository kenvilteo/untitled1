package com.company.untitled1.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum ListBankFake implements EnumClass<String> {

    SOTAI("0000"),
    KHAC("1111");

    private final String id;

    ListBankFake(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static ListBankFake fromId(String id) {
        for (ListBankFake at : ListBankFake.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}