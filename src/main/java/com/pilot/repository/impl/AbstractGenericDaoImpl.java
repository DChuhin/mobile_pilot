package com.pilot.repository.impl;

import com.pilot.repository.GenericDao;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

abstract class AbstractGenericDaoImpl<T, I extends Serializable> implements GenericDao<T, I> {

    private final HibernateBaseDao baseDao;

    @Autowired
    public AbstractGenericDaoImpl(HibernateBaseDao baseDao) {
        this.baseDao = baseDao;
    }

    private HibernateBaseDao getBaseDao() {
        return baseDao;
    }

    @Override
    public Criteria createCriteria() {
        return baseDao.createCriteria(getPersistentClass());
    }

    @Override
    public T get(I id) {
        return baseDao.get(getPersistentClass(), id);
    }

    @Override
    public void delete(I id) {
        delete(get(id));
    }

    @Override
    public void delete(T entity) {
        baseDao.delete(entity);
    }

    @Override
    public void delete(Collection<T> entities) {
        baseDao.delete(entities);
    }

    @Override
    public void save(T entity) {
        baseDao.save(entity);
    }

    @Override
    public List<T> getAll() {
        return getBaseDao().find(createCriteria());
    }

    @Override
    public List<T> find(Criteria criteria) {
        return getBaseDao().find(criteria);
    }
}
