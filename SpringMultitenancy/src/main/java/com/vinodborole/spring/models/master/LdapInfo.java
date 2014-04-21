package com.vinodborole.spring.models.master;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vinodborole.spring.models.AbstractEntity;

@Entity
@Table(name = "ldapinfo")
public class LdapInfo extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7135370207878423704L;
	@Id
	@GeneratedValue
	private int ldapid;
	private String host;
	private String domain;
	private String authentication;

	public int getLdapid() {
		return ldapid;
	}

	public void setLdapid(int ldapid) {
		this.ldapid = ldapid;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getAuthentication() {
		return authentication;
	}

	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}

}
