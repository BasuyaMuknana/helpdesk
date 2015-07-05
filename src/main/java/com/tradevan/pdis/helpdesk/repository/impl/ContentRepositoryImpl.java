/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tradevan.pdis.helpdesk.repository.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.tradevan.framework.repository.JpaBaseRepository;
import com.tradevan.pdis.helpdesk.model.Content;
import com.tradevan.pdis.helpdesk.model.Content_;
import com.tradevan.pdis.helpdesk.repository.ContentRepository;





@Repository
public class ContentRepositoryImpl extends JpaBaseRepository<Content, Long> implements ContentRepository {
	public static final String COL_PAGE_TYPE = Content_.pageType.getName();
	public static final String COL_VER_NO = Content_.verNo.getName();

	
	public Content findLatestByPageType(String pageType)
			throws DataAccessException {
		Map<String, Object> params = new HashMap<String, Object>();
   		params.put(COL_PAGE_TYPE, pageType);
		
   		return this.findByNamedQuery("Content.findLatestByPageType", params).iterator().next();
	}

	
	public Collection<Content> findByPageType(String pageType)
			throws DataAccessException {
		Map<String, Object> params = new HashMap<String, Object>();
   		params.put(COL_PAGE_TYPE, pageType);
		return this.findByNamedQuery("Content.findByPageType", params);
	}

	
	public Content findByPageTypeNVerNo(String pageType, long verNo)
			throws DataAccessException {
		Map<String, Object> params = new HashMap<String, Object>();
   		params.put(COL_PAGE_TYPE, pageType);
   		params.put(COL_VER_NO, verNo);
   		return this.findByNamedQuery("Content.findByPageTypeNVerNo", params).iterator().next();
	}

	

    
}
