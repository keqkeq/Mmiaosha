package com.miaoshaproject.controller;

import com.miaoshaproject.dataobject.AdminDO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * DESCRIBE
 *
 * @Author : wky
 * @since : 2019/12/14 21:38
 */

@Controller("admin")
@RequestMapping("/admin")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class AdminController extends BaseController {


    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private AdminService adminService;

    //用户登陆接口
    @RequestMapping(value = "/login", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    CommonReturnType login(@RequestParam(name="account")String account,
                           @RequestParam(name="password")String password) throws BusinessException {
        //入参检验
        if(org.apache.commons.lang3.StringUtils.isEmpty(account)||
                StringUtils.isEmpty(password)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        AdminDO adminDO = adminService.validateAdminLogin(account,password);
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN",true);

        return CommonReturnType.create(null);
    }

}
