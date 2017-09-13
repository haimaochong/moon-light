package com.light.moon.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.light.moon.entity.OrderEntity;

/**
 * 投资记录Dao
 * 
 * @author lihh
 * 
 */
@Repository
public interface OrderDao extends BaseDao<OrderEntity> {

	@Query(value = "select count(t.id) from OrderEntity t where t.platForm.id = ?1 ")
	public Integer queryInvestNumByPlatform(Long platformId);

}
