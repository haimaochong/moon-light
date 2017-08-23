package com.light.moon.service.impl;

import org.springframework.stereotype.Service;

import com.light.moon.dao.InvestDao;
import com.light.moon.entity.InvestEntity;
import com.light.moon.service.InvestService;

/**
 * 投资记录服务实现类
 * 
 * @author lihh
 * 
 */
@Service
public class InvestServiceImpl extends AbsBaseService<InvestEntity, InvestDao> implements InvestService {

	@Override
	public Integer queryInvestNumByPlatform(Long platformId) {
		return dao.queryInvestNumByPlatform(platformId);
	}

}
