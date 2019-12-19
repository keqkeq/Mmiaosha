package com.miaoshaproject.service;

import com.miaoshaproject.service.model.PromoModel;

/**
 * DESCRIBE
 *
 * @Author : wky
 * @since : 2019/12/10 22:33
 */
public interface PromoService {

    //根据itemId获取即将进行的或正在进行的
    PromoModel getPromoByItemId(Integer itemId);
}
