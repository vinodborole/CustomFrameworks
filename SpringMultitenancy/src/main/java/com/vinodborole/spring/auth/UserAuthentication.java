/**
 * 
 */
package com.vinodborole.spring.auth;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * @author VINOD
 * 
 */
public class UserAuthentication extends JdbcDaoSupport {

    /**
     * 
     */
    public UserAuthentication(final DataSource dataSource) {
        super();
        setDataSource(dataSource);
    }

    public AuthUser getUserByUsername(final String username) {
        return getJdbcTemplate().queryForObject("SELECT userid, username, password, isactive FROM users WHERE username = ?;", new Object[] { username }, new RowMapper<AuthUser>() {

            @Override
            public AuthUser mapRow(final ResultSet rs, final int rowNum) throws SQLException {
                final Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
                auths.add(new SimpleGrantedAuthority("ROLE_USER"));
                AuthUser authUser = new AuthUser(rs.getString("username"), rs.getString("password"), rs.getBoolean("isactive"), true, true, true, auths);
                authUser.setUserid(rs.getInt("userid"));
                return authUser;
            }
        });
    }
}
