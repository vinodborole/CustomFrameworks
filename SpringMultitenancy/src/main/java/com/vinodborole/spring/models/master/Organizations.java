/**
 * 
 */
package com.vinodborole.spring.models.master;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.vinodborole.spring.models.AbstractEntity;

/**
 * @author vinod
 * 
 */
@Entity
@Table(name = "organizations")
public class Organizations extends AbstractEntity {

	/**
     * 
     */
	private static final long serialVersionUID = -7882742955757436807L;

	@Id
	@GeneratedValue
	private int organizationid;
	private String organizationname;

	@OneToOne
	@JoinColumn(name = "tenantid")
	private TenantInfo tenantInfo;

	@OneToOne
	@JoinColumn(name = "mailid")
	private MailInfo mailInfo;

	@Column(name = "isactive")
	private boolean active;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "organization")
	private final Set<OrganizationsAdmin> orgAdmin = new HashSet<OrganizationsAdmin>();

	public int getOrganizationid() {
		return organizationid;
	}

	public void setOrganizationid(int organizationid) {
		this.organizationid = organizationid;
	}

	public String getOrganizationname() {
		return organizationname;
	}

	public void setOrganizationname(String organizationname) {
		this.organizationname = organizationname;
	}

	public TenantInfo getTenantInfo() {
		return tenantInfo;
	}

	public void setTenantInfo(TenantInfo tenantInfo) {
		this.tenantInfo = tenantInfo;
	}

	public MailInfo getMailInfo() {
		return mailInfo;
	}

	public void setMailInfo(MailInfo mailInfo) {
		this.mailInfo = mailInfo;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<OrganizationsAdmin> getOrgAdmin() {
		return orgAdmin;
	}

}
