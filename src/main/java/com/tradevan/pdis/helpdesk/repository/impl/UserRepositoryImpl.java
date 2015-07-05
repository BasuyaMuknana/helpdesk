package com.tradevan.pdis.helpdesk.repository.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.tradevan.framework.repository.JpaBaseRepository;
import com.tradevan.pdis.helpdesk.model.User;
import com.tradevan.pdis.helpdesk.model.User_;
import com.tradevan.pdis.helpdesk.repository.UserRepository;

@Repository
public class UserRepositoryImpl extends JpaBaseRepository<User, Long>
		implements UserRepository {

//	public User findByUsername(String username){
//		CriteriaBuilder cb = em.getCriteriaBuilder(); 
//		CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
//		Root<User> contactRoot = criteriaQuery.from(User.class);
//		//contactRoot.fetch(User_.specialties, JoinType.LEFT);
//		criteriaQuery.select(contactRoot).distinct(true);
//		
//		Predicate criteria = cb.conjunction();
//		if (username != null) {
//			Predicate p = cb.equal(contactRoot.get(User_.userName), username);
//			criteria = cb.and(criteria, p);
//		}
//		
//		criteriaQuery.where(criteria);
//		return this.findByCriteriaQuery(criteriaQuery, 0).iterator().next();
//	}

	@Override
	public User findOneByExample(User userExample) {		
		return findAllByExample(userExample).iterator().next();
	}

	@Override
	public Collection<User> findAllByExample(User userExample) {
		CriteriaBuilder cb = em.getCriteriaBuilder(); 
		CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
		Root<User> contactRoot = criteriaQuery.from(User.class);
		criteriaQuery.select(contactRoot).distinct(true);
		Predicate criteria = cb.conjunction();
		if (userExample.getId() != null) {
			Predicate p = cb.equal(contactRoot.get(User_.id), userExample.getId());
			criteria = cb.and(criteria, p);
		}else{
			if (userExample.getUserName() != null) {
				Predicate p = cb.equal(contactRoot.get(User_.userName), userExample.getUserName());
				criteria = cb.and(criteria, p);
			}
			if (userExample.getEmail() != null) {
				Predicate p = cb.equal(contactRoot.get(User_.email), userExample.getEmail());
				criteria = cb.and(criteria, p);
			}
			if (userExample.getPassword() != null) {
				Predicate p = cb.equal(contactRoot.get(User_.password), userExample.getPassword());
				criteria = cb.and(criteria, p);
			}
		}
		criteriaQuery.where(criteria);
		return this.findByCriteriaQuery(criteriaQuery, 0);
	}

	
}
