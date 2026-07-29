package br.com.vini.library.enums;

import lombok.Getter;

@Getter
public enum AgeGroupEnum {
    L("L"),
    A10("10"),
    A12("12"),
    A14("14"),
    A16("16"),
    A18("18");

    private final String value;

    AgeGroupEnum(String value) {
        this.value = value;
    }
}
