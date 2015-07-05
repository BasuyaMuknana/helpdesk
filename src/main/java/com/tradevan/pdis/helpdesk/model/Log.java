package com.tradevan.pdis.helpdesk.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tradevan.framework.model.BaseEntity;

@Entity
@Table(
		name = "PD_LOG_HISTORY_JPA",
		uniqueConstraints= @UniqueConstraint(columnNames={"AP_ID", "USER_ID", "CONTENT_ID"})
)
@NamedQueries({
		@NamedQuery(name = "Log.findByApNUser", query = "SELECT log From Log log WHERE log.apId = :apId AND log.userId = :userId")
		 })
public class Log extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 805714056509177617L;
	
	/*@Id
	@Column(name="Log_ID")
	private long logId;*/

	@Column(name="AP_ID")
	@NotNull
	private String apId;
	
	@Column(name="USER_ID")
	@NotNull
	private String userId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CONTENT_ID")
	@NotNull
	@JsonIgnore
	private Content content;
	
	@Column(name="DESCRIPTION")
	private String desc;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_TIME")
	private Date createdTime;
	
	@Column(name="USER_IP")
	private String userIp;
	
	public String getApId() {
		return apId;
	}

	public void setApId(String apId) {
		this.apId = apId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	/*public long getLogId() {
		return logId;
	}

	public void setLogId(long logId) {
		this.logId = logId;
	}*/

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}
	
}
