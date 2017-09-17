package com.light.moon.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.light.base.dao.BaseDao;
import com.light.moon.entity.NoticeEntity;

/**
 * 公告Dao
 * 
 * @author lihh
 * 
 */
@Repository
public interface NoticeDao extends BaseDao<NoticeEntity> {

	@Query(value = "select * from moon_notice t order by t.create_time desc limit 0,3 ", nativeQuery = true)
	public List<NoticeEntity> queryNewNotice();

	@Query(value = "select * from moon_notice a where a.CREATE_TIME > (select t.CREATE_TIME from moon_notice t where t.ID=?1) order by a.CREATE_TIME limit 1 ", nativeQuery = true)
	public NoticeEntity queryPreNotice(Long currentId);

	@Query(value = "select * from moon_notice a where a.CREATE_TIME < (select t.CREATE_TIME from moon_notice t where t.ID=?1) order by a.CREATE_TIME desc limit 1 ", nativeQuery = true)
	public NoticeEntity queryNextNotice(Long currentId);

}
