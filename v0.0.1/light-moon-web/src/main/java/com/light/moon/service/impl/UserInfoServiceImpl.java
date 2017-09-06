package com.light.moon.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.light.moon.context.ThreadLocalInfo;
import com.light.moon.context.UserContext;
import com.light.moon.dao.UserInfoDao;
import com.light.moon.dto.RegistUserDto;
import com.light.moon.dto.UserDto;
import com.light.moon.entity.UserInfoEntity;
import com.light.moon.enumCode.OperatorType;
import com.light.moon.enumCode.PayType;
import com.light.moon.service.OperatorLogService;
import com.light.moon.service.UserInfoService;
import com.light.moon.utils.IdUtils;

/**
 * 用户信息服务实现类
 * 
 * @author lihh
 * 
 */
@Service
public class UserInfoServiceImpl extends AbsBaseService<UserInfoEntity, UserInfoDao> implements UserInfoService {

	private ThreadLocalInfo threadLocalInfo = ThreadLocalInfo.getInstance();

	private UserContext userContext = UserContext.getInstance();

	@Resource
	private OperatorLogService operatorLogService;

	@Override
	public UserInfoEntity queryUser(String loginName, String password) {
		return dao.queryUser(loginName, password);
	}

	@Override
	public String registUser(RegistUserDto user) {
		UserInfoEntity entity = dao.queryUser(user.getLoginName());
		if (null != entity) {
			return "该手机号码已注册，请重新输入或登录";
		}

		UserInfoEntity newUser = convertData(user);
		dao.save(newUser);
		login(newUser);
		return null;
	}

	private UserInfoEntity convertData(RegistUserDto user) {
		UserInfoEntity entity = new UserInfoEntity();
		entity.setId(IdUtils.id());
		entity.setLoginName(user.getLoginName());
		entity.setUserName(user.getLoginName());
		entity.setPassword(user.getPassword());
		entity.setSex(0);
		entity.setPayType(PayType.PAY_ZFB);
		entity.setRegistTime(new Date());
		return entity;
	}

	@Override
	public void login(UserInfoEntity user) {
		UserDto userDto = new UserDto();
		userDto.setUserId(user.getId());
		userDto.setLoginName(user.getLoginName());
		userDto.setUserName(user.getUserName());
		userDto.setLastOperateTime(new Date());
		userContext.addLoginUser(threadLocalInfo.getSessionId(), userDto);
		threadLocalInfo.setUser(userDto);

		operatorLogService.addOperatorLog(user.getLoginName(), OperatorType.USER_LOGIN, "用户登录,IP:"
				+ threadLocalInfo.getRequest().getRemoteAddr());
	}

}
