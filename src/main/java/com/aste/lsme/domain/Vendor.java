package com.aste.lsme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Vendor {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	@Pattern(regexp = "^$|[\\s\\S]*[a-z|A-Z|0-9]+[\\s\\S]*", message = "spaces_not_allowed")
	@Size(max = 30,message="Size must be less than 30")
	@NotEmpty(message = "Enter Vendor Name")
	@Column(name = "Vendor_Name")
	private String vendorName;
	
	
	@Pattern(regexp = "^$|[\\s\\S]*[a-z|A-Z|0-9]+[\\s\\S]*", message = "spaces_not_allowed")
	@Size(max = 200)
	@NotEmpty(message = "Enter Vendor Description")
	@Column(name="DESCRIPTION")
	private String description;
	
	@Pattern(regexp = "^\\d{8,12}$", message = "Please Enter the 10/12 digits correctly")
	@Size(max=10,min=10)
	@Column(name="PHONE_NO")
	@NotEmpty(message = "Enter Vendor Phone Number")
	private String phoneNumber;
	
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "please enter the valid symbols only!")
	@Column(name="EMAIL")
	private String email;
	
	@Pattern(regexp = "^\\d{8,12}$", message = "Please Enter the 10/12 digits correctly")
	@Column(name="FAX_NUMBER")
	private String faxNumber;
	
	@Pattern(regexp = "^$|[\\s\\S]*[a-z|A-Z|0-9]+[\\s\\S]*", message = "spaces_not_allowed")
	@Size(max = 200,message="Size must be less than 200")
	@Column(name="ADDRESS")
	private String address;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
