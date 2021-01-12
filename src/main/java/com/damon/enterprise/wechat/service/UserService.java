/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.damon.enterprise.wechat.service;

import com.damon.enterprise.wechat.entity.User;

import java.util.List;

/**
 * @author Damon
 * @version V1.0
 * @since 2021-01-11 16:53
 */
public interface UserService {

    /**
     * 根据部门获取用户列表
     *
     * @param departmentId
     * @param fetchChild
     * @return
     */
    List<User> listUserByDepartment(Integer departmentId, Boolean fetchChild);
}
