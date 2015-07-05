package com.tradevan.pdis.helpdesk.action.admin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradevan.framework.action.JsonAction;
import com.tradevan.framework.model.BaseEntity;
import com.tradevan.framework.util.JsonUtil;
import com.tradevan.pdis.helpdesk.model.Role;
import com.tradevan.pdis.helpdesk.model.User;
import com.tradevan.pdis.helpdesk.service.UserService;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAction extends JsonAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8518162097728838001L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private String models; //kendo grid models
	private HashMap<String,Object> returnMap = new HashMap<String,Object>();
	private String jsonString = null;
	private ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	private String userId = SecurityContextHolder.getContext().getAuthentication().getName();
	private Collection<User> inOutUsers = new ArrayList<User>();
	
	@Autowired
	private UserService userService;
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String readAll() {
		logger.info("[{}] requests [user_readAll]", userId);
		inOutUsers = userService.readAllUsers();
		
		if (inOutUsers != null && inOutUsers.size() > 0){
			returnMap.put("results", inOutUsers);
			
		}else{
			returnMap.put("errors", "roles collection is empty");
		}
		jsonString = JsonUtil.toJsonString(returnMap);
		logger.info("[user_readAll] response to [{}] : [{}]", userId, jsonString);
		this.setJsonData(jsonString);
		return JSON;
	}
	
	public String create() {
		logger.info("[{}] requests [user_create]", userId);
		logger.info("create models [{}]", models);		
		try {
			inOutUsers = jsonToPojoCollection(models);
//			for(User u : users){
//				logger.debug("after deserialize: role_name[{}]",u.getUserName());
//			}			
			inOutUsers = userService.createUsers(inOutUsers);
			returnMap.put("results", inOutUsers);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnMap.put("errors", e.getMessage());
		} 
		
		jsonString = JsonUtil.toJsonString(returnMap);		
		logger.info("[user_create] response to [{}] : [{}]", userId, jsonString);
		this.setJsonData(jsonString);
		return JSON;
	}
	
	public String destroy(){
		logger.info("[{}] requests [user_destroy]", userId);
		logger.info("destroy models [{}]", models);
		try {
			inOutUsers = jsonToPojoCollection(models);
			inOutUsers = userService.destroyUsers(inOutUsers);
			returnMap.put("results", inOutUsers);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnMap.put("errors", e.getMessage());
		}
				
		jsonString = JsonUtil.toJsonString(returnMap);		
		logger.info("[user_destroy] response to [{}] : [{}]", userId, jsonString);
		this.setJsonData(jsonString);
		return JSON;
	}
	
	public String update(){
		logger.info("[{}] requests [user_update]", userId);
		logger.info("update models [{}]", models);
		try {
			inOutUsers = jsonToPojoCollection(models);
			inOutUsers = userService.updateUsers(inOutUsers);
			returnMap.put("results", inOutUsers);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnMap.put("errors", e.getMessage());
		}
				
		jsonString = JsonUtil.toJsonString(returnMap);		
		logger.info("[user_update] response to [{}] : [{}]", userId, jsonString);
		this.setJsonData(jsonString);
		return JSON;
	}
	
	public String queryRoles(){
		logger.info("[{}] requests [user_queryRoles]", userId);
		logger.info("queryRoles models [{}]", models);
		Collection<Role> userRoles;
		User user = null;
		try {
			JsonNode node = mapper.readTree(models);
			JsonNode userNode = node.get("user");
			user = mapper.readValue(userNode.toString(),  User.class);
			logger.debug("Actionexample userName[{}]",user.getUserName());
			logger.debug("Actionexample userId[{}]",user.getId());
			userRoles = userService.queryRoles(user);
			returnMap.put("results", userRoles);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnMap.put("errors", e.getMessage());
		}
				
		jsonString = JsonUtil.toJsonString(returnMap);		
		logger.info("[user_queryRoles] response to [{}] : [{}]", userId, jsonString);
		this.setJsonData(jsonString);
		return JSON;
	}
	
	public String bindRoles(){
		logger.info("[{}] requests [user_bindRoles]", userId);
		logger.info("bindRoles models [{}]", models);

		try {
			JsonNode node = mapper.readTree(models);
			JsonNode rolesNode = node.get("roles");
			JsonNode usersNode = node.get("users");
			Collection<Role> roles = mapper.readValue(rolesNode.toString(),  mapper.getTypeFactory().constructCollectionType(Collection.class, Role.class));
			User user = mapper.readValue(usersNode.toString(),  User.class);
			user = userService.bindRoles(user,roles);
			returnMap.put("results", user);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnMap.put("errors", e.getMessage());
		}			
		jsonString = JsonUtil.toJsonString(returnMap);		
		logger.info("[role_bindUsers] response to [{}] : [{}]", userId, jsonString);
		this.setJsonData(jsonString);
		
		return JSON;
	}
	
	
	
	private Collection<User> jsonToPojoCollection(String models) throws Exception{
		Collection<User> users = null;
		
		try {
			users = mapper.readValue(models, mapper.getTypeFactory().constructCollectionType(Collection.class, User.class));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new Exception("jsonToPojo.fail");
		}		
		return users;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getModels() {
		return models;
	}

	public void setModels(String models) {
		this.models = models;
	}
	
	
}
