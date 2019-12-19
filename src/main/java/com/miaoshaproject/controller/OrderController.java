package com.miaoshaproject.controller;

import com.miaoshaproject.controller.viewobject.ItemVO;
import com.miaoshaproject.controller.viewobject.OrderVO;
import com.miaoshaproject.controller.viewobject.UserVO;
import com.miaoshaproject.dao.ItemDOMapper;
import com.miaoshaproject.dao.UserDOMapper;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.OrderService;
import com.miaoshaproject.service.model.OrderModel;
import com.miaoshaproject.service.model.UserModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DESCRIBE
 *
 * @Author : wky
 * @since : 2019/12/9 22:38
 */
@Controller("order")
@RequestMapping("/order")
@CrossOrigin(origins = {"*"},allowCredentials = "true",allowedHeaders = "*")
public class OrderController extends BaseController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private ItemDOMapper itemDOMapper;

    @Autowired
    private UserController userController;

    @Autowired
    private ItemController itemController;


    //封装下单请求
    //用户注册接口
    @RequestMapping(value = "/createorder", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType createOrder(@RequestParam(name="itemId")Integer itemId,
                                        @RequestParam(name = "amount")Integer amount,
                                        @RequestParam(name="promoId",required = false)Integer promoId) throws BusinessException {

        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");

        if(isLogin == null || !isLogin.booleanValue()){
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN,"用户还未登陆,不能下单");
        }

        //获取用户的登陆信息
        UserModel userModel = (UserModel)httpServletRequest.getSession().getAttribute("LOGIN_USER");
        //System.out.println("id:"+userModel.getId());

        OrderModel orderModel = orderService.createOrder(userModel.getId(),itemId,promoId,amount);
        return CommonReturnType.create(null);
    }

    //根据用户id搜索相关订单信息
    @RequestMapping(value = "/getorderinfo", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getAllOrderByUserId() throws BusinessException {
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");

        if(isLogin == null || !isLogin.booleanValue()){
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN,"用户还未登陆,不能查看订单信息");
        }

        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");

        Integer userId = userModel.getId();

        //获取model的list
        List<OrderModel> orderModels = orderService.getAllOrderByUserId(userId);
        if(orderModels.isEmpty()||orderModels==null){
            return CommonReturnType.create("暂无订单");
        }
        List<OrderVO> orderVOS =  orderModels.stream().map(orderModel -> {
            OrderVO orderVO = null;
            try {
                orderVO = this.convertVOFromModel(orderModel);
            } catch (BusinessException e) {
                e.printStackTrace();
            }
            return orderVO;
        }).collect(Collectors.toList());

        return  CommonReturnType.create(orderVOS);
    }

    private OrderVO convertVOFromModel(OrderModel orderModel) throws BusinessException {
        if(orderModel == null){
            return null;
        }
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(orderModel,orderVO);
        orderVO.setUserVO((UserVO) userController.getUser(orderModel.getUserId()).getData());
        orderVO.setItemVO((ItemVO) itemController.getItem(orderModel.getItemId()).getData());
        return orderVO;
    }
}
