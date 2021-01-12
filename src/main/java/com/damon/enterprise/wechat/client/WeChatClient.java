/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.damon.enterprise.wechat.client;

import com.damon.enterprise.wechat.entity.Users;
import com.damon.enterprise.wechat.entity.AuthToken;
import com.damon.enterprise.wechat.entity.Departments;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * @author Damon
 * @version V1.0
 * @since 2021-01-11 16:21
 */
public interface WeChatClient {

    /**
     * 获取授权token
     *
     * @param corpId
     * @param corpSecret
     * @return
     */
    @Headers({ "Content-Type: application/json" })
    @RequestLine("GET /gettoken?corpid={corpid}&corpsecret={corpsecret}")
    AuthToken getToken(@Param("corpid") String corpId, @Param("corpsecret") String corpSecret);

    /**
     * 获取部门列表
     *
     * @param accessToken
     * @param id
     * @return
     */
    @Headers({ "Content-Type: application/json" })
    @RequestLine("GET /department/list?access_token={access_token}&id={id}")
    Departments departmentList(@Param("access_token") String accessToken, @Param("id") Integer id);

    /**
     * 获取部门用户列表
     *
     * @param accessToken
     * @param departmentId
     * @param fetchChild
     * @return
     */
    @Headers({ "Content-Type: application/json" })
    @RequestLine("GET /user/list?access_token={access_token}&department_id={department_id}&fetch_child={fetch_child}")
    Users userList(@Param("access_token") String accessToken, @Param("department_id") Integer departmentId,
        @Param("fetch_child") Integer fetchChild);

}
