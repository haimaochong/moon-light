package com.light.moon.service;

import java.util.List;

import com.light.moon.dao.OrderDao;
import com.light.moon.entity.OrderEntity;

/**
 * 投资记录服务接口
 * 
 * @author lihh
 * 
 */
public interface OrderService extends BaseService<OrderEntity, OrderDao> {

	public Integer queryInvestNumByPlatform(Long platformId);

	public List<OrderEntity> queryByUserId(Long userId);

}
