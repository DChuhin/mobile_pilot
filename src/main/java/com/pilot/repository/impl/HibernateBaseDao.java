package com.pilot.repository.impl;

import com.pilot.repository.BaseDao;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Repository
class HibernateBaseDao extends HibernateDaoSupport implements BaseDao {

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(HibernateBaseDao.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateBaseDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Init session factory
     */
    @PostConstruct
    public void init() {
        setSessionFactory(sessionFactory);
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
    public <T> List<T> find(Criteria criteria) {
        try {
            return criteria.list();
        } catch (DataAccessException e) {
            LOGGER.debug(e.toString(), e);
            throw e;
        }
    }

    @Override
    public <T> Criteria createCriteria(Class<T> persistentClass) {
        return sessionFactory.getCurrentSession().createCriteria(persistentClass);
    }
}
