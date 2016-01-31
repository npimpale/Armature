package com.persistent.securityPractice.armature.dto;

import java.util.Date;

public class Contact {
	private long id;
	private long project_id;
	private String name;
	private String organizationName;
	private String deskPhone;
	private String mobile;
	private String email;
	private String designation;
	private String comments;
	private String createdBy;
	private String updatedBy;
	private Date createDate;
	private Date updateDate;

	public long getProject_id() {
		return project_id;
	}

	public void setProject_id(long project_id) {
		this.project_id = project_id;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getDeskPhone() {
		return deskPhone;
	}

	public void setDeskPhone(String deskPhone) {
		this.deskPhone = deskPhone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int salt = 53;
		int code = (int) this.getId() + Integer.valueOf(this.getMobile());
		return salt * code;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (!(obj != null && obj instanceof Contact)) {
			return false;
		}

		Contact c = (Contact) obj;

		return this.getEmail().equals(c.getEmail())
				&& this.getName().equals(c.getName())
				&& this.getOrganizationName().equals(c.getOrganizationName());
	}

	@Override
	public String toString() {
		return "Contact: [Id: " + this.getId() + ", Name: " + this.getName()
				+ ", Email: " + this.getEmail() + ", Orgnization: "
				+ this.getOrganizationName() + ", Designation: "
				+ this.getDesignation() + ", Mobile: " + this.getMobile()
				+ ", DeskPhone: " + this.getDeskPhone() + ", Comments: "
				+ this.getComments() + "]";
	}
}
