/**
 * 
 */
package com.vinodborole.spring.multitenancy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vinodborole.spring.auth.MultiTenantUserDetailsService;

/**
 * @author VINOD
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:springmultitenancy-config.xml", "classpath:springmultitenancy-security-config.xml" })
public class TenantRoutingDataSourceTest {
    @Autowired
    private MultiTenantUserDetailsService multiTenantUserDetailsService;

    /**
     * @throws java.lang.Exception
     */

    @Before
    public void setUp() throws Exception {
        UserDetails userDetails = multiTenantUserDetailsService.loadUserByUsername("vinod@abc.com");
        System.out.println(userDetails.getUsername());
        Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    @Test
    public void loginTest() {
        try {
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Assert.assertNotNull(authentication);
        } catch (Exception e) {
            Assert.fail("Error occured while login:" + e.getMessage());
        }
    }

}
