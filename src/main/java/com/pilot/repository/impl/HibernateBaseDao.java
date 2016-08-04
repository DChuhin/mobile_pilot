package com.pilot.repository.impl;

import com.pilot.repository.BaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Repository
public class HibernateBaseDao extends HibernateDaoSupport implements BaseDao {

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(HibernateBaseDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Init session factory
     */
    @PostConstruct
    public void init() {
        setSessionFactory(sessionFactory);
    }

    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public <T> void save(T entity) {
        try {
            getHibernateTemplate().saveOrUpdate(entity);
        } catch (DataAccessException e) {
            LOGGER.debug(e.toString(), e);
            throw e;
        }
    }

    @Override
    public <T> void delete(T entity) {
        try {
            getHibernateTemplate().delete(entity);
        } catch (DataAccessException e) {
            LOGGER.debug(e.toString(), e);
            throw e;
        }
    }

    @Override
    public <T> void delete(Collection<T> entities) {
        try {
            getHibernateTemplate().deleteAll(entities);
        } catch (DataAccessException e) {
            LOGGER.debug(e.toString(), e);
            throw e;
        }
    }

    @Override
    public <T> T get(Class<T> entityClass, Serializable id) {
        try {
            if (null != id) {
                return getHibernateTemplate().get(entityClass, id);
            }
        } catch (DataAccessException e) {
            LOGGER.debug(e.toString(), e);
            throw e;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> find(Class<T> tClass, DetachedCriteria criteria) {
        try {
            return (List<T>) getHibernateTemplate().findByCriteria(criteria);
        } catch (DataAccessException e) {
            LOGGER.debug(e.toString(), e);
            throw e;
        }
    }
}
