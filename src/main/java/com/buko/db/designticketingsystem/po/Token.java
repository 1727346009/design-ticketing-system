package com.buko.db.designticketingsystem.po;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Mr.徐健威
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Token implements Serializable {
    private String authenticate;
}
