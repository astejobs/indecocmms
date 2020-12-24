package com.aste.lsme.daoImpl;

//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.UserDetailsDaoInterface;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.FaultReport;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.domain.UserGroup;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.webservicesDtos.FaultReportDto;
import com.aste.lsme.webservicesDtos.UserDTO;
import com.google.cloud.storage.Acl.User;

@Repository
public class UserDetailsDaoImpl implements UserDetailsDaoInterface {
	
	@PersistenceContext
	EntityManager entityManager;

	public UserDetail loadUserByUsername(String username)
	{
		Query query = entityManager.createQuery("Select u from UserDetail u where u.username=:username").
				setParameter("username", username);
		try
		{
		return (UserDetail) query.getSingleResult();
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public UserDetail loadUserByEmail(String email)
	{
		Query query = entityManager.createQuery("Select u from UserDetail u where u.eMail=:email").
				setParameter("email", email);
		try
		{
		return (UserDetail) query.getSingleResult();
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	

	@Transactional
	public UserDetail saveUser(UserDetail user) throws Exception
	{
		
		
			entityManager.persist(user);
			return user;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<UserDetail> getallusers()
	{
		Query query = entityManager.createQuery("Select u from UserDetail u ");
	    return query.getResultList();
	}
	
	public UserDetail findUser(Long id)
	{
		 return entityManager.find(UserDetail.class, id);
	}
	
	public String removeUser(Long id)
	{
		try
		{
		entityManager.remove(entityManager.find(UserDetail.class, id));
		return "success";
		}
		catch(Exception ex)
		{
			return "fail";
		}
	}
		
	public UserDetail updateUser(UserDetail user)
	{
		try
		{
			
			return entityManager.merge(user);
		}
		catch(Exception ex)
		{
			return null;
		}
		
	}
	
	 @Transactional
	   public UserDetail findUserByUsername(String Uname)
	   {
		   Query query = entityManager.createQuery("Select u from UserDetail u where u.username=:Uname")
				   .setParameter("Uname", Uname);
		   
		   try
		   {
			  return (UserDetail) query.getSingleResult();
		   }
		   catch(Exception ex)
		   {
			   return null;
		   }
	   }
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserDetail> getAdminUsers() {
		
		 Query query = entityManager.createQuery("Select u from UserDetail u where u.usergroup.id = :id ").setParameter("id",1L);
		 return query.getResultList();
	 }
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserDetail> getNotAdminUsers() {
		
		 Query query = entityManager.createQuery("Select u from UserDetail u where u.usergroup.id != :id ").setParameter("id",1L);
		 return query.getResultList();
	 } 
	
	@Override
	public String getRoleOnUsername(String username){
		 Query query = entityManager.createQuery("Select u.usergroup.role.name from UserDetail u where u.username = :username ")
				 													.setParameter("username",username);
		 try {
			return (String) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<UserDTO> getUsersOnRole(String role,String workspace){
		Query query = entityManager.createQuery("Select new com.aste.lsme.webservicesDtos.UserDTO(u.id,u.username,u.firstName,u.lastName) from UserDetail  u JOIN u.usergroup ug JOIN ug.workspaces w where ug.role.name = :role and w.workspaceId = :w")
															.setParameter("role", role).setParameter("w",workspace);
		return query.getResultList();
	}

	@Override
	public void updatedeviceToken(String userName, String deviceToken) {
		Query query= entityManager.createQuery("Update UserDetail u set u.deviceToken = :deviceToken where u.username = :userName")
				.setParameter("deviceToken", deviceToken)
				.setParameter("userName", userName);
		query.executeUpdate();
		
	}

	@Override
	public List<String> getDeviceToken(List<Long> technicianIds) {
		Query query=entityManager.
				createQuery("Select f.deviceToken from UserDetail f where f.id IN :technicianIds")
								.setParameter("technicianIds", technicianIds);
		
		return query.getResultList();

	}

	@Override
	public List<UserDTO> getDeviceTokenOnRole(String role, String w) {
	
		Query query=entityManager.
				createQuery("Select new com.aste.lsme.webservicesDtos.UserDTO(f.id,f.deviceToken) from UserDetail f JOIN f.usergroup ug JOIN ug.workspaces w  where ug.role.name=:role and w.workspaceId=:workspace")
								.setParameter("role", role).setParameter("workspace", w);
		
		return query.getResultList();
	}

	@Override
	public List<UserDTO> getAttendentsOnFrId(String frId) {
		System.out.println(frId+"Kkkkkkkkkkkkkkkkkkkkkk");
		Query query=entityManager. 

				createQuery("Select new com.aste.lsme.webservicesDtos.UserDTO(i.id,i.username,i.firstName,i.lastName,i.deviceToken) from FaultReport u JOIN u.attendedBy i where u.frId= :frId").setParameter("frId", frId);

		return query.getResultList(); 
	}
	
	
	
	@Override
	public String fetch2FaCodeOnUsername(String username){
		Query query=entityManager.createQuery("Select u.twoFaCode from UserDetail u where u.username = :username")
										.setParameter("username",username);
		try {
			return  (String) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	} 
	
	@Override
	public void updateCodeAndExpirationTime(String code,LocalDateTime expirationTime,String username){
		Query query= entityManager.createQuery("Update UserDetail u set u.twoFaCode = :code,u.twoFaExpireTime= :expirationTime where u.username = :username")
				.setParameter("code", code)
				.setParameter("expirationTime", expirationTime)
				.setParameter("username", username);
		query.executeUpdate();
	}
	
	@Override
	public String getEmailOnUsername(String username){
		Query query=entityManager.createQuery("Select u.email from UserDetail u where u.username = :username")
				.setParameter("username",username);
		try {
			return (String) query.getSingleResult();
		} catch (Exception e) {
		e.printStackTrace();
			return null;
		} 
	}
	@Override
	public boolean checkIf2FaEnabled(String username){
		Query query=entityManager.createQuery("Select u.isTwoFaEnabled from UserDetail u where u.username = :username")
				.setParameter("username",username);
		return (boolean) query.getSingleResult();
	}

	@Override
	public boolean logout(String deviceToken) {
	
		try{
		Query q=entityManager.createQuery("Update  UserDetail u set u.deviceToken=null where u.deviceToken=:deviceToken").setParameter("deviceToken", deviceToken);
	
		q.executeUpdate();
		return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;}
		}

	@Override
	public List<UserDTO> getUsersOnSearch(String w,String search) {
		  CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		  CriteriaQuery<UserDTO> criteriaQuery = cb.createQuery(UserDTO.class);
		  Root<UserDetail> fromFr = criteriaQuery.from(UserDetail.class);
		  Join<UserDetail, UserGroup> join1 = fromFr.join("usergroup");
		  Join<UserGroup, Workspace> join2 = join1.join("workspaces");		  
		  if(!search.isEmpty()){
		  List<Predicate> predicateList = getPredicatesLike(cb, criteriaQuery,
		  fromFr,search);	  
     	  criteriaQuery.where(cb.and(cb.or(predicateList.toArray(new Predicate[0])),cb.equal(join2.get("workspaceId"), w),cb.equal(join1.get("role").get("name"),Constants.ROLE_TECHNICIAN)));
	       criteriaQuery.select(cb.construct(UserDTO.class, fromFr.get("id"),fromFr.get("username"),fromFr.get("firstName"),
		  fromFr.get("lastName")));
		  return entityManager.createQuery(criteriaQuery).getResultList();
		  }else{
			  return null;
		  }
	}	
	
	 private List<Predicate> getPredicatesLike(CriteriaBuilder cb, CriteriaQuery cq,
				Root<UserDetail> fromFr,String search){

		    	List<Predicate> predicates = new ArrayList<Predicate>();
				predicates.add(cb.like(fromFr.<String>get("username"),"%"+search+"%"));
				predicates.add(cb.like(fromFr.<String>get("firstName"),"%"+search+"%"));				
				predicates.add(cb.like(fromFr.get("lastName"),"%"+search+"%"));
			
				return predicates;
		}
	@Override
	public UserDTO getDeviceTokenOnUsername(String username) {

		Query query=entityManager.createQuery("Select new com.aste.lsme.webservicesDtos.UserDTO(u.id,u.deviceToken) from UserDetail u where u.username = :username")
				.setParameter("username", username);
		
			 
			  return (UserDTO) query.getSingleResult();
		    
		
	}

	@Override
	public UserDTO getUserOnUsername(String username) {

		Query query=entityManager. 

				createQuery("Select new com.aste.lsme.webservicesDtos.UserDTO(u.id,u.username,u.firstName,u.lastName) from UserDetail u where u.username= :username")
				.setParameter("username",username);

		return (UserDTO) query.getSingleResult(); 
	}
}
 