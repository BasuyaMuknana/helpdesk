package com.tradevan.pdis.helpdesk.service;

import java.util.Collection;
import java.util.HashMap;

import com.tradevan.framework.model.BaseEntity;
import com.tradevan.pdis.helpdesk.model.Role;
import com.tradevan.pdis.helpdesk.model.User;

public interface UserService {
	public Collection<User> readAllUsers();
	public Collection<User> destroyUsers(Collection<User> users) throws Exception;
	public Collection<User> createUsers(Collection<User> users) throws Exception;
	public Collection<User> updateUsers(Collection<User> users) throws Exception;
	public Collection<Role> queryRoles(User user) throws Exception;
	public User bindRoles(User user, Collection<Role> roles) throws Exception;
}
