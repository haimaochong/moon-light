package com.light.moon.service;

import java.util.List;

import com.light.base.service.BaseService;
import com.light.moon.dao.NoticeDao;
import com.light.moon.entity.NoticeEntity;

/**
 * 公告服务接口
 * 
 * @author lihh
 * 
 */
public interface NoticeService extends BaseService<NoticeEntity, NoticeDao> {

	public List<NoticeEntity> queryNewNotice();
	
	public NoticeEntity queryPreNotice(Long currentId);
	
	public NoticeEntity queryNextNotice(Long currentId);

}
