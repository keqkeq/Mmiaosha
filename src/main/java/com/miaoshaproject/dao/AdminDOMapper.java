package com.miaoshaproject.dao;

import com.miaoshaproject.dataobject.AdminDO;

public interface AdminDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminDO record);

    int insertSelective(AdminDO record);

    AdminDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminDO record);

    int updateByPrimaryKey(AdminDO record);

    AdminDO selectByAccount(String account);
}