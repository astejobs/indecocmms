package com.aste.lsme.domain;

public class ChecklistSearch {
	
	private Equipment equipment;
	
	private String checklistname;
	
	private Workspace workspace;
	
	private int FromPage;

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public String getChecklistname() {
		return checklistname;
	}

	public void setChecklistname(String checklistname) {
		this.checklistname = checklistname;
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}

	public int getFromPage() {
		return FromPage;
	}

	public void setFromPage(int fromPage) {
		FromPage = fromPage;
	}
	
	
	

}
