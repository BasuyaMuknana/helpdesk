package com.tradevan.pdis.helpdesk.repository;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.dao.DataAccessException;

import com.tradevan.framework.model.BaseEntity;
import com.tradevan.framework.repository.BaseRepository;
import com.tradevan.pdis.helpdesk.model.Log;


public interface LogRepository extends BaseRepository<Log, Long> {
	/*Collection<Log> findByApNUser(String apId, String userId)
			throws DataAccessException;*/
	//ArrayList<String> getDistinctApId() throws Exception;
//	Collection<Log> findFilteredLogs(String filterField, String filterValue);
	public Collection<Log> findFilteredLogs(BaseEntity example);
}
