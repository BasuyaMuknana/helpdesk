package com.tradevan.pdis.helpdesk.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tradevan.framework.model.BaseEntity;

@Entity
@Table(name = "PD_ROLES")
public class Role extends BaseEntity {

	/**
     * 
     */
	private static final long serialVersionUID = 5245560470855686494L;

	@Column(name = "role_name",unique = true, nullable = false)
	private String roleName;

	@Column(name = "role_desc")
	private String roleDesc;

	@ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JsonIgnore
	private Set<User> users = new HashSet<User>();

	/*@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "role_permission", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "permission_id") })
	@JsonIgnore
	private Set<Permission> permissions;*/

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	/*public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}*/

}
