package com.light.moon.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.light.moon.entity.BaseId;

/**
 * 基础Dao
 * 
 * @author lihh
 * 
 * @param <T>
 */
public interface BaseDao<T extends BaseId> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
}
