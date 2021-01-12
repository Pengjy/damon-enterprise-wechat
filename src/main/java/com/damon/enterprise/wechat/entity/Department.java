/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
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
public class Department implements Serializable {

    private Integer id;

    private String name;

    @JsonProperty("name_en")
    private String nameEn;

    @JsonProperty("parentid")
    private Integer parentId;

    private Integer order;

}


