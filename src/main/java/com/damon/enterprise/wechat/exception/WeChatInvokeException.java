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
 * @since 2021-01-11 17:17
 */
public class WeChatInvokeException extends RuntimeException {

    private Integer code;

    public WeChatInvokeException() {
        super();
    }

    public WeChatInvokeException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
