package com.light.moon.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.light.base.enumCode.OperatorType;
import com.light.base.service.impl.AbsBaseService;
import com.light.base.utils.IdUtils;
import com.light.moon.dao.OperateLogDao;
import com.light.moon.entity.OperateLogEntity;
import com.light.moon.service.OperatorLogService;

/**
 * 日志服务实现类
 * 
 * @author lihh
 * 
 */
@Service
public class OperatorLogServiceImpl extends AbsBaseService<OperateLogEntity, OperateLogDao>
		implements OperatorLogService {

	@Override
	public void addOperatorLog(String userName, OperatorType type, String msg) {
		OperateLogEntity entity = new OperateLogEntity();
		entity.setId(IdUtils.id());
		entity.setUserName(userName);
		entity.setType(type);
		entity.setMsg(msg);
		entity.setOperateTime(new Date());
		dao.save(entity);
	}

}
