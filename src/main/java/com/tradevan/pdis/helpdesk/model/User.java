package com.tradevan.pdis.helpdesk.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tradevan.framework.model.BaseEntity;

@Entity
@Table(name = "PD_USERS")
public class User extends BaseEntity {

	/**
     * 
     */
	private static final long serialVersionUID = -3730661283467684091L;

	@Column(name = "user_name",unique = true, nullable = false)
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;
	
	@Column(name = "enabled")
	private int enabled;
	
	@Column(name = "ap_id")
	private String apId;

	@ManyToMany(cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch=FetchType.EAGER)
	@JoinTable(name = "PD_USER_ROLE", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
	@JsonIgnore
	private Set<Role> roles = new HashSet<Role>();

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@JsonIgnore //讓jackson將物件轉json字串時忽略，反向卻要的設定方式
	public String getPassword() {
		return password;
	}
	
	@JsonProperty //讓jackson將物件轉json字串時忽略，反向卻要的設定方式
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getApId() {
		return apId;
	}

	public void setApId(String apId) {
		this.apId = apId;
	}
	
	

}
