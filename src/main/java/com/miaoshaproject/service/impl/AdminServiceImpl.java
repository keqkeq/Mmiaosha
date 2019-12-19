package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.AdminDOMapper;
import com.miaoshaproject.dataobject.AdminDO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.service.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DESCRIBE
 *
 * @Author : wky
 * @since : 2019/12/14 22:27
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDOMapper adminDOMapper;

    @Override
    public AdminDO validateAdminLogin(String account,String password) throws BusinessException {
        AdminDO adminDO = adminDOMapper.selectByAccount(account);
        if(adminDO == null){
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }

        if(!adminDO.getAccount().equals(account) || !StringUtils.equals(adminDO.getPassword(),password)){
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }

        return adminDO;
    }


}
