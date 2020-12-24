package com.aste.lsme.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.UserDetailsDaoInterface;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.service.UserDetailsServiceInterface;
import com.aste.lsme.webservicesDtos.UserDTO;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsServiceInterface {
	 
	@Autowired
	UserDetailsDaoInterface userdetails;
	
	
	public UserDetail loadUserByUsername(String username)
	{
		 return userdetails.loadUserByUsername(username);
	}
	
	public UserDetail saveUser(UserDetail user) throws Exception
	{
		return userdetails.saveUser(user);
	}
	
	public List<UserDetail> getallusers()
	{
		return userdetails.getallusers();
	}
	public UserDetail findUser(Long id)
	{
		return userdetails.findUser(id);
	}
	public String removeUser(Long id)
	{
		return userdetails.removeUser(id);
		
	}
	public UserDetail updateUser(UserDetail user)
	{
		return userdetails.updateUser(user);
	}
	public UserDetail findUserByUsername(String Uname)
	  {
		  return userdetails.findUserByUsername(Uname);
	  }

	@Override
	public List<UserDetail> getAdminUsers() {
		return userdetails.getAdminUsers();
	}

	@Override
	public List<UserDetail> getNotAdminUsers() {
		return userdetails.getNotAdminUsers();
	}

	@Override
	public String getRoleOnUsername(String username) {
		return userdetails.getRoleOnUsername(username);
	}

	@Override
	public List<UserDTO> getUsersOnRole(String role,String workspace) {
		// TODO Auto-generated method stub
		return userdetails.getUsersOnRole(role,workspace);
	}

	@Override
	public void updatedeviceToken(String userName, String deviceToken) {
		userdetails.updatedeviceToken(userName,deviceToken);
		
	}

	@Override
	public List<String> getDeviceToken(List<Long> technicianIds) {
		return userdetails.getDeviceToken(technicianIds);
	}

	@Override
	public List<UserDTO> getDeviceTokenOnRole(String role, String w) {
		return userdetails.getDeviceTokenOnRole(role,w);
	}

	@Override
	public List<UserDTO> getAttendentsOnFrId(String frId) {
		return userdetails.getAttendentsOnFrId(frId);
	}

	@Override
	public String fetch2FaCodeOnUsername(String username) {
		// TODO Auto-generated method stub
		return userdetails.fetch2FaCodeOnUsername(username);
	}

	@Override
	public void updateCodeAndExpirationTime(String code, LocalDateTime expirationTime, String username) {
		 userdetails.updateCodeAndExpirationTime(code, expirationTime, username);
	}

	@Override
	public String getEmailOnUsername(String username) {
		// TODO Auto-generated method stub
		return userdetails.getEmailOnUsername(username);
	}

	@Override
	public boolean checkIf2FaEnabled(String username) {
		// TODO Auto-generated method stub
		return userdetails.checkIf2FaEnabled(username);
	}

	@Override
	public boolean logout(String deviceToken) {
		return userdetails.logout(deviceToken);
	}


	@Override
	public UserDTO getDeviceTokenOnUsername(String username) {
		// TODO Auto-generated method stub
		return userdetails.getDeviceTokenOnUsername(username);
	}

	@Override
	public UserDTO getUserOnUsername(String username) {
		// TODO Auto-generated method stub
		return userdetails.getUserOnUsername(username);
	}
	
	@Override
	public List<UserDTO> getUsersOnSearch(String w,String search) {
		return userdetails.getUsersOnSearch(w,search);
	}


}
