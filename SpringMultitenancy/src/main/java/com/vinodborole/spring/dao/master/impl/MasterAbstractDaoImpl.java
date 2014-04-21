/**
 * 
 */
package com.vinodborole.spring.dao.master.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.vinodborole.spring.common.UserInfoHelper;
import com.vinodborole.spring.dao.master.IMasterAbstractDao;
import com.vinodborole.spring.models.AbstractEntity;

/**
 * @author VINOD
 *
 */
public abstract class MasterAbstractDaoImpl<E extends AbstractEntity, I extends Serializable> implements IMasterAbstractDao<E, I> {
    private final Class<E> entityClass;

    protected MasterAbstractDaoImpl(final Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Autowired
    @Qualifier("masterSessionFactory")
    private SessionFactory masterSessionFactory;

    @Autowired
    private UserInfoHelper userInfoHelper;

    public final Session getCurrentSession() {
        return masterSessionFactory.getCurrentSession();
    }

    @Override
    public E findById(final I id) {
        return (E) getCurrentSession().get(entityClass, id);
    }

    public List<E> findAll() {
        return getCurrentSession().createQuery("from " + entityClass.getName()).list();
    }

    public void save(final E entity) {
        entity.setCreatedate(new Date());
        entity.setCreatedby(userInfoHelper.getLoggedInUserId());
        getCurrentSession().persist(entity);
    }

    public E update(final E entity) {
        entity.setLastupdatedate(new Date());
        entity.setLastupdatedby(userInfoHelper.getLoggedInUserId());
        return (E) getCurrentSession().merge(entity);
    }

    @Override
    public void delete(final E e) {
        getCurrentSession().delete(e);
    }

    public void deleteById(final I id) {
        final E entity = findById(id);
        delete(entity);
    }

    @Override
    public List<E> findByCriteria(final Criterion criterion) {
        final Criteria criteria = getCurrentSession().createCriteria(entityClass);
        criteria.add(criterion);
        return criteria.list();
    }
}
