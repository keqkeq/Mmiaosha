package com.miaoshaproject.service;

import com.miaoshaproject.dataobject.UserDO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.UserModel;

/**
 * DESCRIBE
 *
 * @Author : wky
 * @since : 2019/9/18 10:32
 */
public interface UserService {
    /**
     * 通过用户Id获取用户对象的方法
     */
    UserModel getUserById(Integer id);

    void register(UserModel userModel) throws BusinessException;

    /**
     * password是非加密密码
     *
     * @param telphone
     * @param encrptPassword
     * @throws BusinessException
     */
    UserModel validateLogin(String telphone, String encrptPassword) throws BusinessException;

}
