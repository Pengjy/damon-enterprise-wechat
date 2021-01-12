/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.damon.enterprise.wechat.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @author Damon
 * @version V1.0
 * @since 2021-01-11 16:09
 */
@Data
@ConfigurationProperties(prefix = WeChatProperties.PREFIX)
public class WeChatProperties {

    public static final String PREFIX="wechat";

    private String url;

    private String corpId;

    private String corpSecret;

    private Integer tokenExpires;

    /**
     * 是否开启重试
     */
    private Boolean retry = false;
    /**
     * 最大尝试次数
     */
    private Integer maxAttempts = 3;

    /**
     * 时间间隔
     */
    private Long period = 5000L;

    /**
     * 最大时间间隔
     */
    private Long maxPeriod = 5000L;

    /**
     * 连接超时时间
     */
    private Integer connectTimeOut = 3000;

    /**
     * 读取超时时间
     */
    private Integer readTimeout = 3000;
}
