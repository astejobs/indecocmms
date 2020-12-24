package com.aste.lsme.webservicesDtos;

public class TechnicianDTO {

	long id;
	String technicianName;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTechnicianName() {
		return technicianName;
	}
	public void setTechnicianName(String technicianName) {
		this.technicianName = technicianName;
	}
	public TechnicianDTO(long id, String technicianName) {
		super();
		this.id = id;
		this.technicianName = technicianName;
	}
	
	
}
