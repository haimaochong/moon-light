package com.light.moon.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.light.moon.dao.NoticeDao;
import com.light.moon.entity.NoticeEntity;
import com.light.moon.service.NoticeService;

/**
 * 公告服务实现类
 * 
 * @author lihh
 * 
 */
@Service
public class NoticeServiceImpl extends AbsBaseService<NoticeEntity, NoticeDao> implements NoticeService {

	@Override
	public List<NoticeEntity> queryNewNotice() {
		return dao.queryNewNotice();
	}

	@Override
	public NoticeEntity queryPreNotice(Long currentId) {
		return dao.queryPreNotice(currentId);
	}

	@Override
	public NoticeEntity queryNextNotice(Long currentId) {
		return dao.queryNextNotice(currentId);
	}

}
