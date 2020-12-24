package com.aste.lsme.domain;


public class PredictiveCriteria {

	String building="-1";
	Long loc=-1l;
	String system="-1";
	Long subsystem=-1l;
	Long page=1l;
	Long total=0l;
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public Long getLoc() {
		return loc;
	}
	public void setLoc(Long loc) {
		this.loc = loc;
	}
	
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public Long getSubsystem() {
		return subsystem;
	}
	public void setSubsystem(Long subsystem) {
		this.subsystem = subsystem;
	}
	public Long getPage() {
		return page;
	}
	public void setPage(Long page) {
		this.page = page;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	
	

}
