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

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.tradevan.framework.model.BaseEntity;
import com.tradevan.framework.repository.JpaBaseRepository;
import com.tradevan.pdis.helpdesk.model.Content;
import com.tradevan.pdis.helpdesk.model.Log;
import com.tradevan.pdis.helpdesk.model.Log_;
import com.tradevan.pdis.helpdesk.repository.LogRepository;







@Repository
public class LogRepositoryImpl extends JpaBaseRepository<Log, Long> implements LogRepository {
	public static final String COL_AP_ID = Log_.apId.getName();
	public static final String COL_USER_ID = Log_.userId.getName();
	public static final String COL_CONTENT_ID = Log_.content.getName();
	@Override
	public Collection<Log> findFilteredLogs(BaseEntity example) {
		//Log logEx = new Log();
		Content contentEx = null;

		//logEx.setContent(contentEx);
		CriteriaBuilder cb = em.getCriteriaBuilder(); 
		CriteriaQuery<Log> criteriaQuery = cb.createQuery(Log.class);
		Root<Log> contactRoot = criteriaQuery.from(Log.class);
		criteriaQuery.select(contactRoot);
		Predicate criteria = cb.conjunction();

		if (example instanceof Content) {
			contentEx = (Content)example;
			Predicate p = cb.equal(contactRoot.get(Log_.content), contentEx);
			
			criteria = cb.and(criteria, p);
		}
		
		criteriaQuery.where(criteria);
		return this.findByCriteriaQuery(criteriaQuery, 0);

	}
	
	
	
	
//	@Override
//	public ArrayList<String> getDistinctApId() throws Exception {
//		// using JPA2 CriteriaQuery 
//		CriteriaBuilder cb = em.getCriteriaBuilder(); 
//		CriteriaQuery<Log> criteriaQuery = cb.createQuery(Log.class);
//		Root<Log> contactRoot = criteriaQuery.from(Log.class);
//		criteriaQuery.select(contactRoot).distinct(true);
//
//		return this.findByCriteriaQuery(criteriaQuery, 0);
//		return null;
//	}


	/*public Collection<Log> findByApNUser(String apId, String userId)
			throws DataAccessException {
		Map<String, Object> params = new HashMap<String, Object>();
   		params.put(COL_AP_ID, apId);
   		params.put(COL_USER_ID, userId);
		return this.findByNamedQuery("Log.findByApNUser", params);
	}*/
	
	
	

    
}
