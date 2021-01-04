package com.buko.db.designticketingsystem.enumerate.impl;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
/**
 * @author buko
 */
public enum PermissionEnum implements IEnum<Integer> {
    /**
     *
     */
    DISABLE("禁用", -1),
    COMMON("普通业务员", 0),
    SUPER("超级管理员", 1);

    @JsonValue
    private final String name;
    @EnumValue
    private final Integer code;

    @JsonCreator
    PermissionEnum(String name, Integer code) {
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
