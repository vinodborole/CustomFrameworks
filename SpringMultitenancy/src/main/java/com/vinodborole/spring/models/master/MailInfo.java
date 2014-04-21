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
@Table(name = "mailinfo")
public class MailInfo extends AbstractEntity {

	@Id
	@GeneratedValue
	private int mailid;
	private String host;
	private int port;
	private String username;
	private String password;
	private boolean auth;
	private String replyto;
	private String mailfrom;
	private boolean ssl;

	public int getMailid() {
		return mailid;
	}

	public void setMailid(int mailid) {
		this.mailid = mailid;
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

	public boolean isAuth() {
		return auth;
	}

	public void setAuth(boolean auth) {
		this.auth = auth;
	}

	public String getReplyto() {
		return replyto;
	}

	public void setReplyto(String replyto) {
		this.replyto = replyto;
	}

	public String getMailfrom() {
		return mailfrom;
	}

	public void setMailfrom(String mailfrom) {
		this.mailfrom = mailfrom;
	}

	public boolean isSsl() {
		return ssl;
	}

	public void setSsl(boolean ssl) {
		this.ssl = ssl;
	}

}
