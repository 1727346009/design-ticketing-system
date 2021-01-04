package com.buko.db.designticketingsystem.enumerate.impl;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author buko
 */
public enum OrderFormStatusEnum implements IEnum<Integer> {
    /**
     *
     */
    OVER("支付超时", -1),
    UNPAIR("未支付", 0),
    NOT_TAKE_OFF("未起飞", 1),
    REBOOK("已改签", 2),
    CANCEL("退票完成", 3),
    FINISH("已完成", 4);

    @JsonValue
    private final String name;
    @EnumValue
    private final Integer code;
    @JsonCreator
    OrderFormStatusEnum(String name, Integer code) {
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
