/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.damon.enterprise.wechat.utils;

import com.damon.enterprise.wechat.entity.BaseEntity;
import com.damon.enterprise.wechat.exception.WeChatInvokeException;

import java.util.concurrent.Callable;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Damon
 * @version V1.0
 * @since 2021-01-11 17:13
 */
@Slf4j
public class ResponseUtil {

    private ResponseUtil() {
        //doSomething
    }

    @SneakyThrows
    public static <T> T success(BaseEntity baseEntity, Callable<T> callable) {
        if (null != baseEntity.getErrCode() && !baseEntity.getErrCode().equals(0)) {
            log.error("微信接口调用失败,错误码:{};错误信息:{}",baseEntity.getErrCode(),baseEntity.getErrMsg());
            throw new WeChatInvokeException(baseEntity.getErrCode(),baseEntity.getErrMsg());
        }
        return callable.call();
    }
}
