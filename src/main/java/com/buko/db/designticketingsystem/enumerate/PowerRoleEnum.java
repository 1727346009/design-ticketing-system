package com.buko.db.designticketingsystem.enumerate;

/**
 * @author buko
 */
public enum PowerRoleEnum {
    /**
     * super
     */
    SUPER("super", -1),
    /**
     * user
     */
    USER("user", 0),
    /**
     * manager
     */
    MANAGER("manager", 1);

    private final String name;

    private final Integer code;

    PowerRoleEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public Integer getCode() {
        return code;
    }
}
