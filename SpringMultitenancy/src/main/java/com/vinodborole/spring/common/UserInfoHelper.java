/**
 * 
 */
package com.vinodborole.spring.common;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.vinodborole.spring.auth.AuthUser;
import com.vinodborole.spring.dao.master.IMasterUserDao;
import com.vinodborole.spring.dao.master.IOrganizationDao;
import com.vinodborole.spring.models.master.MasterUserInfo;
import com.vinodborole.spring.models.master.Organizations;
import com.vinodborole.spring.models.master.OrganizationsAdmin;

/**
 * @author vinod
 * 
 */
@Component
public class UserInfoHelper {

    @Autowired
    private IOrganizationDao iOrgDao;

    @Autowired
    private IMasterUserDao iMasterDao;

    public Integer getLoggedInUserId() {
        return getLoggedInUser().getUserid();
    }

    public AuthUser getLoggedInUser() {
        AuthUser user = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Object principal = auth.getPrincipal();
            if (principal instanceof AuthUser) {
                user = (AuthUser) principal;
            }
        }
        return user;
    }

    public MasterUserInfo getSystemMasterUser(String orgdomain) {
        OrganizationsAdmin orgAdmin = getOrgAdmin(orgdomain);
        if (orgAdmin != null) {
            return orgAdmin.getMasterUserInfo();
        }
        return null;
    }

    private OrganizationsAdmin getOrgAdmin(String orgdomain) {
        Organizations org = iOrgDao.findByDomainName(orgdomain);
        if (org != null) {
            Set<OrganizationsAdmin> orgAdmins = org.getOrgAdmin();
            if (orgAdmins.size() > 0) {
                Iterator<OrganizationsAdmin> itr = orgAdmins.iterator();
                while (itr.hasNext()) {
                    OrganizationsAdmin orgadmin = itr.next();
                    if (orgadmin.isIsactive()) {
                        return orgadmin;
                    }
                }
            }
        }
        return null;
    }

    public Organizations getUserOrganization(String username) {
        MasterUserInfo masterUserInfo = iMasterDao.findMasterUserByEmailId(username);
        return masterUserInfo.getOrganizations();
    }

    public OrganizationsAdmin getOrganizationAdmin(Organizations org) {
        Set<OrganizationsAdmin> orgAdmins = org.getOrgAdmin();
        if (orgAdmins.size() > 0) {
            OrganizationsAdmin orgAdmin = orgAdmins.iterator().next();
            return orgAdmin;
        }
        return null;
    }
}
