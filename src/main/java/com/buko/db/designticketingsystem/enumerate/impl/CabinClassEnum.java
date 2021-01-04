package com.buko.db.designticketingsystem.enumerate.impl;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author buko
 */
public enum CabinClassEnum implements IEnum<Integer> {
    /**
     *
     */
    ECONOMY("经济舱", 0),
    BUSINESS("商务舱", 1),
    FIRST("头等舱", 2);

    @JsonValue
    private final String name;
    @EnumValue
    private final Integer code;

    @JsonCreator
    CabinClassEnum(String name, Integer code) {
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
