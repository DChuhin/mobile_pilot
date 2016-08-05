package com.pilot.repository.impl;

import com.pilot.repository.UserDao;
import com.pilot.repository.model.entity.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * User dao impl
 */
@Repository
public class UserDaoImpl extends AbstractGenericDaoImpl<User, Long> implements UserDao {

    @Autowired
    public UserDaoImpl(HibernateBaseDao baseDao) {
        super(baseDao);
    }

    @Override
    public User getUserByUserName(String name) {
        Criteria criteria = createCriteria();
        criteria.add(Restrictions.eq("name", name));
        return (User) criteria.uniqueResult();
    }

    @Override
    public Class<User> getPersistentClass() {
        return User.class;
    }
}
