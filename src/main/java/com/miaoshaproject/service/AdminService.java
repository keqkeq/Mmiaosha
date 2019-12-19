package com.miaoshaproject.service;

import com.miaoshaproject.dataobject.AdminDO;
import com.miaoshaproject.error.BusinessException;

/**
 * DESCRIBE
 *
 * @Author : wky
 * @since : 2019/12/14 21:58
 */
public interface AdminService {

    //登录操作
    AdminDO validateAdminLogin(String account,String password) throws BusinessException;

    //
}
