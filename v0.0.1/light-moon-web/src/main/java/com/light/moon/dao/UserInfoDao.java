package com.light.moon.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.light.moon.entity.UserInfoEntity;

/**
 * 用户信息Dao
 * 
 * @author lihh
 * 
 */
@Repository
public interface UserInfoDao extends BaseDao<UserInfoEntity> {

	@Query("from UserInfoEntity t where t.loginName = ?1 and t.password = ?2 ")
	public UserInfoEntity queryUser(String loginName, String password);
	
	@Query("from UserInfoEntity t where t.loginName = ?1 ")
	public UserInfoEntity queryUser(String loginName);
	
	@Query("update UserInfoEntity t set t.password = ?2 where t.id = ?1 ")
	@Modifying
	public void resetPwd(Long userId, String newPwd);

}
