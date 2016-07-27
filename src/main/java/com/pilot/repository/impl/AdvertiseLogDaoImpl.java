package com.pilot.repository.impl;

import com.pilot.model.entity.AdvertiseLog;
import com.pilot.model.request.AdvertiseRequest;
import com.pilot.repository.AdvertiseLogDao;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Advertise log dao impl
 */
@Repository
public class AdvertiseLogDaoImpl implements AdvertiseLogDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveOrUpdate(AdvertiseLog advertiseLog) {
        sessionFactory.getCurrentSession().saveOrUpdate(advertiseLog);
    }

    @Override
    public void delete(AdvertiseLog advertiseLog) {
        sessionFactory.getCurrentSession().delete(advertiseLog);
    }

    @Override
    public AdvertiseLog get(long id) {
        return (AdvertiseLog) sessionFactory.getCurrentSession().get(AdvertiseLog.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<AdvertiseLog> getChartListByRequest(AdvertiseRequest advertiseRequest) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AdvertiseLog.class);

        List<Long> advertises = advertiseRequest.getAdvertises();
        List channels = advertiseRequest.getChannels();

        if (advertises != null && !advertises.isEmpty()) {
            criteria.add(Restrictions.in("advertiseId", advertises));
        }

        if (channels != null && !channels.isEmpty()) {
            criteria.add(Restrictions.in("channelId", channels));
        }

        if (advertiseRequest.getStartDate() != null) {
            criteria.add(Restrictions.ge("date", advertiseRequest.getStartDate()));
        }

        if (advertiseRequest.getEndDate() != null) {
            criteria.add(Restrictions.le("date", advertiseRequest.getEndDate()));
        }

        return (List<AdvertiseLog>) criteria.list();
    }
}
