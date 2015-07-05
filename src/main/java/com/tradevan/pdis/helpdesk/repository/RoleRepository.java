package com.tradevan.pdis.helpdesk.repository;

import java.util.Collection;

import com.tradevan.framework.repository.BaseRepository;
import com.tradevan.pdis.helpdesk.model.Role;


public interface RoleRepository extends BaseRepository<Role, Long>{
	public Role findOneByExample(Role roleExample);
	public Collection<Role> findAllByExample(Role roleExample);
	public void deleteRoles(Collection<Role> roles);
}
