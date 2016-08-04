package com.pilot.repository.impl;

import com.pilot.repository.GenericDao;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public abstract class AbstractGenericDaoImpl<T, I extends Serializable> implements GenericDao<T, I> {

    @Autowired
    private HibernateBaseDao baseDao;

    protected HibernateBaseDao getBaseDao() {
        return baseDao;
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
        return getBaseDao().find(getPersistentClass(), null);
    }

    @Override
    public void flush() {
        getBaseDao().getHibernateTemplate().flush();
    }

    @Override
    public List<T> find(DetachedCriteria criteria) {
        return getBaseDao().find(getPersistentClass(), criteria);
    }
}
