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
import com.tradevan.pdis.helpdesk.model.Role;
import com.tradevan.pdis.helpdesk.model.Role_;
import com.tradevan.pdis.helpdesk.model.User;
import com.tradevan.pdis.helpdesk.repository.RoleRepository;
import com.tradevan.pdis.helpdesk.repository.UserRepository;


@Repository
public class RoleRepositoryImpl extends JpaBaseRepository<Role, Long>
		implements RoleRepository {

	@Override
	public Role findOneByExample(Role roleExample) {
		return findAllByExample(roleExample).iterator().next();
	}

	@Override
	public Collection<Role> findAllByExample(Role roleExample) {
		CriteriaBuilder cb = em.getCriteriaBuilder(); 
		CriteriaQuery<Role> criteriaQuery = cb.createQuery(Role.class);
		Root<Role> contactRoot = criteriaQuery.from(Role.class);
		criteriaQuery.select(contactRoot).distinct(true);
		Predicate criteria = cb.conjunction();
		
		if (roleExample.getId()!= null) {
			Predicate p1 = cb.equal(contactRoot.get(Role_.id), roleExample.getId());
			criteria = cb.and(criteria, p1);
		}else{
			if (roleExample.getRoleName() != null) {
				Predicate p1 = cb.equal(contactRoot.get(Role_.roleName), roleExample.getRoleName());
				criteria = cb.and(criteria, p1);
			}
		}
		criteriaQuery.where(criteria);
		return this.findByCriteriaQuery(criteriaQuery, 0);
	}

	@Override
	public void deleteRoles(Collection<Role> roles) {
		for(Role r : roles){
			this.delete(r);
		}
	}

//	@Override
//	public Role findByRoleName(String roleName) {
//		CriteriaBuilder cb = em.getCriteriaBuilder(); 
//		CriteriaQuery<Role> criteriaQuery = cb.createQuery(Role.class);
//		Root<Role> contactRoot = criteriaQuery.from(Role.class);
//		criteriaQuery.select(contactRoot).distinct(true);
//		Predicate criteria = cb.conjunction();
//		if (roleName != null) {
//			Predicate p1 = cb.equal(contactRoot.get(Role_.roleName), roleName);
//			criteria = cb.and(criteria, p1);
//		}		
//		criteriaQuery.where(criteria);
//		return this.findByCriteriaQuery(criteriaQuery, 0).iterator().next();		
//	}

	

	
}
