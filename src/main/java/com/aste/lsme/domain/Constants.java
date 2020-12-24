package com.aste.lsme.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public interface Constants {

	String USER = "user";
	String ADMINISTRATOR = "administration";
	String RECEIVED = "Received";
	String RETURNED = "Returned";
	String ISSUED = "Issued";
	String RESERVED = "Reserved";
	String ASSETPROPERTY = "Asset_Property";
	String CMMS = "CMMS";
	String BDM = "Corrective Maintenance";
	String KPE = "kpe";
	String CONFIGURATOR = "Configurator";
	String PMAINTENANCE = "Preventive Maintenance";
	String PDMAINTENANCE = "Predictive Maintenance";
	String CREATEWS = " Worksite";
	String REPORTS = "Reports";
	String INVENTORY = "Inventory";
	String WSPACE = "WorkSpace Site";
	String CPM = "C&PM";
	String ELECTRICAL = "Electrical Equipment";
	String CIVIL = "Civil Asset";
	String MECHANICAL = "Mechanical Equipment";
	String FIRE = "Fire Equipment";
	String LIFT = "Lift Equipment";
	String LIFTSUBSYSTEM = "lift";
	String MECHANICALSUBSYSTEM = "mechanical";
	String COMMUNICATIONSUBSYSTEM = "communication";
	String COMMUNICATIONSYSTEM = "communicationsystem";
	String FIRESUBSYSTEM = "fire";
	String INSTRUMENTSUBSYSTEM = "instrument";
	String ELECTRICALSUBSYSTEM = "electrical";
	String CONTROLAUTOMATIONSUBSYSTEM = "controlAutomation";
	String CIVILSUBSYSTEM = "civil";
	String EQUIPMENT = " Equipment";
	String INSTRUMENT = "INSTRUMENTATION";
	String AUTOMATION = "CONTROL AUTOMATION";
	String RENTALS = "RENTALS";
	String DMMS = "DMS";
	String SEARCH = "SEARCH";
	String SOP = "SOP AND VEDIO";
	String EOP = "EOP";
	String PM = "Project Management";
	String HOME = "HOME";
	String UC = "Under Construction";
	String NF = "Not Found";
	String OVERDUE="Overdue";
	public static HashMap<String, String> EquipmentTypes = new HashMap<String, String>() {
	    {
	      put("ELECTRICAL", "ELECTRICAL");
	      put("MECHANICAL", "MECHANICAL");
	      put("CIVIL", "CIVIL");
	      put("ACMV", "ACMV");
	      put("FIRE", "FIRE");
	      
	    }
	  };
	 
	 String PATH="E:\\bin\\";
	//String PATH="F:\\images\\lsmeimages\\";

	//String PATH="D:\\nadeem\\docs\\images\\lsmeimages\\";
	String QUOTATION_PATH=PATH+"quotations\\";
	String TASK_IMAGE_PATH=PATH+"taskimages\\";
	String QR_IMAGES_PATH=PATH+"qrcodeimages\\";
	String PURCHASEORDER_PATH=PATH+"purchaseOrders\\";
	String LOCATION_QR_IMAGE_PATH=PATH+"locationimages\\";
    static final int SECOND = 1000;
	static final int MINUTE = 60 * SECOND;
	static final int HOUR = 60 * MINUTE;
	static final int DAY = 24 * HOUR;
	static final int ROWS = 10;
	public static HashMap<Integer, String> frequency = new HashMap<Integer, String>() {
		{
			put(1, "DAILY");
			put(2, "WEEKLY");
			put(3, "FORTNIGHTLY");
			put(4, "MONTHLY");
			put(5, "QUARTERLY");
			put(6, "HALFYEARLY");
			put(7, "YEARLY");
		}
	};
	public static HashMap<String, String> conditions = new HashMap<String, String>() {
		{
			put("yes", "YES");
			put("no", "NO");
			put("NA", "NA");
		}
	};
	String PARTIAL_ISSUE = "Partial Issue";
	String RESERVE_ISSUED = "issued";
	String ISSUE_NOT_RECEIVED = "notreceived";
	String COMPLETED = "Completed";

	String CUSTOM_SUCCESSMESSAGE = "customSuccessMessage";
	String CUSTOM_ERRORMESSAGE = "customErrorMessage";
	String EMAILFROM = "info@stie.com.sg";
	String ACMV = "ACMV Equipment";
	String AC = "AC Equipment";
	String ACMVSUBSYSTEM = "acmv";
	String ACSUBSYSTEM = "acmv";
	String POJECTECTID_HEADER = "workspace";
	String FEEDBACK = "Feedback";
	String SANITATION_PLUMBING_SUBSYSTEM = "Plumbing & Sanitary";
	String OPEN = "Open";
	String PAUSE = "Pause";
	String CLOSED = "Closed";
	String PAUSE_REQUESTED="Pause Requested";
	String KIV = "KIV";
	List<String> STATUS = Arrays.asList(OPEN,PAUSE,COMPLETED,CLOSED);
	String BEF_IMG = "FR-BI-";
	String AFT_IMG = "FR-AI-";
	String THRUSHOLD = "thrushhold";
	String PDF=".pdf";
	String ENCRYPTION_KEY="MaDdaPpGaWgA";
	String ROLE_TECHNICIAN ="Technician";
	String ROLE_MANAGING_AGENT="ManagingAgent";
	String TWO_FA_VERIFICATION = "Verify";
	String TASK_BEF_IMG = "TASK-BI-";
	String TASK_AFT_IMG = "TASK-AI-";
	String FR_NOTIFICATION_CLICK_ACTIVITY="EditFaultReportActivity";
	String TASK_NOTIFICATION_CLICK_ACTIVITY="PmTaskActivity";
	String YES="Yes";
	String GREATER="greater";
	String LESS="less";
	Double LOCATION_FENCE=100.00;
	Double EQUIPMENT_FENCE=100.0;
	Double BUILDING_FENCE=100.00;
	String ACTIVE="Active";
	String IN_ACTIVE="Inactive";
	String QUOTATION_ACCEPTED="Accepted";
	String QUOTATION_REJECTED="Rejected";
	enum Type 
	{ 
	    FAULT_CREATED, FAULT_COMPLETED,FAULT_PAUSED,FAULT_REOPENED, FAULT_PAUSE_REQUESTED,FAULT_ACCEPTED,EOT_ACCEPTED,EOT_REJECTED,FAULT_REJECTED,QUOTATION_UPLOADED,PURCHASE_ORDER_UPLOADED,TASK_COMPLETED,TASK_REOPENED,QUOTATION_ACCEPTED,QUOTATION_REJECTED; 
	} 

}
