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
import com.tradevan.pdis.helpdesk.model.Log;
import com.tradevan.pdis.helpdesk.model.User;
import com.tradevan.pdis.helpdesk.service.ContentService;
import com.tradevan.pdis.helpdesk.service.LogService;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LogAction extends JsonAction {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2479039312497305997L;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String models; //kendo grid models
	private HashMap<String,Object> returnMap = new HashMap<String,Object>();
	private String jsonString = null;
	private ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	private String userId = SecurityContextHolder.getContext().getAuthentication().getName();
	private Collection<Log> inOutLogs = new ArrayList<Log>();
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private ContentService contentService;
	
	private String filterField;
	private String filterValue;
	

	@Override
	public String execute() throws Exception {
		return "SUCCESS";
	}
	
//	public String getApIdCandi() throws Exception {
//		logger.info("[{}] requests [log_getApIdCandi]", userId);
//		ArrayList<String> apIdList = new ArrayList<String>();
//		
////		apIdList = LogService.getApIdCandidates();
//		
//		if (apIdList != null && apIdList.size() > 0){
//			returnMap.put("results", apIdList);		
//		}else{
//			returnMap.put("errors", "contents collection is empty");
//		}	
//		jsonString = JsonUtil.toJsonString(returnMap);
//		logger.info("[log_getApIdCandi] response to [{}] : [{}]", userId, jsonString);
//		this.setJsonData(jsonString);
//		return JSON;
//	}
	
	public String readAll() {
		logger.info("[{}] requests [log_readAll] with filterField[{}], filterValue[{}]", userId, filterField,filterValue);
		inOutLogs = logService.readAllLogs(filterField,filterValue);
		
		//if (inOutLogs != null && inOutLogs.size() > 0){
			returnMap.put("results", inOutLogs);
			//returnMap.put("total", inOutLogs.size());
			
		//}else{
			//returnMap.put("errors", "contents collection is empty");
		//}
		jsonString = JsonUtil.toJsonString(returnMap);
		logger.info("[log_readAll] response to [{}] : [{}]", userId, jsonString);
		this.setJsonData(jsonString);
		return JSON;
	}

	public String getFilterField() {
		return filterField;
	}

	public void setFilterField(String filterField) {
		this.filterField = filterField;
	}

	public String getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}

	
	
	
	

	


}
