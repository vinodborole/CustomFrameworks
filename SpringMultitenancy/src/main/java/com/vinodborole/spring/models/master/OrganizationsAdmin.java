/**
 * 
 */
package com.vinodborole.spring.models.master;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.vinodborole.spring.models.AbstractEntity;

/**
 * @author vinod
 * 
 */
@Entity
@Table(name = "organizationsadmin")
public class OrganizationsAdmin extends AbstractEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -8186542881407106041L;
    @Id
    @GeneratedValue
    private int organizationsadminid;
    @OneToOne
    @JoinColumn(name = "userid")
    private MasterUserInfo masterUserInfo;
    @ManyToOne
    @JoinColumn(name = "organizationid")
    private Organizations organization;
    private boolean isactive;

    public int getOrganizationsadminid() {
        return organizationsadminid;
    }

    public void setOrganizationsadminid(int organizationsadminid) {
        this.organizationsadminid = organizationsadminid;
    }

    public MasterUserInfo getMasterUserInfo() {
        return masterUserInfo;
    }

    public void setMasterUserInfo(MasterUserInfo masterUserInfo) {
        this.masterUserInfo = masterUserInfo;
    }

    public Organizations getOrganization() {
        return organization;
    }

    public void setOrganization(Organizations organization) {
        this.organization = organization;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

}
