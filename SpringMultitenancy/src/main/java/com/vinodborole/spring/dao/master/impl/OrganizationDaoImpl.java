/**
 * 
 */
package com.vinodborole.spring.dao.master.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.vinodborole.spring.dao.master.IOrganizationDao;
import com.vinodborole.spring.dao.master.MasterTx;
import com.vinodborole.spring.models.master.Organizations;

/**
 * @author vinod
 * 
 */
@Repository("OrganizationDao")
public class OrganizationDaoImpl extends MasterAbstractDaoImpl<Organizations, Integer> implements IOrganizationDao {

    protected OrganizationDaoImpl() {
        super(Organizations.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.xpdoffice.core.dao.master.OrganizationDao#getAllOrganizations()
     */
    @Override
    @MasterTx
    public List<Organizations> getAllOrganizations() {
        Session session = getCurrentSession();
        final Criteria criteria = session.createCriteria(Organizations.class);
        return criteria.list();
    }

    @Override
    @MasterTx
    public Organizations findById(Integer id) {
        return super.findById(id);
    }

    @Override
    @MasterTx
    public Organizations findByDomainName(String domainName) {
        Criteria criteria = getCurrentSession().createCriteria(Organizations.class, "org");
        criteria.add(Restrictions.eq("organizationdomain", domainName));
        Object obj = criteria.uniqueResult();
        return obj != null ? (Organizations) obj : null;
    }

}
