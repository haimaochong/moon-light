package com.light.moon.service;

import com.light.base.enumCode.OperatorType;
import com.light.base.service.BaseService;
import com.light.moon.dao.OperateLogDao;
import com.light.moon.entity.OperateLogEntity;

/**
 * 日志服务接口
 * 
 * @author lihh
 * 
 */
public interface OperatorLogService extends BaseService<OperateLogEntity, OperateLogDao> {

	public void addOperatorLog(String userName, OperatorType type, String msg);

}
