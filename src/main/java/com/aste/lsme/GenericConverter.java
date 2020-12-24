package com.aste.lsme;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.aste.lsme.converter.StringToEntityConverter;
import com.aste.lsme.domain.ACMV;
import com.aste.lsme.domain.AccessSite;
import com.aste.lsme.domain.AssetSubtype;
import com.aste.lsme.domain.AssetType;
import com.aste.lsme.domain.Building;
import com.aste.lsme.domain.ChecklistHeader;
import com.aste.lsme.domain.ChecklistProperty;
import com.aste.lsme.domain.ChecklistPropertyTitle;
import com.aste.lsme.domain.ChecklistSearch;
import com.aste.lsme.domain.CostCenter;
import com.aste.lsme.domain.CustDetail;
import com.aste.lsme.domain.Department;
import com.aste.lsme.domain.Division;
import com.aste.lsme.domain.DynamicFieldsOfEquiment;
import com.aste.lsme.domain.Electrical;
import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.EquipmentBaseline;
import com.aste.lsme.domain.EquipmentFieldData;
import com.aste.lsme.domain.EquipmentFieldDes;
import com.aste.lsme.domain.EquipmentServiceDates;
import com.aste.lsme.domain.EquipmentTaskReading;
import com.aste.lsme.domain.ExceptionDate;
import com.aste.lsme.domain.FaultCategory;
import com.aste.lsme.domain.FaultReport;
import com.aste.lsme.domain.FaultReportSearch;
import com.aste.lsme.domain.Fire;
import com.aste.lsme.domain.Group;
import com.aste.lsme.domain.GroupPriviledges;
import com.aste.lsme.domain.LabourRate;
import com.aste.lsme.domain.LabourRateSearchCriteria;
import com.aste.lsme.domain.Location;
import com.aste.lsme.domain.MaintainenceGroup;
import com.aste.lsme.domain.Manufacturer;
import com.aste.lsme.domain.Mechanical;
import com.aste.lsme.domain.Meter;
import com.aste.lsme.domain.ModuleDetail;
import com.aste.lsme.domain.OtherRate;
import com.aste.lsme.domain.Priority;
import com.aste.lsme.domain.Priorty;
import com.aste.lsme.domain.QrCodeGenerator;
import com.aste.lsme.domain.Quotation;
import com.aste.lsme.domain.QuotationKeygen;
import com.aste.lsme.domain.Quotations;
import com.aste.lsme.domain.Role;
import com.aste.lsme.domain.SOR;
import com.aste.lsme.domain.Schedule;
import com.aste.lsme.domain.ScheduledEquipment;
import com.aste.lsme.domain.SearchPMSchedule;
import com.aste.lsme.domain.SearchPMTask;
import com.aste.lsme.domain.SitePriviledge;
import com.aste.lsme.domain.StartScheduleDate;
import com.aste.lsme.domain.StoreKeeper;
import com.aste.lsme.domain.Task;
import com.aste.lsme.domain.TaskChecklist;
import com.aste.lsme.domain.Team;
import com.aste.lsme.domain.Technician;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.domain.UserGroup;
import com.aste.lsme.domain.UtilityReading;
import com.aste.lsme.domain.Warehouse;
import com.aste.lsme.domain.Workspace;

@Configuration
public class GenericConverter extends WebMvcConfigurerAdapter{

	@PersistenceContext
	private EntityManager em;	
	
