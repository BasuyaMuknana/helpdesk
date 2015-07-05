package com.tradevan.pdis.helpdesk.repository;

import java.util.Collection;
import org.springframework.dao.DataAccessException;
import com.tradevan.framework.repository.BaseRepository;
import com.tradevan.pdis.helpdesk.model.Content;

public interface ContentRepository extends BaseRepository<Content, Long> {

	Content findLatestByPageType(String pageType)
			throws DataAccessException;
	
	Content findByPageTypeNVerNo(String pageType, long verNo)
			throws DataAccessException;
		
	Collection<Content> findByPageType(String pageType)
			throws DataAccessException;

}
