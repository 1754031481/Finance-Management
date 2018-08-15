package com.zc.entity.finance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zc.common.core.orm.hibernate.BaseIdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "finance")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Finance extends BaseIdEntity{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "project")
	private String project;

	@Column(name = "total_money")
	private String totalMoney;

	@Column(name = "total_count")
	private String totalCount;

	@Column(name = "status")
	private Integer status;

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Finance{" +
				"project='" + project + '\'' +
				", totalMoney='" + totalMoney + '\'' +
				", totalCount='" + totalCount + '\'' +
				", status='" + status + '\'' +
				", id=" + id +
				", createUser='" + createUser + '\'' +
				", createdTime=" + createdTime +
				", updateTime=" + updateTime +
				", createdIp='" + createdIp + '\'' +
				'}';
	}
}
