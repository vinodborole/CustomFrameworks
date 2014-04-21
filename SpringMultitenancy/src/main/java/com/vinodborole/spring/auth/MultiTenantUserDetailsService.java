/**
 * 
 */
package com.vinodborole.spring.auth;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vinodborole.spring.dao.master.IMasterUserDao;
import com.vinodborole.spring.models.master.MasterUserInfo;
import com.vinodborole.spring.models.master.Organizations;
import com.vinodborole.spring.models.master.TenantInfo;
import com.vinodborole.spring.multitenancy.MultiTenantConfigurationManager;

/**
 * @author vinod
 * 
 */
public class MultiTenantUserDetailsService implements UserDetailsService {
    private final MultiTenantConfigurationManager multitenantConfigurationManager;
    private final IMasterUserDao masterUserDao;
    private static final Logger logger = Logger.getLogger(MultiTenantUserDetailsService.class);

    /**
     * 
     * @param tenantDataSourceMappingManager
     * @param masterUserDao
     */
    public MultiTenantUserDetailsService(final MultiTenantConfigurationManager multitenantConfigurationManager, final IMasterUserDao masterUserDao) {
        super();
        this.multitenantConfigurationManager = multitenantConfigurationManager;
        this.masterUserDao = masterUserDao;
    }

    public static final String ROLE_USER = "ROLE_USER";

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        int tenantId;
        Organizations org;
        try {
            MasterUserInfo masterUserInfo = masterUserDao.findMasterUserByEmailId(username);
            org = masterUserInfo.getOrganizations();
            logger.info("User Organization Name:" + org.getOrganizationname());
            TenantInfo tenantInfo = org.getTenantInfo();
            tenantId = tenantInfo.getTenantid();// Master
            logger.info("User tenand Id:" + tenantId);
        } catch (final EmptyResultDataAccessException e) {
            tenantId = MultiTenantConfigurationManager.MASTER_DB_ID;
            throw new UsernameNotFoundException("No user found with username: " + username, e);
        }

        // Tenant specific user lookup for authentication
        final DataSource dataSource = (DataSource) multitenantConfigurationManager.getTargetDataSources().get(tenantId);
        final AuthUser user = new UserAuthentication(dataSource).getUserByUsername(username);
        user.setTenantId(tenantId);
        user.setOrganization(org);
        return user;
    }
}
