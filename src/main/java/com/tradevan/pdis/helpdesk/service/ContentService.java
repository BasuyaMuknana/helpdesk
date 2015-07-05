package com.tradevan.pdis.helpdesk.service;

import java.util.Collection;

import com.tradevan.pdis.helpdesk.model.Content;

public interface ContentService {
	public Collection<Content> readAllContents();
	public Collection<Content> destroyContents(Collection<Content> roles) throws Exception;
	public Collection<Content> createContents(Collection<Content> roles) throws Exception;
	public Collection<Content> updateContents(Collection<Content> roles) throws Exception;
}
