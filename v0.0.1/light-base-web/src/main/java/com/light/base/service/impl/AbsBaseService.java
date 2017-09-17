package com.light.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import com.light.base.dao.BaseDao;
import com.light.base.entity.BaseId;
import com.light.base.service.BaseService;

/**
 * 提供给service继承简化工作量
 * 
 * @author lihh
 * 
 * @param <T>
 * @param <Dao>
 */
public abstract class AbsBaseService<T extends BaseId, Dao extends BaseDao<T>> implements BaseService<T, Dao> {

	@Autowired
	protected Dao dao;

	@Transactional(rollbackFor = Exception.class)
	public T save(T entity) {
		return dao.save(entity);
	}

	@Transactional(rollbackFor = Exception.class)
	public Iterable<T> save(Iterable<T> entities) {
		return dao.save(entities);
	}

	public T findOne(Long id) {
		return dao.findOne(id);
	}

	public Iterable<T> findAll() {
		return dao.findAll();
	}

	public Iterable<T> findAll(Iterable<Long> ids) {
		return dao.findAll(ids);
	}

	public Iterable<T> findAll(Sort sort) {
		return dao.findAll(sort);
	}

	public Page<T> findAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(Long id) {
		dao.delete(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(T entity) {
		dao.delete(entity);
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(Iterable<? extends T> entities) {
		dao.delete(entities);
	}

	public T findOne(Specification<T> spec) {
		return dao.findOne(spec);
	}

	/**
	 * Returns all entities matching the given {@link Specification}.
	 * 
	 * @param spec
	 * @return
	 */
	public List<T> findAll(Specification<T> spec) {
		return dao.findAll(spec);
	}

	/**
	 * Returns a {@link Page} of entities matching the given
	 * {@link Specification}.
	 * 
	 * @param spec
	 * @param pageable
	 * @return
	 */
	public Page<T> findAll(Specification<T> spec, Pageable pageable) {
		return dao.findAll(spec, pageable);
	}

	/**
	 * Returns all entities matching the given {@link Specification} and
	 * {@link Sort}.
	 * 
	 * @param spec
	 * @param sort
	 * @return
	 */
	public List<T> findAll(Specification<T> spec, Sort sort) {
		return dao.findAll(spec, sort);
	}
}
