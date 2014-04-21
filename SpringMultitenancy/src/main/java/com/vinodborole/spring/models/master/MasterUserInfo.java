/**
 * 
 */
package com.vinodborole.spring.models.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.vinodborole.spring.common.AuthenticationType;
import com.vinodborole.spring.models.AbstractEntity;

/**
 * @author vinod
 * 
 */
@Entity
@Table(name = "users")
public class MasterUserInfo extends AbstractEntity {

	/**
     * 
     */
	private static final long serialVersionUID = -5375832114950318831L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;
	private String emailid;
	@Enumerated(EnumType.STRING)
	private AuthenticationType authenticationtype;

	@OneToOne
	@JoinColumn(name = "ldapid")
	private LdapInfo ldapInfo;

	@ManyToOne
	@JoinColumn(name = "organizationid")
	private Organizations organizations;
	@Column(name = "isactive")
	private boolean active;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public AuthenticationType getAuthenticationtype() {
		return authenticationtype;
	}

	public void setAuthenticationtype(AuthenticationType authenticationtype) {
		this.authenticationtype = authenticationtype;
	}

	public LdapInfo getLdapInfo() {
		return ldapInfo;
	}

	public void setLdapInfo(LdapInfo ldapInfo) {
		this.ldapInfo = ldapInfo;
	}

	public Organizations getOrganizations() {
		return organizations;
	}

	public void setOrganizations(Organizations organizations) {
		this.organizations = organizations;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
