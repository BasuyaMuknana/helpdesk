package com.tradevan.pdis.helpdesk.repository;

import java.util.Collection;

import com.tradevan.framework.repository.BaseRepository;
import com.tradevan.pdis.helpdesk.model.User;


public interface UserRepository extends BaseRepository<User, Long>{
	public User findOneByExample(User userExample);
	public Collection<User> findAllByExample(User userExample);
}
