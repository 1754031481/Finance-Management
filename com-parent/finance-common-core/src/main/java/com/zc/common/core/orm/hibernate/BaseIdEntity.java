package com.zc.common.core.orm.hibernate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zc.common.core.json.jackson.convertor.JacksonConvertorDate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.util.ClassUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author zhangkaoqin
 * 
 */
@MappedSuperclass
public abstract class BaseIdEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Field
	@Column(name = "id")
	public Long id;
	// 建立的用户
	@Column(name = "create_user")
	public String createUser;
	// 建立的时间
	@JsonSerialize(using = JacksonConvertorDate.class)
	@Column(name = "created_time")
	public Date createdTime;
	// 更新时间
	@JsonSerialize(using = JacksonConvertorDate.class)
	@Column(name = "update_time")
	public Date updateTime;
	// 建立的ip
	@Column(name = "created_ip")
	public String createdIp ;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedIp() {
		return createdIp;
	}

	public void setCreatedIp(String createdIp) {
		this.createdIp = createdIp;
	}

	@Column(nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	/**
	 * 在创建之前设定时间
	 */
	@PrePersist
	public void onCreate() {
		createdTime = new Date();
		updateTime = new Date();
		String des = "org.springframework.security.core.Authentication";
		if (ClassUtils.isPresent(des,ClassUtils.getDefaultClassLoader())) {
		}
	}

	/**
	 * 在更新的时候设定时间
	 */
	@PreUpdate
	public void onUpdate() {
		updateTime = new Date();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
