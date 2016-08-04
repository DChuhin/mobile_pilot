package com.pilot.repository;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * BaseDao interface
 */
public interface BaseDao {

    /**
     * Get current session
     *
     * @return Session
     */
    Session getCurrentSession();

    /**
     * Save entity
     *
     * @param entity entity
     * @param <T>    class of entity
     */
    <T> void save(T entity);

    /**
     * Delete entity
     *
     * @param entity entity
     * @param <T>    class of entity
     */
    <T> void delete(T entity);

    /**
     * Delete entities collection
     *
     * @param entities entities collection
     * @param <T>      class of entity
     */
    <T> void delete(Collection<T> entities);

    /**
     * @param entityClass entity class
     * @param id          unique id
     * @param <T>         class of entity
     * @return T
     */
    <T> T get(Class<T> entityClass, Serializable id);

    /**
     * Find entity by filter
     *
     * @param criteria criteria
     * @param <T>      class of entity
     * @return List<T>
     */
    <T> List<T> find(Class<T> tClass, DetachedCriteria criteria);

}
