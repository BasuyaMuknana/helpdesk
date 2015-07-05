package com.tradevan.pdis.helpdesk.service;



import java.util.ArrayList;
import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import com.tradevan.pdis.helpdesk.model.Content;
import com.tradevan.pdis.helpdesk.model.Log;




public interface LogService {
	/*public Collection<Log> findByApNUser(String apId, String userId);
	
	public Log findById(long id);
	
	@Transactional
	public Log saveLog(Log log);*/
	
//	public Collection<Content> readAllLogs(String PageType);

	Collection<Log> readAllLogs(String filterField, String filterValue);
	
}
