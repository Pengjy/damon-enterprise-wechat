/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.damon.enterprise.wechat.service;

import com.damon.enterprise.wechat.entity.Department;

import java.util.List;

/**
 * @author Damon
 * @version V1.0
 * @since 2021-01-11 16:53
 */
public interface DepartmentService {

    /**
     * 获取所有的部门
     * @return
     */
    List<Department> listAllDepartment();

}
