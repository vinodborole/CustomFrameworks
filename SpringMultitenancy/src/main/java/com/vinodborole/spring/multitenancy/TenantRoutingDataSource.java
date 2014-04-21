/**
 * 
 */
package com.vinodborole.spring.multitenancy;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vinodborole.spring.auth.AuthUser;

/**
 * @author vinod
 * 
 */
public class TenantRoutingDataSource extends AbstractRoutingDataSource {
    private static final Logger logger = Logger.getLogger(TenantRoutingDataSource.class);

    public TenantRoutingDataSource(final MultiTenantConfigurationManager multitenantConfigurationManager) {
        setTargetDataSources(multitenantConfigurationManager.getTargetDataSources());
    }

    @Override
    protected Object determineCurrentLookupKey() {
        int tenantLookUpKey = 0;
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            final Object principal = authentication.getPrincipal();
            if (principal instanceof AuthUser) {
                tenantLookUpKey = ((AuthUser) principal).getTenantId();
            }
        }
        logger.info("Returning tenanid as : " + tenantLookUpKey);
        return tenantLookUpKey;
    }
}
