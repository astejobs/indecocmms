package com.aste.lsme.domain;

public class PasswordDTO {
	
	private String userName;
	
	private String oldpassword;
	
	private String newpassword;
	
	private String confirmNewpassowrd;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getConfirmNewpassowrd() {
		return confirmNewpassowrd;
	}

	public void setConfirmNewpassowrd(String confirmNewpassowrd) {
		this.confirmNewpassowrd = confirmNewpassowrd;
	}
	
	
	

}
