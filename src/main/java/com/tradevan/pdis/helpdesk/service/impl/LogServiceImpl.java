package com.tradevan.pdis.helpdesk.service.impl;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tradevan.framework.model.BaseEntity;
import com.tradevan.pdis.helpdesk.model.Log;
import com.tradevan.pdis.helpdesk.repository.ContentRepository;
import com.tradevan.pdis.helpdesk.repository.LogRepository;
import com.tradevan.pdis.helpdesk.service.LogService;



@Service("logService")
@Transactional(rollbackFor = Exception.class)
public class LogServiceImpl implements LogService {
	
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private ContentRepository contentRepository;
    
    private Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);

	@Override
	public Collection<Log> readAllLogs(String filterField, String filterValue) {
		Collection<Log> logs = null;
		BaseEntity example = null;
		if(filterField == null || filterValue == null){
			logs = logRepository.findAll();
		}else{
			switch(filterField){
			case "content_id":
				logger.info("read logs with content_id is [{}]", filterValue);
				example = contentRepository.findById(Long.parseLong(filterValue));
				break;
			}
			
			
			logs = logRepository.findFilteredLogs(example);
		}
		return logs;
	}

	/*public Collection<Log> findByApNUser(String apId, String userId) {
		return logRepository.findByApNUser(apId, userId);
	}


	public Log saveLog(Log log) {
		return logRepository.save(log);
	}


	public Log findById(long id) {
		return logRepository.findById(id);
	}*/

	
    
   
}
