package com.aste.lsme.webservicesDtos;

public class WorkspaceDto {
	
	String workspaceId;
	String buildingDescription;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public WorkspaceDto() {
		super();
	}




	public WorkspaceDto(String workspaceId, String buildingDescription) {
		super();
		this.workspaceId = workspaceId;
		this.buildingDescription = buildingDescription;
	}
	
	
	
	
	public String getWorkspaceId() {
		return workspaceId;
	}
	public void setWorkspaceId(String workspaceId) {
		this.workspaceId = workspaceId;
	}
	public String getBuildingDescription() {
		return buildingDescription;
	}
	public void setBuildingDescription(String buildingDescription) {
		this.buildingDescription = buildingDescription;
	}

	
	

}
