/*package com.aste.lsme.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user_access")
public class UserAccess {

	@Id
	@Column(name="USER_ACCESS_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long User_Access_Id;
	
	@Column(name="CONFIRM_FLAG",columnDefinition="BIT", length=1)
	Boolean Confirm_Flag;
	
	@Column(name="STA_CD")
	String Sta_Cd;
	
	@Column(name="LST_LOGIN")
	String Lst_Login;
	
	@Column(name="PWD_DTTM")
	String Pwd_Dttm;
	
	@Column(name="REM")
	String Rem;
	
	@Column(name="CRE_BY")
	Date Cre_By;
	
	@Column(name="MOD_BY")
	Date Mod_By;
	
	@Column(name="MOD_DTTM")
	String Mod_Dttm;
	
	@Column(name="USER_SK")
	Integer User_Sk;
	
	@Column(name="LST_LOGIN_DTTM")
	String Lst_Login_Dttm;
	
	@JsonIgnore
	@OneToOne(mappedBy="userAccesses")
	UserDetail userDetail;
	
	@JoinColumn(name="USER_GRPID")
	@ManyToOne(fetch=FetchType.EAGER)
	UserGroup userGroup;

	@PreRemove
	public void preRemoveOperation() {
		userDetail.setUserAccesses(null);
		
	}
	 
	public Long getUser_Access_Id() {
		return User_Access_Id;
	}

	public void setUser_Access_Id(Long user_Access_Id) {
		User_Access_Id = user_Access_Id;
	}

	public Boolean getConfirm_Flag() {
		return Confirm_Flag;
	}

	public void setConfirm_Flag(Boolean confirm_Flag) {
		Confirm_Flag = confirm_Flag;
	}

	public String getSta_Cd() {
		return Sta_Cd;
	}

	public void setSta_Cd(String sta_Cd) {
		Sta_Cd = sta_Cd;
	}

	public String getLst_Login() {
		return Lst_Login;
	}

	public void setLst_Login(String lst_Login) {
		Lst_Login = lst_Login;
	}

	public String getPwd_Dttm() {
		return Pwd_Dttm;
	}

	public void setPwd_Dttm(String pwd_Dttm) {
		Pwd_Dttm = pwd_Dttm;
	}

	public String getRem() {
		return Rem;
	}

	public void setRem(String rem) {
		Rem = rem;
	}

	public Date getCre_By() {
		return Cre_By;
	}

	public void setCre_By(Date cre_By) {
		Cre_By = cre_By;
	}

	public Date getMod_By() {
		return Mod_By;
	}

	public void setMod_By(Date mod_By) {
		Mod_By = mod_By;
	}

	public String getMod_Dttm() {
		return Mod_Dttm;
	}

	public void setMod_Dttm(String mod_Dttm) {
		Mod_Dttm = mod_Dttm;
	}

	public Integer getUser_Sk() {
		return User_Sk;
	}

	public void setUser_Sk(Integer user_Sk) {
		User_Sk = user_Sk;
	}

	public String getLst_Login_Dttm() {
		return Lst_Login_Dttm;
	}

	public void setLst_Login_Dttm(String lst_Login_Dttm) {
		Lst_Login_Dttm = lst_Login_Dttm;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}
	
}
*/