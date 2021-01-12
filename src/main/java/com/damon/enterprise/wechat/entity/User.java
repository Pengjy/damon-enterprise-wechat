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
import java.util.List;

import lombok.Data;

/**
 * @author Damon
 * @version V1.0
 * @since 2021-01-11 16:44
 */
@Data
public class User implements Serializable {

    private String userid;

    private String name;

    private List<Integer> department;

    private List<Integer> order;

    private String position;

    private String mobile;

    private String gender;

    private String email;

    private String avatar;

    @JsonProperty("main_department")
    private Long mainDepartment;

    @JsonProperty("qr_code")
    private String qrCode;

    @JsonProperty("thumb_avatar")
    private String thumbAvatar;

}
