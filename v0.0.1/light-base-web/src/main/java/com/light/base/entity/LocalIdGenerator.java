package com.light.base.entity;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.PersistentIdentifierGenerator;
import org.hibernate.id.SequenceGenerator;

import com.light.base.utils.IdUtils;

/**
 * 
 * @author lihh
 * 
 */
public class LocalIdGenerator extends SequenceGenerator implements PersistentIdentifierGenerator, IdentifierGenerator {

	@Override
	public Serializable generate(SessionImplementor sessionImplementor, Object o) throws HibernateException {
		try {
			// ModifyBy:chenqiong
			// 如果对象已经有ID设置了，则不需要再产生
			if (o instanceof BaseId) {
				BaseId base = (BaseId) o;
				if (base.getId() != null && 0l != base.getId()) {
					return base.getId();
				}
			}
		} catch (Exception e) {
		}

		return IdUtils.id();
	}
}
