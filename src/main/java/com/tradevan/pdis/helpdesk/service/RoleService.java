package com.tradevan.pdis.helpdesk.service;


import java.util.Collection;
import com.tradevan.pdis.helpdesk.model.Role;
import com.tradevan.pdis.helpdesk.model.User;


public interface RoleService {
	public Collection<Role> readAllRoles();
	public Collection<Role> destroyRoles(Collection<Role> roles) throws Exception;
	public Collection<Role> createRoles(Collection<Role> roles) throws Exception;
	public Collection<Role> updateRoles(Collection<Role> roles) throws Exception;
	public Collection<Role> bindUsers(Collection<Role> roles, Collection<User> users,
			String op) throws Exception;

}
