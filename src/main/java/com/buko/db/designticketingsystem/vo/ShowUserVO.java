package com.buko.db.designticketingsystem.vo;

import com.buko.db.designticketingsystem.enumerate.impl.CredentialsTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author buko
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShowUserVO {
    private Long id;

    private String username;

    private String realName;

    private String credentials;

    private CredentialsTypeEnum credentialsType;

    private String phoneNumber;
}
