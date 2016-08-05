package com.pilot.repository;

import org.hibernate.Criteria;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * GenericDao
 *
 * @param <T> object class
 * @param <I> type of primary key
 */
public interface GenericDao<T, I extends Serializable> {

    /**
     * Returns actual persistent object's name in storage. This name can differ
     * from what {@code class.getSimpleName()} returns. Actual name of persistent
     * object can be helpful during writing native queries.
     *
     * @return actual persistent object's name
     */
    Class<T> getPersistentClass();

    /**
     * Create criteria
     *
     * @return Criteria
     */
    Criteria createCriteria();

    /**
     * Gets an entity using the given unique id
     *
     * @param id unique id of an entity
     * @return found entity
     */
    T get(I id);

    /**
     * Deletes entity from underlying datastore
     *
     * @param id unique id of an entity
     */
    void delete(I id);

    /**
     * Deletes entity from underlying datastore
     *
     * @param entity to remove
     */
    void delete(T entity);

    /**
     * Deletes entities from underlying datastore
     *
     * @param entities to remove
     */
    void delete(Collection<T> entities);

    /**
     * Saves new entity to underlying datastore
     *
     * @param entity to persist
     */
    void save(T entity);

    /**
     * Gets list of all entities in the datastore
     *
     * @return List<T>
     */
    List<T> getAll();

    /**
     * Gets list of entities in the datastore
     *
     * @param criteria criteria
     * @return List<T>
     */
    List<T> find(Criteria criteria);
}
