package com.miaoshaproject.error;

/**
 * DESCRIBE
 *
 * @Author : wky
 * @since : 2019/10/25 10:35
 */
public interface CommonError {
    public int getErrCode();
    public  String getErrMsg();
    public CommonError setErrMsg(String errMsg);

}
