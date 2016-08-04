package com.pilot.repository.impl;

import com.pilot.repository.UserDao;
import com.pilot.repository.model.entity.User;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User dao impl
 */
@Repository
public class UserDaoImpl extends AbstractGenericDaoImpl<User, Long> implements UserDao {

    @Override
    public User getUserByUserName(String name) {
        DetachedCriteria criteria = DetachedCriteria.forClass(getPersistentClass());
        criteria.add(Restrictions.eq("name", name));
        List<User> users = find(criteria);
        if (users.isEmpty()) {
            return null;
        } else {
            if (users.size() != 1) {
                throw new IllegalStateException();
            } else {
                return users.get(0);
            }
        }

    }

    @Override
    public Class<User> getPersistentClass() {
        return User.class;
    }
}
