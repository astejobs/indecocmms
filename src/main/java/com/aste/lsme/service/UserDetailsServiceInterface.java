package com.aste.lsme.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.webservicesDtos.UserDTO;

public interface UserDetailsServiceInterface {
	
	public UserDetail loadUserByUsername(String username);
	public UserDetail saveUser(UserDetail user) throws Exception;
	public List<UserDetail> getallusers();
	public UserDetail findUser(Long id);
	public String removeUser(Long id);
	public UserDetail updateUser(UserDetail user);
	public UserDetail findUserByUsername(String Uname);
	public List<UserDetail> getAdminUsers();
	public List<UserDetail> getNotAdminUsers();
	String getRoleOnUsername(String username);
	List<UserDTO> getUsersOnRole(String role, String workspace);
	public void updatedeviceToken(String string, String deviceToken);
	public List<String> getDeviceToken(List<Long> technicianIds);
	public List<UserDTO> getDeviceTokenOnRole(String roleManagingAgent, String w);
	public List<UserDTO> getAttendentsOnFrId(String frId);
	String fetch2FaCodeOnUsername(String username); 
	void updateCodeAndExpirationTime(String code, LocalDateTime expirationTime, String username);
	String getEmailOnUsername(String username);
	boolean checkIf2FaEnabled(String username);
	public boolean logout(String username);
	public UserDTO getDeviceTokenOnUsername(String username);
	public UserDTO getUserOnUsername(String username);
	public List<UserDTO> getUsersOnSearch(String w, String search);

}
