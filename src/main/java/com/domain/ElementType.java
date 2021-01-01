package com.domain;

import lombok.Getter;

public enum ElementType {

    _int("int", 4),
    _float("float", 4),
    _short("short", 2),
    _long("long", 8),
    _char("char", 2),
    _boolean("boolean", 4),
    _byte("byte", 1),
    _double("double", 8),
    _body_size("int", 4),
    _string("String", 0), // also need bodySize in the pre bodySize
    _byte_array("byteArray", 0), //need bodySize in the pre variable
    ;

    ElementType(String type, int length) {
        this.type = type;
        this.length = length;
    }

    @Getter
    private String type;
    @Getter
    private int length;

}
