/**
 * 
 */
package com.vinodborole.spring.models.master;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vinodborole.spring.models.AbstractEntity;

/**
 * @author vinod
 * 
 */
@Entity
@Table(name = "tenantinfo")
public class TenantInfo extends AbstractEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 5716066553542985472L;
    @Id
    @GeneratedValue
    private int tenantid;
    private String type;
    private String name;
    private String schemaname;
    private String host;
    private Integer port;
    private String username;
    private String password;

    public int getTenantid() {
        return tenantid;
    }

    public void setTenantid(int tenantid) {
        this.tenantid = tenantid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchemaname() {
        return schemaname;
    }

    public void setSchemaname(String schemaname) {
        this.schemaname = schemaname;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
