package com.pilot.repository;

import com.pilot.model.entity.User;

/**
 * User dao
 */
public interface UserDao {

    /**
     * Get user by username
     *
     * @param name name
     * @return user
     */
    User getUserByUserName(String name);

}
