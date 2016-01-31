package com.persistent.securityPractice.armature.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

public class Assessment implements Serializable {
	private Long id;
	private Long userId;
	private String userName;
	private Long projectId;
	private String projectName;
	private Long productId;
	private String productName;
	private String productVersion;
	private TYPE type;
	private STATUS status;
	private Date startDate;
	private Date endDate;
	private Date updateDate;
	private String createdBy;
	private String updatedBy;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductVersion() {
		return productVersion;
	}

	public void setProductVersion(String productVersion) {
		this.productVersion = productVersion;
	}

	public TYPE getType() {
		return type;
	}

	@JsonValue
	public void setType(TYPE type) {
		this.type = type;
	}

	public STATUS getStatus() {
		return status;
	}

	@JsonValue
	public void setStatus(STATUS status) {
		this.status = status;
	}

	@JsonSerialize(using = DateSerializer.class)
	public Date getStartDate() {
		return startDate;
	}

	@JsonSerialize(using = DateSerializer.class)
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@JsonSerialize(using = DateSerializer.class)
	public Date getEndDate() {
		return endDate;
	}

	@JsonSerialize(using = DateSerializer.class)
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@JsonSerialize(using = DateSerializer.class)
	public Date getUpdateDate() {
		return updateDate;
	}

	@JsonSerialize(using = DateSerializer.class)
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
}
