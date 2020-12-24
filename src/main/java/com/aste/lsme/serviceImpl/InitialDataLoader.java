package com.aste.lsme.serviceImpl;



import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.aste.lsme.dao.GroupDetailsDaoInterface;
import com.aste.lsme.dao.UserDetailsDaoInterface;
import com.aste.lsme.dao.WorkspaceDao;
import com.aste.lsme.domain.Building;
import com.aste.lsme.domain.Department;
import com.aste.lsme.domain.Division;
import com.aste.lsme.domain.FaultCategory;
import com.aste.lsme.domain.Location;
import com.aste.lsme.domain.MaintainenceGroup;
import com.aste.lsme.domain.Priorty;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.domain.UserGroup;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.BuildingService;
import com.aste.lsme.service.CostCenterService;
import com.aste.lsme.service.DepartmentService;
import com.aste.lsme.service.DivisionService;
import com.aste.lsme.service.FaultCategoryService;
import com.aste.lsme.service.LocationService;
import com.aste.lsme.service.MaintainenceGroupService;
import com.aste.lsme.service.PriortyService;


@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		
	}
/*
	@Autowired
	UserDetailsDaoInterface userdao;
	
	@Autowired
	WorkspaceDao worspacedao;
	
	@Autowired 
	GroupDetailsDaoInterface groupdao;

	@Autowired
	BuildingService buildingService;
	 
	@Autowired
	LocationService locationService;
	
	@Autowired
	DepartmentService departmentService;
	
	@Autowired
	CostCenterService costCenterService;

	@Autowired
	DivisionService divisionService;
	
	@Autowired
	FaultCategoryService faultCategoryService;
	
	@Autowired
	PriortyService priortyService;
	
	@Autowired
	MaintainenceGroupService maintainenceGroupService;
	
	boolean alreadySetup = false;


	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		if (alreadySetup)
			return;

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		 UserDetail u = new UserDetail();
		 u.setUsername("admin");
		 u.setFirstName("admin");
		 u.setLastName("admin");
		 u.setPassword(passwordEncoder.encode("admin"));
		 u.setDepartment("admin");
		 u.setEmail("admin@admin.com");
		 u.setPhoneNo("1234567890");
		 u.setDesignation("admin");
		 u.setUserTypeFlag("Active");
		 Workspace w = new Workspace();
		 w.setWorkspaceId("lsme-DEMO-112016-001");
		 w.setContractor("demo");
		 w.setBldngOwnerPayAmt("45564");
		 w.setBuildingDescription("demo");
		 w.setDescription("demo");
		 w.setOwner("demo");
		 
		 UserGroup ug = new UserGroup();
		 ug.setUserGroupName("Admin");
		 ug.setGroupDesc("admins");
		 ug.setUserdetails(Arrays.asList(u));
		 ug.setWorkspaces(Arrays.asList(w));
		 ug.setModuleDetails(groupdao.getallmodules());
		 u.setUsergroup(ug);
		 
		 
		
		
		createUserIfNotFound("Admin",u,ug,w);
		alreadySetup = true;
		

	}

	
	private UserDetail createUserIfNotFound(String name,UserDetail u, UserGroup ug,Workspace w) {
		
		
		 if(userdao.findUserByUsername(name)==null && groupdao.findGroupbyName(ug.getUserGroupName())==null)
		 {
			 worspacedao.persist(w);
			 
			  try {
				groupdao.saveGroup(ug);
				userdao.saveUser(u);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      
		 }
		 if(buildingService.getAll().isEmpty()){
			 for(int i=0; i<20 ; i++){
						 
						 Building building = new Building();
						 building.setName("Building"+i);
						 building.setDescription("buildinggggggggg");
						 building.setWorkspace(w);
						 
						 Location location = new Location();
						 location.setName("Location"+i);
						 location.setDescription("loccccccccc");
						 location.setBuilding(building);
						 location.setWorkspace(w);
						 
						 
						 Department department = new Department();
						 department.setName("department"+i);
						 department.setDescription("deppppppppp");
						 department.setWorkspace(w);
						 
						 
						 Division division = new Division();
						 division.setName("division"+i);
						 division.setDescription("dicivivv");
						 division.setWorkspace(w);
						 
						 
						 Priorty priorty = new Priorty();
						 priorty.setName("Priority"+i);
						 priorty.setDescription("[pppppppppppp");
						 priorty.setWorkspace(w);
						 
						 
						 FaultCategory faultCategory = new FaultCategory();
						 faultCategory.setName("faultCategory"+i);
						 faultCategory.setDescription("fffffffff");
						 faultCategory.setPriorty(priorty);
						 faultCategory.setWorkspace(w);
						 
						 
						 MaintainenceGroup maintainenceGroup = new MaintainenceGroup();
						 maintainenceGroup.setName("maintGrp"+i);
						 maintainenceGroup.setDescription("mmmmmmmmm");
						 maintainenceGroup.setStatus("Active");
						 maintainenceGroup.setWorkspace(w);
						 
						 buildingService.persist(building, w);
						 locationService.persist(location, w);
						 departmentService.persist(department, w);
						 divisionService.persist(division, w);
						 priortyService.persist(priorty, w);
						 faultCategoryService.addFaultCategory(faultCategory, w);
						 maintainenceGroupService.persist(maintainenceGroup, w);
					 }
		 }
		 return u;	  
		
		}
		
	


	*/
}
