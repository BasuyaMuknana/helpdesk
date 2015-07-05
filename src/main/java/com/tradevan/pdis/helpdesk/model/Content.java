package com.tradevan.pdis.helpdesk.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.tradevan.framework.model.BaseEntity;

@Entity
@Table(
		name = "PD_PAGE_CONTENT_JPA",
		uniqueConstraints= @UniqueConstraint(columnNames={"PAGE_TYPE", "VER_NO"})
)
@NamedQueries({
		@NamedQuery(name = "Content.findByPageType", 
		query = "SELECT content From Content content WHERE content.pageType = :pageType"),
		@NamedQuery(name = "Content.findByPageTypeNVerNo", 
		query = "SELECT content From Content content WHERE content.pageType = :pageType AND content.verNo = :verNo"),
		@NamedQuery(name = "Content.findLatestByPageType", 
		query = "SELECT content From Content content WHERE content.pageType = :pageType ORDER BY content.verNo DESC") 
})
public class Content extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7603634563188632480L;

	/*@Id
	@Column(name="CONTENT_ID")
	@OneToMany(mappedBy="content")
	private long contentId;*/

	@Column(name="PAGE_TYPE")
	@NotNull
	private String pageType;
	
	@Column(name="VER_NO")
	@NotNull
	private long verNo;
	
	@Column(name="CONTENT",length=2048)
	private String content;
	
	@Column(name="CONTENT_CLOB",length=4000)
	@Lob
	@Type(type = "org.hibernate.type.TextType") //告訴hibernate要把CLOB以文字形態處理，否則只會有一個OID而出現Bad value for type long的錯誤
	private String contentClob;

	

	/*public long getContentId() {
		return contentId;
	}

	public void setContentId(long contentId) {
		this.contentId = contentId;
	}*/

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public long getVerNo() {
		return verNo;
	}

	public void setVerNo(long verNo) {
		this.verNo = verNo;
	}

	public String getContentClob() {
		return contentClob;
	}

	public void setContentClob(String contentClob) {
		this.contentClob = contentClob;
	}
	
	/*@Column(name="CREATED_TIME")
	private Date createdTime;
	

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}*/

	
	
}
