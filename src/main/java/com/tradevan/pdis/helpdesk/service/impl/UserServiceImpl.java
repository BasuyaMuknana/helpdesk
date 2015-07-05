package com.tradevan.pdis.helpdesk.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;










import javax.annotation.Resource;

import org.jasypt.spring.security3.PasswordEncoder;
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
import com.tradevan.pdis.helpdesk.service.UserService;

/**
 * 因設計上將user基本資料的操作及和role之間的關係操作分開，所以方法上分三個群組
 * 1.user基本資料操作
 * 		readAllUsers
 * 		createUsers
 * 		updateUsers
 * 		destroyUsers(這個method會連同user_role關係一同刪除)
 * 2.user_role關係操作
 * 		readUserRoles
 * 		writeUserRoles
 * 3.其他獨立操作
 * 		updatePwd(因為updateUsers無法判斷密碼有無被修改，所以開設獨立功能)
 * 
 * @author 2904
 *
 */
@Service("userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
	protected static final String DEFAULT_USER_ROLE = "ROLE_USER";
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	private PasswordEncoder pwdEncoder;

	@Override
	public Collection<User> readAllUsers() {
		Collection<User> users = null;
		users = userRepository.findAll();
		return users;
	}

	@Override
	public Collection<User> destroyUsers(Collection<User> users)
			throws Exception {
		//User.java中設定CascadeType.REMOVE，使刪除user時一併刪除對應的user_role關係
		for(User u : users){
			User persistUser = userRepository.findById(u.getId());
			for(Role r: persistUser.getRoles()){
				r.getUsers().remove(persistUser);
			}			
			persistUser.setRoles(null);
			userRepository.delete(u);
			logger.info("user[{}] has been deleted!",u.getUserName());
		}
		return this.readAllUsers();
	}

	@Override
	public Collection<User> createUsers(Collection<User> users)
			throws Exception {
		//add default role: ROLE_USER
		//User.java中設定CascadeType.PERSIST，使增加user時一併增加預設的user_role關係
		users = this.addDefaultRolesAndHashPwdForNewUser(users);
		
		try{
			for(User u: users){
				u = userRepository.save(u);
				logger.info("user[{}] has been created!",u.getUserName());
			}
		} catch(Exception e){
			logger.error(e.getMessage(),e);
			throw new Exception("database.manipulating.error");
		}
		return users;
	}

	@Override
	public Collection<User> updateUsers(Collection<User> users)
			throws Exception {
		Collection<User> updatedUsers = new ArrayList<User>();
		for(User u : users){
			User persistUser = userRepository.findById(u.getId());
			persistUser.setApId(u.getApId());
			persistUser.setEmail(u.getEmail());
			persistUser.setEnabled(u.getEnabled());
			persistUser.setUserName(u.getUserName());
			persistUser.setPassword(pwdEncoder.encodePassword(u.getPassword(), null));
			persistUser = userRepository.update(persistUser);
			updatedUsers.add(persistUser);
			logger.info("user[{}] has been updated!",u.getUserName());
		}
		//這裡只能回傳update過的role給kendo datasource
		return updatedUsers;
	}
	
	@Override
	public Collection<Role> queryRoles(User user) throws Exception{
		Collection<Role> userRoles = null;
		User persistUser = userRepository.findOneByExample(user);
		return persistUser.getRoles();
	}
	
	@Override
	public User bindRoles(User user, Collection<Role> roles) throws Exception {	
		User bindingUser = null;
		Set<Role> bindingRoles = new HashSet<Role>();
		
		//先把persist user找出來
		bindingUser = userRepository.findOneByExample(user);
		
		//先把persist role找出來
		for(Role r : roles){
			r = roleRepository.findOneByExample(r);
			bindingRoles.add(r);
		}
		
		bindingUser.setRoles(bindingRoles);
		bindingUser = userRepository.update(bindingUser);
		
		return bindingUser;
	}
	
	/**
	 * 新增使用者時使用，替要新增的使用者加上預設角色與密碼(同username)
	 * @param newUsers
	 * @return
	 */
	private Collection<User> addDefaultRolesAndHashPwdForNewUser(Collection<User> newUsers){
		Set<Role> defaultRoles = new HashSet<Role>();
		Role roleEx = new Role();
		roleEx.setRoleName(DEFAULT_USER_ROLE);
		Role r = roleRepository.findOneByExample(roleEx);
		defaultRoles.add(r);
		
		for(User u : newUsers){
			u.setRoles(defaultRoles);
			u.setPassword(pwdEncoder.encodePassword(u.getPassword(), null));
		}
		return newUsers;
	}

	public PasswordEncoder getPwdEncoder() {
		return pwdEncoder;
	}
	
	@Resource(name="pwdEncoder")
	public void setPwdEncoder(PasswordEncoder pwdEncoder) {
		this.pwdEncoder = pwdEncoder;
	}


	
}
