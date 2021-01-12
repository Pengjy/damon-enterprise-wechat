/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.damon.enterprise.wechat.service.impl;

import com.damon.enterprise.wechat.service.AuthService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.damon.enterprise.wechat.client.WeChatClient;
import com.damon.enterprise.wechat.entity.AuthToken;
import com.damon.enterprise.wechat.exception.WeChatInvokeException;
import com.damon.enterprise.wechat.properties.WeChatProperties;
import com.damon.enterprise.wechat.utils.ResponseUtil;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Damon
 * @version V1.0
 * @since 2021-01-11 16:55
 */
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Resource
    private WeChatProperties weChatProperties;

    @Resource
    private WeChatClient weChatClient;

    private LoadingCache<String, String> cache = null;

    @Override
    public String accessToken() {
        if (null == cache) {
            cache = CacheBuilder.newBuilder().expireAfterWrite(weChatProperties.getTokenExpires(), TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) {
                        return loadAccessToken(key);
                    }
                });
        }

        try {
            return cache.get(weChatProperties.getCorpId());
        } catch (ExecutionException e) {
            log.error("获取token失败", e);
            throw new WeChatInvokeException(-1, "获取token失败");
        }
    }

    private String loadAccessToken(String corpId) {
        log.info("refresh token");
        AuthToken authToken = weChatClient.getToken(corpId, weChatProperties.getCorpSecret());
        return ResponseUtil.success(authToken, authToken::getAccessToken);
    }
}
