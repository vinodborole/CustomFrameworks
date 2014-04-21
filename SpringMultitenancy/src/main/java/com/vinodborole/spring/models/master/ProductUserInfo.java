/**
 * 
 */
package com.vinodborole.spring.models.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.vinodborole.spring.models.AbstractEntity;

/**
 * @author vinod
 * 
 */

@Entity
@Table(name = "productuser")
public class ProductUserInfo extends AbstractEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 6281781411159002291L;
    @Id
    @GeneratedValue
    private int productuserid;
    private String firstname;
    private String lastname;
    private String emailid;
    @OneToOne
    @JoinColumn(name = "productroleid")
    private ProductRole productRole;

    @Column(name = "isactive")
    private boolean active;

    public int getProductuserid() {
        return productuserid;
    }

    public void setProductuserid(int productuserid) {
        this.productuserid = productuserid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public ProductRole getProductRole() {
        return productRole;
    }

    public void setProductRole(ProductRole productRole) {
        this.productRole = productRole;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
