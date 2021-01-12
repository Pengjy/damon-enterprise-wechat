package com.damon.enterprise.wechat.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import lombok.Data;

/**
 * @author Damon
 * @version V1.0
 * @since 2021-01-11 16:34
 */
@Data
public class BaseEntity implements Serializable {

    @JsonProperty("errcode")
    private Integer errCode;

    @JsonProperty("errmsg")
    private String errMsg;
}
