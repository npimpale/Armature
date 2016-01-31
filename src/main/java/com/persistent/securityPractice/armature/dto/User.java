package com.persistent.securityPractice.armature.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

public class User implements Serializable{
	private long id;
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private String organization;
	private String mobile;
	private String deskPhone;
	private String title;
	private String comments;
	private String hashedPassword;
	private String salt;
	private boolean isFirstLogin;
	private boolean isDeleted;
	private Date createDate;
	private Date updateDate;
	private List<Project> projectList;

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDeskPhone() {
		return deskPhone;
	}

	public void setDeskPhone(String deskPhone) {
		this.deskPhone = deskPhone;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@JsonSerialize(using = DateSerializer.class)
	public Date getCreateDate() {
		return createDate;
	}

	@JsonSerialize(using = DateSerializer.class)
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@JsonSerialize(using = DateSerializer.class)
	public Date getUpdateDate() {
		return updateDate;
	}

	@JsonSerialize(using = DateSerializer.class)
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public boolean getIsFirstLogin() {
		return isFirstLogin;
	}

	public void setIsFirstLogin(boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}

	public List<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}

	@Override
	public int hashCode() {
		int salt = 93;
		return salt
				* (this.getFirstName().length() + this.getLastName().length() + this
						.getUserName().length());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (!(obj != null && obj instanceof User)) {
			return false;
		}

		User u = (User) obj;

		return this.getFirstName().equals(u.getFirstName())
				&& this.getLastName().equals(u.getLastName())
				&& this.getUserName().equals(u.getUserName())
				&& this.getHashedPassword().equals(u.getHashedPassword())
				&& this.getEmail().equals(u.getEmail());
	}

	@Override
	public String toString() {
		return "User: [ Id: " + this.getId() + ", UserName: "
				+ this.getUserName() + ", FirstName: " + this.getFirstName()
				+ ", LastName: " + this.getLastName() + " ]";
	}

}
