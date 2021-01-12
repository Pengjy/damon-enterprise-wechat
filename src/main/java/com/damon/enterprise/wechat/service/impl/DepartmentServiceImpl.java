/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.damon.enterprise.wechat.service.impl;

import com.damon.enterprise.wechat.client.WeChatClient;
import com.damon.enterprise.wechat.service.AuthService;
import com.damon.enterprise.wechat.service.DepartmentService;
import com.damon.enterprise.wechat.utils.ResponseUtil;
import com.damon.enterprise.wechat.entity.Department;
import com.damon.enterprise.wechat.entity.Departments;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Damon
 * @version V1.0
 * @since 2021-01-11 17:21
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private AuthService authService;

    @Resource
    private WeChatClient weChatClient;

    @Override
    public List<Department> listAllDepartment() {
        String accessToken = authService.accessToken();

        Departments departments = weChatClient.departmentList(accessToken, null);
        return ResponseUtil.success(departments, departments::getDepartment);
    }
}
