package com.light.moon.service;

import com.light.moon.dao.UserInfoDao;
import com.light.moon.entity.UserInfoEntity;

/**
 * 用户信息服务接口
 * 
 * @author lihh
 * 
 */
public interface UserInfoService extends BaseService<UserInfoEntity, UserInfoDao> {

	public UserInfoEntity queryUser(String loginName, String password);
	
}