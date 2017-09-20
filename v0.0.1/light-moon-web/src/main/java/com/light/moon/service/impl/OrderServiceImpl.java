package com.light.moon.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.light.moon.dao.OrderDao;
import com.light.moon.entity.OrderEntity;
import com.light.moon.service.OrderService;

/**
 * 投资记录服务实现类
 * 
 * @author lihh
 * 
 */
@Service
public class OrderServiceImpl extends AbsBaseService<OrderEntity, OrderDao> implements OrderService {

	@Override
	public Integer queryInvestNumByPlatform(Long platformId) {
		return dao.queryInvestNumByPlatform(platformId);
	}
	
	@Override
	public List<OrderEntity> queryByUserId(Long userId) {
		return dao.queryByUserId(userId);
	}

	@Override
	public BigDecimal queryAllAmount() {
		return dao.queryAllAmount();
	}

}
