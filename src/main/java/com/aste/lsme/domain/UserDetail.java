package com.aste.lsme.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "usr_dtl")
public class UserDetail {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private Long id;
	
	@Column(name = "USERNAME", length = 50,unique= true)
	@NotNull
	@NotEmpty(message = "User Name can't be empty")
	private String username;

	@Column(name = "PASSWORD")
	@NotEmpty(message = "Password can't be empty")
	@NotNull
	private String password;

	@Column(name = "FIRST_NAME", length = 30)
	@NotEmpty(message = "First Name can't be empty")
	@NotNull
	private String firstName;

	@Column(name = "LAST_NAME", length = 30)
	@NotEmpty(message = "Last can't be empty")
	@NotNull
	private String lastName;

	@Column(name = "DEPARTMENT", length = 30)
	@NotEmpty(message = "Department can't be empty")
	@NotNull
	private String department;
	@Column(name = "DESIGNATION", length = 30)
	@NotEmpty(message = "Designation can't be empty")
	@NotNull
	private String designation;

	@Column(name = "E_MAIL", length = 50, unique=true)
	@NotEmpty(message = "email is required")
	@NotNull
	private String email;
	
	
	@Column(name = "COMPANY", length = 50)
	private String company;

	@Column(name = "PHONE_NO", length = 20)
	@NotEmpty(message = "phone No is required")
	@Size(min = 8, max = 12, message = "Phone No. is atleast of 10 digits")
	@Digits(fraction = 0, integer = 12, message = "Phone No. is atleast of 10 digits")
	@NotNull
	private String phoneNo;

	@Column(name = "USER_TYPE_FLAG", length = 20)
	@NotNull
	private String userTypeFlag;
	
	@JsonIgnore
	@ManyToOne
	@NotNull(message="User Group can't be empty")
	private UserGroup usergroup;

	// @NotNull
	@Column(name = "userimage", length = 150)
	private String userImage;
	
	
	@Column(name="deviceToken")
	private String deviceToken;

	
	private boolean isTwoFaEnabled;
	
	private String twoFaCode;
	
	private LocalDateTime twoFaExpireTime;
	
	private String twoFaDefaultType;
	

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getUserTypeFlag() {
		return userTypeFlag;
	}

	public void setUserTypeFlag(String userTypeFlag) {
		this.userTypeFlag = userTypeFlag;
	}


	public UserGroup getUsergroup() {
		return usergroup;
	}

	public void setUsergroup(UserGroup usergroup) {
		this.usergroup = usergroup;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}	

	public boolean isTwoFaEnabled() {
		return isTwoFaEnabled;
	}

	public void setTwoFaEnabled(boolean isTwoFaEnabled) {
		this.isTwoFaEnabled = isTwoFaEnabled;
	}

	public String getTwoFaCode() {
		return twoFaCode;
	}

	public void setTwoFaCode(String twoFaCode) {
		this.twoFaCode = twoFaCode;
	}

	public LocalDateTime getTwoFaExpireTime() {
		return twoFaExpireTime;
	}

	public void setTwoFaExpireTime(LocalDateTime twoFaExpireTime) {
		this.twoFaExpireTime = twoFaExpireTime;
	}

	public String getTwoFaDefaultType() {
		return twoFaDefaultType;
	}

	public void setTwoFaDefaultType(String twoFaDefaultType) {
		this.twoFaDefaultType = twoFaDefaultType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDetail other = (UserDetail) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		
		return firstName +" "+  lastName ;
	}

	
	

}
