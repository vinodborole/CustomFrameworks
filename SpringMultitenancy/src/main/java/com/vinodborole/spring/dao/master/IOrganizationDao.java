/**
 * 
 */
package com.vinodborole.spring.dao.master;

import java.util.List;

import com.vinodborole.spring.models.master.Organizations;

/**
 * @author vinod
 * 
 */
public interface IOrganizationDao {

    List<Organizations> getAllOrganizations();

    Organizations findById(Integer id);

    Organizations findByDomainName(String domainName);
}
