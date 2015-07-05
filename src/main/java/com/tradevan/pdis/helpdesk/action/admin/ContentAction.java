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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradevan.framework.action.JsonAction;
import com.tradevan.framework.util.JsonUtil;
import com.tradevan.pdis.helpdesk.model.Content;
import com.tradevan.pdis.helpdesk.service.ContentService;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentAction extends JsonAction {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4754158662637800728L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String models; //kendo grid models
	private HashMap<String,Object> returnMap = new HashMap<String,Object>();
	private String jsonString = null;
	private ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	private String userId = SecurityContextHolder.getContext().getAuthentication().getName();
	private Collection<Content> inOutContents = new ArrayList<Content>();
	
	@Autowired
	private ContentService contentService;
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String readAll() {
		logger.info("[{}] requests [content_readAll]", userId);
		inOutContents = contentService.readAllContents();
		
		if (inOutContents != null && inOutContents.size() > 0){
			returnMap.put("results", inOutContents);
			
		}else{
			returnMap.put("errors", "contents collection is empty");
		}
		jsonString = JsonUtil.toJsonString(returnMap);
		logger.info("[content_readAll] response to [{}] : [{}]", userId, jsonString);
		this.setJsonData(jsonString);
		return JSON;
	}
	
	public String create() {
		logger.info("[{}] requests [content_create]", userId);
		logger.info("create models [{}]", models);
		try {
			inOutContents = jsonToPojoCollection(models);
			for(Content c : inOutContents){
				logger.debug("after deserialize: pageType[{}]",c.getPageType());
			}
			
			inOutContents = contentService.createContents(inOutContents);
			returnMap.put("results", inOutContents);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnMap.put("errors", e.getMessage());
		} 	
		jsonString = JsonUtil.toJsonString(returnMap);		
		logger.info("[content_create] response to [{}] : [{}]", userId, jsonString);
		this.setJsonData(jsonString);
		return JSON;
	}
	
	public String destroy(){
		logger.info("[{}] requests [content_destroy]", userId);
		logger.info("destroy models [{}]", models);
		try {
			inOutContents = jsonToPojoCollection(models);
			inOutContents = contentService.destroyContents(inOutContents);
			returnMap.put("results", inOutContents);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnMap.put("errors", e.getMessage());
		}
				
		jsonString = JsonUtil.toJsonString(returnMap);		
		logger.info("[content_destroy] response to [{}] : [{}]", userId, jsonString);
		this.setJsonData(jsonString);
		return JSON;
	}
	
	public String update(){
		logger.info("[{}] requests [content_update]", userId);
		logger.info("update models [{}]", models);
		try {
			inOutContents = jsonToPojoCollection(models);
			inOutContents = contentService.updateContents(inOutContents);
			returnMap.put("results", inOutContents);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnMap.put("errors", e.getMessage());
		}
				
		jsonString = JsonUtil.toJsonString(returnMap);		
		logger.info("[content_update] response to [{}] : [{}]", userId, jsonString);
		this.setJsonData(jsonString);
		return JSON;
	}
	
	private Collection<Content> jsonToPojoCollection(String models) throws Exception{
		Collection<Content> contents = null;
		
		try {
			contents = mapper.readValue(models, mapper.getTypeFactory().constructCollectionType(Collection.class, Content.class));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new Exception("jsonToPojo.fail");
		}		
		return contents;
	}

	
	public ContentService getContentService() {
		return contentService;
	}

	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	public String getModels() {
		return models;
	}

	public void setModels(String models) {
		this.models = models;
	}
	
	
}
