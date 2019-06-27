package com.qqspace.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.qqspace.dao.base.BaseDao;

public class BaseDaoimpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	Class<?> clazz;
	public BaseDaoimpl() {
		ParameterizedType genericSuperclass = (ParameterizedType)this.getClass().getGenericSuperclass();
		clazz=(Class<?>) genericSuperclass.getActualTypeArguments()[0];
	}
	@Override
	public void add(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	@Override
	public long findAllCount() {
		DetachedCriteria criteria=DetachedCriteria.forClass(clazz);
		criteria.setProjection(Projections.rowCount());
		List<?> list = this.getHibernateTemplate().findByCriteria(criteria);
		if(list.size()!=0) {
			return (long) list.get(0);
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(Serializable id) {
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from "+clazz.getName());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByPage(Integer currPage, Integer pageSize) {
		DetachedCriteria criteria=DetachedCriteria.forClass(clazz);
		criteria.setProjection(null);
		return (List<T>) this.getHibernateTemplate().findByCriteria(criteria,currPage,pageSize);
	}

}
