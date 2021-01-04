package com.buko.db.designticketingsystem.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author buko
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShowManagerVO {
    private Long id;

    private String realNAME;

    private String username;
}
