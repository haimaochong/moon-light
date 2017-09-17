package com.light.moon.service;

import com.light.base.service.BaseService;
import com.light.moon.dao.UserInfoDao;
import com.light.moon.dto.RegistUserDto;
import com.light.moon.entity.UserInfoEntity;

/**
 * 用户信息服务接口
 * 
 * @author lihh
 * 
 */
public interface UserInfoService extends BaseService<UserInfoEntity, UserInfoDao> {

	public UserInfoEntity queryUser(String loginName, String password);

	public String registUser(RegistUserDto user);

	public void login(UserInfoEntity user);
	
}
