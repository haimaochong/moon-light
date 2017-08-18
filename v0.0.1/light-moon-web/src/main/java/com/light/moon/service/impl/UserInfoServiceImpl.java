package com.light.moon.service.impl;

import org.springframework.stereotype.Service;

import com.light.moon.dao.UserInfoDao;
import com.light.moon.entity.UserInfoEntity;
import com.light.moon.service.UserInfoService;

/**
 * 用户信息服务实现类
 * 
 * @author lihh
 * 
 */
@Service
public class UserInfoServiceImpl extends AbsBaseService<UserInfoEntity, UserInfoDao> implements UserInfoService {

	@Override
	public UserInfoEntity queryUser(String loginName, String password) {
		return dao.queryUser(loginName, password);
	}

}
