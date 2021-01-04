package com.buko.db.designticketingsystem.enumerate.impl;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author buko
 */
public enum CredentialsTypeEnum implements IEnum<Integer> {
    /**
     *
     */
    IdentityCard("身份证", 0),
    PASSPORT("护照", 1);

    @JsonValue
    private final String name;
    @EnumValue
    private final Integer code;

    @JsonCreator
    CredentialsTypeEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Integer getValue() {
        return code;
    }
}
