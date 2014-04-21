/**
 * 
 */
package com.vinodborole.spring.dao.master;

import com.vinodborole.spring.models.master.MasterUserInfo;

/**
 * @author vinod
 * 
 */
public interface IMasterUserDao {

    MasterUserInfo findMasterUserByEmailId(String emailId);

    void saveUser(MasterUserInfo user);

}
