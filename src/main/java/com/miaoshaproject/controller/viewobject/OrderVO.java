package com.miaoshaproject.controller.viewobject;

import java.math.BigDecimal;

/**
 * DESCRIBE
 *
 * @Author : wky
 * @since : 2019/12/16 16:26
 */
public class OrderVO {
    //单号
    private String id;

    //购买用户
    private UserVO userVO;

    //购买商品
    private ItemVO itemVO;

    //若非空，则表示是以秒杀商品方式下单
    private Integer promoId;

    //购买商品单价,若promoId非空，则表示秒杀商品价格
    private BigDecimal itemPrice;


    //购买数量
    private Integer amount;


    //购买金额
    private BigDecimal orderPrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public ItemVO getItemVO() {
        return itemVO;
    }

    public void setItemVO(ItemVO itemVO) {
        this.itemVO = itemVO;
    }

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }


}
