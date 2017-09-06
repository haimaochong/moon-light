package com.light.moon.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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

}
