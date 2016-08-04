package com.pilot.repository;

import com.pilot.repository.model.entity.User;

/**
 * User dao
 */
public interface UserDao extends GenericDao<User, Long> {

    /**
     * Get user by username
     *
     * @param name name
     * @return user
     */
    User getUserByUserName(String name);

}
