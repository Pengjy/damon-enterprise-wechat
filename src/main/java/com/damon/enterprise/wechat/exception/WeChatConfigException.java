/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.damon.enterprise.wechat.exception;

/**
 * @author Damon
 * @version V1.0
 * @since 2021-01-11 17:47
 */
public class WeChatConfigException extends RuntimeException{

    private String properties;

    public WeChatConfigException() {
        super();
    }

    public WeChatConfigException(String properties, String message) {
        super(message);
        this.properties = properties;
    }
}
