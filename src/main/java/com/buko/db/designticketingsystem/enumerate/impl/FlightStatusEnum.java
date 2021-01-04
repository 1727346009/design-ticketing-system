package com.buko.db.designticketingsystem.enumerate.impl;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author buko
 */
public enum FlightStatusEnum implements IEnum<Integer> {
    /**
     *
     */
    CANCEL("已取消", -1),
    NO_TAKE_OFF("未起飞", 0),
    DELAY("延误", 1),
    TAKE_OFF("起飞", 2),
    END("已落地", 3);

    @JsonValue
    private final String name;
    @EnumValue
    private final Integer code;

    @JsonCreator
    FlightStatusEnum(String name, Integer code) {
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
