package com.tradevan.pdis.helpdesk.action.admin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradevan.framework.action.JsonAction;
import com.tradevan.framework.model.BaseEntity;
import com.tradevan.framework.util.JsonUtil;
import com.tradevan.pdis.helpdesk.model.Role;
import com.tradevan.pdis.helpdesk.model.User;
import com.tradevan.pdis.helpdesk.service.RoleService;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleAction extends JsonAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1197556787327447708L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String models; //kendo grid models
	private HashMap<String,Object> returnMap = new HashMap<String,Object>();
	private String jsonString = null;
	private ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	private String userId = SecurityContextHolder.getContext().getAuthentication().getName();
	private Collection<Role> inOutRoles = new ArrayList<Role>();
	
	@Autowired
	private RoleService roleService;
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String readAll() {
		logger.info("[{}] requests [role_readAll]", userId);
		inOutRoles = roleService.readAllRoles();
		
		if (inOutRoles != null && inOutRoles.size() > 0){
			returnMap.put("results", inOutRoles);
			
		}else{
			returnMap.put("errors", "roles collection is empty");
		}
		jsonString = JsonUtil.toJsonString(returnMap);
		logger.info("[role_readAll] response to [{}] : [{}]", userId, jsonString);
		this.setJsonData(jsonString);
		return JSON;
	}
	
	public String create() {
		logger.info("[{}] requests [role_create]", userId);
		logger.info("create models [{}]", models);
		try {
			inOutRoles = jsonToPojoCollection(models);
			for(Role r : inOutRoles){
				logger.debug("after deserialize: role_name[{}]",r.getRoleName());
			}
			
			inOutRoles = roleService.createRoles(inOutRoles);
			returnMap.put("results", inOutRoles);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnMap.put("errors", e.getMessage());
		} 	
		jsonString = JsonUtil.toJsonString(returnMap);		
		logger.info("[role_create] response to [{}] : [{}]", userId, jsonString);
		this.setJsonData(jsonString);
		return JSON;
	}
	
	public String destroy(){
		logger.info("[{}] requests [role_destroy]", userId);
		logger.info("destroy models [{}]", models);
		try {
			inOutRoles = jsonToPojoCollection(models);
			inOutRoles = roleService.destroyRoles(inOutRoles);
			returnMap.put("results", inOutRoles);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnMap.put("errors", e.getMessage());
		}
				
		jsonString = JsonUtil.toJsonString(returnMap);		
		logger.info("[role_destroy] response to [{}] : [{}]", userId, jsonString);
		this.setJsonData(jsonString);
		return JSON;
	}
	
	public String update(){
		logger.info("[{}] requests [role_update]", userId);
		logger.info("update models [{}]", models);
		try {
			inOutRoles = jsonToPojoCollection(models);
			inOutRoles = roleService.updateRoles(inOutRoles);
			returnMap.put("results", inOutRoles);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnMap.put("errors", e.getMessage());
		}
				
		jsonString = JsonUtil.toJsonString(returnMap);		
		logger.info("[role_update] response to [{}] : [{}]", userId, jsonString);
		this.setJsonData(jsonString);
		return JSON;
	}
	
	public String bindUsers(){
		logger.info("[{}] requests [role_bindUsers]", userId);
		logger.info("bindUsers models [{}]", models);

		try {
			JsonNode node = mapper.readTree(models);
			JsonNode rolesNode = node.get("roles");
			JsonNode usersNode = node.get("users");
			JsonNode opNode = node.get("op");
//			System.out.println("rolesNode="+rolesNode);
			Collection<Role> roles = mapper.readValue(rolesNode.toString(),  mapper.getTypeFactory().constructCollectionType(Collection.class, Role.class));
			Collection<User> users = mapper.readValue(usersNode.toString(),  mapper.getTypeFactory().constructCollectionType(Collection.class, User.class));
			String op = mapper.readValue(opNode.toString(), String.class );
			
			//inputMap = mapper.readValue(models, HashMap.class);
			//buPOJO = mapper.readValue(models, BindUserPOJO.class);
			//inOutRoles = roleService.bindUsers(inputMap);
			inOutRoles = roleService.bindUsers(roles,users,op);
			returnMap.put("results", inOutRoles);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnMap.put("errors", e.getMessage());
		}			
		jsonString = JsonUtil.toJsonString(returnMap);		
		logger.info("[role_bindUsers] response to [{}] : [{}]", userId, jsonString);
		this.setJsonData(jsonString);
		
		return JSON;
	}
	
	private Collection<Role> jsonToPojoCollection(String models) throws Exception{
		Collection<Role> roles = null;
		
		try {
			roles = mapper.readValue(models, mapper.getTypeFactory().constructCollectionType(Collection.class, Role.class));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new Exception("jsonToPojo.fail");
		}		
		return roles;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public String getModels() {
		return models;
	}

	public void setModels(String models) {
		this.models = models;
	}
	
//	jackson 只能deserialize static inner class
//	public class BindUserPOJO{
//		private Collection<User> users;
//		private Collection<Role> roles;
//		private String op;
//		
//		//Introducing the dummy constructor
//	    public BindUserPOJO() {
//	    }
//		
//		public BindUserPOJO(Collection<User> users, Collection<Role> roles, String op){
//			this.users = users;
//			this.roles = roles;
//			this.op = op;
//		}
//		public Collection<User> getUsers() {
//			return users;
//		}
//		public void setUsers(Collection<User> users) {
//			this.users = users;
//		}
//		public Collection<Role> getRoles() {
//			return roles;
//		}
//		public void setRoles(Collection<Role> roles) {
//			this.roles = roles;
//		}
//		public String getOp() {
//			return op;
//		}
//		public void setOp(String op) {
//			this.op = op;
//		}
//		
//	}
	


	
	
}