	@Override
	public void addFormatters(FormatterRegistry registry) {		
		
		   StringToEntityConverter  accessSiteConverter = new StringToEntityConverter();
		   accessSiteConverter.setClazz(AccessSite.class);
		   accessSiteConverter.setEm(em);
	       registry.addConverter(accessSiteConverter);
			
	       StringToEntityConverter  acmvConverter = new StringToEntityConverter();
	       acmvConverter.setClazz(ACMV.class);
	       acmvConverter.setEm(em);
	       registry.addConverter(acmvConverter);
		
		 
	       StringToEntityConverter  buildingConverter = new StringToEntityConverter();
	       buildingConverter.setClazz(Building.class);
	       buildingConverter.setEm(em);
	       registry.addConverter(buildingConverter);
		  
		
	       StringToEntityConverter  checklistHeaderConverter = new StringToEntityConverter();
	       checklistHeaderConverter.setClazz(ChecklistHeader.class);
	       checklistHeaderConverter.setEm(em);
	       registry.addConverter(checklistHeaderConverter);
		
	       StringToEntityConverter  checklistPropertyConverter = new StringToEntityConverter();
	       checklistPropertyConverter.setClazz(ChecklistProperty.class);
	       checklistPropertyConverter.setEm(em);
	       registry.addConverter(checklistPropertyConverter);
		
	       StringToEntityConverter  checklistPropertyTitleConverter = new StringToEntityConverter();
	       checklistPropertyTitleConverter.setClazz(ChecklistPropertyTitle.class);
	       checklistPropertyTitleConverter.setEm(em);
	       registry.addConverter(checklistPropertyTitleConverter);
	 
	       
	       StringToEntityConverter  checklistSearchConverter = new StringToEntityConverter();
	       checklistSearchConverter.setClazz(ChecklistSearch.class);
	       checklistSearchConverter.setEm(em);
	       registry.addConverter(checklistSearchConverter);
		 
	       StringToEntityConverter  costCenterConverter = new StringToEntityConverter();
	       costCenterConverter.setClazz(CostCenter.class);
	       costCenterConverter.setEm(em);
	       registry.addConverter(costCenterConverter);
		 
	       StringToEntityConverter  custDetailConverter = new StringToEntityConverter();
	       custDetailConverter.setClazz(CustDetail.class);
	       custDetailConverter.setEm(em);
	       registry.addConverter(custDetailConverter);
		 
	       StringToEntityConverter  departmentConverter = new StringToEntityConverter();
	       departmentConverter.setClazz(Department.class);
	       departmentConverter.setEm(em);
	       registry.addConverter(departmentConverter);
 
	       StringToEntityConverter  divisionConverter = new StringToEntityConverter();
	       divisionConverter.setClazz(Division.class);
	       divisionConverter.setEm(em);
	       registry.addConverter(divisionConverter);  
	       
	       StringToEntityConverter  dynamicFieldsOfEquimentConverter = new StringToEntityConverter();
	       dynamicFieldsOfEquimentConverter.setClazz(DynamicFieldsOfEquiment.class);
	       dynamicFieldsOfEquimentConverter.setEm(em);
	       registry.addConverter(dynamicFieldsOfEquimentConverter);
	       
	       StringToEntityConverter  electricalConverter = new StringToEntityConverter();
	       electricalConverter.setClazz(Electrical.class);
	       electricalConverter.setEm(em);
	       registry.addConverter(electricalConverter);
	       
	       StringToEntityConverter  equipmentConverter = new StringToEntityConverter();
	       equipmentConverter.setClazz(Equipment.class);
	       equipmentConverter.setEm(em);
	       registry.addConverter(equipmentConverter);
	       
	       StringToEntityConverter  equipmentBaselineConverter = new StringToEntityConverter();
	       equipmentBaselineConverter.setClazz(EquipmentBaseline.class);
	       equipmentBaselineConverter.setEm(em);
	       registry.addConverter(equipmentBaselineConverter);
		 
	       StringToEntityConverter  equipmentFieldDataConverter = new StringToEntityConverter();
	       equipmentFieldDataConverter.setClazz(EquipmentFieldData.class);
	       equipmentFieldDataConverter.setEm(em);
	       registry.addConverter(equipmentFieldDataConverter);
	 
	       StringToEntityConverter  equipmentFieldDesConverter = new StringToEntityConverter();
	       equipmentFieldDesConverter.setClazz(EquipmentFieldDes.class);
	       equipmentFieldDesConverter.setEm(em);
	       registry.addConverter(equipmentFieldDesConverter);
	 
	       StringToEntityConverter  equipmentServiceDatesConverter = new StringToEntityConverter();
	       equipmentServiceDatesConverter.setClazz(EquipmentServiceDates.class);
	       equipmentServiceDatesConverter.setEm(em);
	       registry.addConverter(equipmentServiceDatesConverter);
		 
	       StringToEntityConverter  equipmentTaskReadingConverter = new StringToEntityConverter();
	       equipmentTaskReadingConverter.setClazz(EquipmentTaskReading.class);
	       equipmentTaskReadingConverter.setEm(em);
	       registry.addConverter(equipmentTaskReadingConverter);
		 
	       StringToEntityConverter  exceptionDateConverter = new StringToEntityConverter();
	       exceptionDateConverter.setClazz(ExceptionDate.class);
	       exceptionDateConverter.setEm(em);
	       registry.addConverter(exceptionDateConverter);
	       
	       StringToEntityConverter  faultCategoryConverter = new StringToEntityConverter();
	       faultCategoryConverter.setClazz(FaultCategory.class);
	       faultCategoryConverter.setEm(em);
	       registry.addConverter(faultCategoryConverter);
	 
	       StringToEntityConverter  faultReportConverter = new StringToEntityConverter();
	       faultReportConverter.setClazz(FaultReport.class);
	       faultReportConverter.setEm(em);
	       registry.addConverter(faultCategoryConverter);
		
	       StringToEntityConverter  faultReportSearchConverter = new StringToEntityConverter();
	       faultReportSearchConverter.setClazz(FaultReportSearch.class);
	       faultReportSearchConverter.setEm(em);
	       registry.addConverter(faultReportSearchConverter);
	 
		
	       StringToEntityConverter  fireConverter = new StringToEntityConverter();
	       fireConverter.setClazz(Fire.class);
	       fireConverter.setEm(em);
	       registry.addConverter(fireConverter);
	       
	       StringToEntityConverter  groupConverter = new StringToEntityConverter();
	       groupConverter.setClazz(Group.class);
	       groupConverter.setEm(em);
	       registry.addConverter(groupConverter);
	       
	       StringToEntityConverter  groupPriviledgesConverter = new StringToEntityConverter();
	       groupPriviledgesConverter.setClazz(GroupPriviledges.class);
	       groupPriviledgesConverter.setEm(em);
	       registry.addConverter(groupPriviledgesConverter);
	       
	       StringToEntityConverter  labourRateConverter = new StringToEntityConverter();
	       labourRateConverter.setClazz(LabourRate.class);
	       labourRateConverter.setEm(em);
	       registry.addConverter(labourRateConverter);
	       
	       StringToEntityConverter  labourRateSearchCriteriaConverter = new StringToEntityConverter();
	       labourRateSearchCriteriaConverter.setClazz(LabourRateSearchCriteria.class);
	       labourRateSearchCriteriaConverter.setEm(em);
	       registry.addConverter(labourRateSearchCriteriaConverter);
	
	       StringToEntityConverter  locationConverter = new StringToEntityConverter();
	       locationConverter.setClazz(Location.class);
	       locationConverter.setEm(em);
	       registry.addConverter(locationConverter);
	 
	       StringToEntityConverter  maintainenceGroupConverter = new StringToEntityConverter();
	       maintainenceGroupConverter.setClazz(MaintainenceGroup.class);
	       maintainenceGroupConverter.setEm(em);
	       registry.addConverter(maintainenceGroupConverter);
	
		
	       StringToEntityConverter  manufacturerConverter = new StringToEntityConverter();
	       manufacturerConverter.setClazz(Manufacturer.class);
	       manufacturerConverter.setEm(em);
	       registry.addConverter(manufacturerConverter);
	       
	       
	       StringToEntityConverter  mechanicalConverter = new StringToEntityConverter();
	       mechanicalConverter.setClazz(Mechanical.class);
	       mechanicalConverter.setEm(em);
	       registry.addConverter(mechanicalConverter);
	       
	       StringToEntityConverter  meterConverter = new StringToEntityConverter();
	       meterConverter.setClazz(Meter.class);
	       meterConverter.setEm(em);
	       registry.addConverter(meterConverter);
	
	       StringToEntityConverter  moduleDetailConverter = new StringToEntityConverter();
	       moduleDetailConverter.setClazz(ModuleDetail.class);
	       moduleDetailConverter.setEm(em);
	       registry.addConverter(moduleDetailConverter);
	 
	       
	       StringToEntityConverter  otherRateConverter = new StringToEntityConverter();
	       otherRateConverter.setClazz(OtherRate.class);
	       otherRateConverter.setEm(em);
	       registry.addConverter(otherRateConverter);
	
	       StringToEntityConverter  priorityConverter = new StringToEntityConverter();
	       priorityConverter.setClazz(Priority.class);
	       priorityConverter.setEm(em);
	       registry.addConverter(priorityConverter);
	       
	       StringToEntityConverter  priortyConverter = new StringToEntityConverter();
	       priortyConverter.setClazz(Priorty.class);
	       priortyConverter.setEm(em);
	       registry.addConverter(priortyConverter);
	
	       StringToEntityConverter  qrCodeGeneratorConverter = new StringToEntityConverter();
	       qrCodeGeneratorConverter.setClazz(QrCodeGenerator.class);
	       qrCodeGeneratorConverter.setEm(em);
	       registry.addConverter(qrCodeGeneratorConverter);
	       
	       StringToEntityConverter  quotationConverter = new StringToEntityConverter();
	       quotationConverter.setClazz(Quotation.class);
	       quotationConverter.setEm(em);
	       registry.addConverter(quotationConverter);
	       
	       StringToEntityConverter  quotationKeygenConverter = new StringToEntityConverter();
	       quotationKeygenConverter.setClazz(QuotationKeygen.class);
	       quotationKeygenConverter.setEm(em);
	       registry.addConverter(quotationKeygenConverter);
	
	       StringToEntityConverter  quotationsConverter = new StringToEntityConverter();
	       quotationsConverter.setClazz(Quotations.class);
	       quotationsConverter.setEm(em);
	       registry.addConverter(quotationsConverter);
	
	       StringToEntityConverter  roleConverter = new StringToEntityConverter();
	       roleConverter.setClazz(Role.class);
	       roleConverter.setEm(em);
	       registry.addConverter(roleConverter);
		
	       StringToEntityConverter  scheduleConverter = new StringToEntityConverter();
	       scheduleConverter.setClazz(Schedule.class);
	       scheduleConverter.setEm(em);
	       registry.addConverter(scheduleConverter);
	
	     /*  StringToEntityConverter  scheduledEquipmentConverter = new StringToEntityConverter();
	       scheduledEquipmentConverter.setClazz(ScheduledEquipment.class);
	       scheduledEquipmentConverter.setEm(em);
	       registry.addConverter(scheduledEquipmentConverter);*/
	       
	       StringToEntityConverter  searchPMScheduleConverter = new StringToEntityConverter();
	       searchPMScheduleConverter.setClazz(SearchPMSchedule.class);
	       searchPMScheduleConverter.setEm(em);
	       registry.addConverter(searchPMScheduleConverter);
	
	       StringToEntityConverter  searchPMTaskConverter = new StringToEntityConverter();
	       searchPMTaskConverter.setClazz(SearchPMTask.class);
	       searchPMTaskConverter.setEm(em);
	       registry.addConverter(searchPMTaskConverter);
	       
	       StringToEntityConverter  sitePriviledgeConverter = new StringToEntityConverter();
	       sitePriviledgeConverter.setClazz(SitePriviledge.class);
	       sitePriviledgeConverter.setEm(em);
	       registry.addConverter(sitePriviledgeConverter);
	
	       StringToEntityConverter  sORConverter = new StringToEntityConverter();
	       sORConverter.setClazz(SOR.class);
	       sORConverter.setEm(em);
	       registry.addConverter(sORConverter);
	       
	       StringToEntityConverter  startScheduleDateConverter = new StringToEntityConverter();
	       startScheduleDateConverter.setClazz(StartScheduleDate.class);
	       startScheduleDateConverter.setEm(em);
	       registry.addConverter(startScheduleDateConverter);
	       
	       StringToEntityConverter  storeKeeperConverter = new StringToEntityConverter();
	       storeKeeperConverter.setClazz(StoreKeeper.class);
	       storeKeeperConverter.setEm(em);
	       registry.addConverter(storeKeeperConverter);
	
	       StringToEntityConverter  taskConverter = new StringToEntityConverter();
	       taskConverter.setClazz(Task.class);
	       taskConverter.setEm(em);
	       registry.addConverter(taskConverter);
	       
	       StringToEntityConverter  taskChecklistConverter = new StringToEntityConverter();
	       taskChecklistConverter.setClazz(TaskChecklist.class);
	       taskChecklistConverter.setEm(em);
	       registry.addConverter(taskChecklistConverter);
	
	       StringToEntityConverter  teamConverter = new StringToEntityConverter();
	       teamConverter.setClazz(Team.class);
	       teamConverter.setEm(em);
	       registry.addConverter(teamConverter);
	       
	       StringToEntityConverter  technicianConverter = new StringToEntityConverter();
	       technicianConverter.setClazz(Technician.class);
	       technicianConverter.setEm(em);
	       registry.addConverter(technicianConverter);
	
	       StringToEntityConverter  userDetailConverter = new StringToEntityConverter();
	       userDetailConverter.setClazz(UserDetail.class);
	       userDetailConverter.setEm(em);
	       registry.addConverter(userDetailConverter);
	
	       StringToEntityConverter  userGroupConverter = new StringToEntityConverter();
	       userGroupConverter.setClazz(UserGroup.class);
	       userGroupConverter.setEm(em);
	       registry.addConverter(userGroupConverter);
	
	       StringToEntityConverter  utilityReadingConverter = new StringToEntityConverter();
	       utilityReadingConverter.setClazz(UtilityReading.class);
	       utilityReadingConverter.setEm(em);
	       registry.addConverter(utilityReadingConverter);
	
	       StringToEntityConverter  warehouseConverter = new StringToEntityConverter();
	       warehouseConverter.setClazz(Warehouse.class);
	       warehouseConverter.setEm(em);
	       registry.addConverter(warehouseConverter);
	       
	       StringToEntityConverter  workspaceEntityConverter = new StringToEntityConverter();
	       workspaceEntityConverter.setClazz(Workspace.class);
	       workspaceEntityConverter.setEm(em);
	       registry.addConverter(workspaceEntityConverter); 
       
	       StringToEntityConverter  assetSubTypeConverter = new StringToEntityConverter();
	       assetSubTypeConverter.setClazz(AssetSubtype.class);
	       assetSubTypeConverter.setEm(em);
	       registry.addConverter(assetSubTypeConverter);
	       
	       StringToEntityConverter  assetTypeConverter = new StringToEntityConverter();
	       assetTypeConverter.setClazz(AssetType.class);
	       assetTypeConverter.setEm(em);
	       registry.addConverter(assetTypeConverter);
	       
	       

	} 
}
