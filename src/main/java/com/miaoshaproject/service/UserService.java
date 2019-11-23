package com.miaoshaproject.service;

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

}
