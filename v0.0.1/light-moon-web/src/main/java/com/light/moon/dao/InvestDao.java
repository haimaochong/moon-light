package com.light.moon.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.light.moon.entity.InvestEntity;

/**
 * 投资记录Dao
 * 
 * @author lihh
 * 
 */
@Repository
public interface InvestDao extends BaseDao<InvestEntity> {

	@Query(value = "select count(t.id) from InvestEntity t where t.platForm.id = ?1 ")
	public Integer queryInvestNumByPlatform(Long platformId);

}
