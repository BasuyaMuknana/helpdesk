package com.tradevan.pdis.helpdesk.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tradevan.pdis.helpdesk.model.Content;
import com.tradevan.pdis.helpdesk.repository.ContentRepository;
import com.tradevan.pdis.helpdesk.service.ContentService;



@Service("contentService")
@Transactional(rollbackFor = Exception.class)
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository contentRepository;
    
    private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Override
	public Collection<Content> readAllContents() {
		Collection<Content> contents = null;
		contents = contentRepository.findAll();
		return contents;
	}

	@Override
	public Collection<Content> destroyContents(Collection<Content> contents)
			throws Exception {
		for(Content c : contents){
			Content persistContent = contentRepository.findById(c.getId());
			contentRepository.delete(persistContent);
			logger.info("content:version[{}:{}] has been deleted!",c.getPageType(),c.getVerNo());
		}
		return this.readAllContents();
	}

	@Override
	public Collection<Content> createContents(Collection<Content> contents)
			throws Exception {
		try{
			for(Content c: contents){
				c = contentRepository.save(c);
				logger.info("content:version[{}:{}] has been created!",c.getPageType(),c.getVerNo());
			}
		} catch(Exception e){
			logger.error(e.getMessage(),e);
			throw new Exception("database.manipulating.error");
		}
		return contents;
	}

	@Override
	public Collection<Content> updateContents(Collection<Content> contents)
			throws Exception {
		Collection<Content> updatedContents = new ArrayList<Content>();
		for(Content c : contents){
			Content persistContent = contentRepository.findById(c.getId());
			persistContent.setVerNo(c.getVerNo());
			persistContent.setPageType(c.getPageType());
			persistContent.setContent(c.getContent());
			persistContent.setContentClob(c.getContentClob());
			persistContent = contentRepository.update(persistContent);
			updatedContents.add(persistContent);
			logger.info("content:version[{}:{}] has been updated!",c.getPageType(),c.getVerNo());
		}
		//這裡只能回傳update過的role給kendo datasource
		return updatedContents;
	}


   
}
