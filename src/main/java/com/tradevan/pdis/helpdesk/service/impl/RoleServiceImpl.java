package com.tradevan.pdis.helpdesk.service.impl;

import java.util.ArrayList;
import java.util.Collection;










import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





import org.springframework.transaction.annotation.Transactional;

import com.tradevan.framework.model.BaseEntity;
import com.tradevan.pdis.helpdesk.model.Role;
import com.tradevan.pdis.helpdesk.model.User;
import com.tradevan.pdis.helpdesk.repository.RoleRepository;
import com.tradevan.pdis.helpdesk.repository.UserRepository;
import com.tradevan.pdis.helpdesk.service.RoleService;


/**
 * 
 * @author 2904
 *
 */
@Service("roleService")
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {
	private static final String BIND_OP_ADD = "add";
	private static final String BIND_OP_REMOVE = "remove";
	private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public Collection<Role> readAllRoles() {
		Collection<Role> roles = null;
		roles = roleRepository.findAll();
		return roles;
	}

	@Override
	public Collection<Role> destroyRoles(Collection<Role> roles) throws Exception{
		//做此動作之前，必須先清除user_role table中的對應
		for(Role r : roles){
			Role persistRole = roleRepository.findById(r.getId());
			if(persistRole.getUsers().size() > 0){
				// TODO 回傳此role有人用需先清除user role對應
				logger.info("attempt to delete role[{}] with user_role relation!",r.getRoleName());
				throw new Exception("clean.user.role.relation.before.delete");
			}else{
				roleRepository.delete(persistRole);
				logger.info("role[{}] has been deleted!",r.getRoleName());
				
			}		
		}
		return this.readAllRoles();
	}

	@Override
	public Collection<Role> createRoles(Collection<Role> roles) throws Exception {
		try{
			for(Role r: roles){
				r = roleRepository.save(r);
				logger.info("role[{}] has been created!",r.getRoleName());
			}
		} catch(Exception e){
			logger.error(e.getMessage(),e);
			throw new Exception("database.manipulating.error");
		}
		return roles;
	}
	
	@Override
	public Collection<Role> updateRoles(Collection<Role> roles) throws Exception {
		Collection<Role> updatedRoles = new ArrayList<Role>();
		for(Role r : roles){
			Role persistRole = roleRepository.findById(r.getId());
			persistRole.setRoleName(r.getRoleName());
			persistRole.setRoleDesc(r.getRoleDesc());
			persistRole = roleRepository.update(persistRole);
			updatedRoles.add(persistRole);
			logger.info("role[{}] has been updated!",r.getRoleName());
		}
		//這裡只能回傳update過的role給kendo datasource
		return updatedRoles;
	}
	
	@Override
	public Collection<Role> bindUsers(Collection<Role> roles, Collection<User> users, String op) throws Exception {	
		Collection<User> bindingUsers = new ArrayList<User>();
		Collection<Role> bindingRoles = new ArrayList<Role>();
		
		if(BIND_OP_ADD.equalsIgnoreCase(op)){
			op = BIND_OP_ADD; 	
		}else if (BIND_OP_REMOVE.equalsIgnoreCase(op)){
			op = BIND_OP_REMOVE;
		}else{
			throw new Exception("invalid.parameter");
		}
		
		//先把persist user找出來
		for(User u : users){
			u = userRepository.findOneByExample(u);
			bindingUsers.add(u);
		}
		//先把persist role找出來
		for(Role r : roles){
			r = roleRepository.findOneByExample(r);
			bindingRoles.add(r);
		}

		if(BIND_OP_ADD.equals(op)){
			for(User u : bindingUsers){
//				for(Role r : u.getRoles()){
//					logger.debug("before {} has roles {}", u.getUserName(), r.getRoleName());
//				}
				u.getRoles().addAll(bindingRoles);
//				for(Role r : u.getRoles()){
//					logger.debug("after {} has roles {}", u.getUserName(), r.getRoleName());
//				}
				userRepository.update(u);
			}
			
			
			for(Role r : bindingRoles){
//				r = roleRepository.findOneByExample(r);
//				for(User u : r.getUsers()){
//					logger.debug("before {} has users {}", r.getRoleName(), u.getUserName());
//				}
				r.getUsers().addAll(bindingUsers);
//				for(User u : r.getUsers()){
//					logger.debug("after {} has users {}", r.getRoleName(), u.getUserName());
//				}
				roleRepository.update(r);
			}
		}else{
			for(User u : bindingUsers){
				u.getRoles().removeAll(bindingRoles);
				userRepository.update(u);
			}
			
			for(Role r : bindingRoles){
				r.getUsers().removeAll(bindingUsers);
				roleRepository.update(r);
			}
		}		
		
		return bindingRoles;
	}

	

	

	
}
