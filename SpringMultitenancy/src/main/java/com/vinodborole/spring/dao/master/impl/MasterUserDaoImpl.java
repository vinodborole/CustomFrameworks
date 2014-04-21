/**
 * 
 */
package com.vinodborole.spring.dao.master.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.vinodborole.spring.dao.master.IMasterUserDao;
import com.vinodborole.spring.dao.master.MasterTx;
import com.vinodborole.spring.models.master.MasterUserInfo;

/**
 * @author vinod
 * 
 */
@Repository("masterUserDaoImpl")
public class MasterUserDaoImpl extends MasterAbstractDaoImpl<MasterUserInfo, Integer> implements IMasterUserDao {

    protected MasterUserDaoImpl() {
        super(MasterUserInfo.class);
    }

    @Override
    @MasterTx
    public MasterUserInfo findMasterUserByEmailId(final String emailId) {
        return (MasterUserInfo) findByCriteria(Restrictions.eq("emailid", emailId)).get(0);
    }

    @Override
    @MasterTx
    public MasterUserInfo findById(Integer id) {
        return super.findById(id);
    }

    @Override
    @MasterTx
    public void saveUser(final MasterUserInfo user) {
        save(user);
    }

}
