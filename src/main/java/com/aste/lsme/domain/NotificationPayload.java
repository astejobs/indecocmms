package com.aste.lsme.domain;

import java.util.List;

public class NotificationPayload {
	
	List<String> devices;
	String title;
	String body;
	String activity;
	String workspace;
	String id;
	public List<String> getDevices() {
		return devices;
	}
	public void setDevices(List<String> devices) {
		this.devices = devices;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getWorkspace() {
		return workspace;
	}
	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public NotificationPayload(List<String> devices, String title, String body, String activity, String workspace,
			String id) {
		super();
		this.devices = devices;
		this.title = title;
		this.body = body;
		this.activity = activity;
		this.workspace = workspace;
		this.id = id;
	}
	
	
	public NotificationPayload(String title, String body, String activity, String workspace, String id) {
		super();
		this.title = title;
		this.body = body;
		this.activity = activity;
		this.workspace = workspace;
		this.id = id;
	}
	public NotificationPayload() {
		super();
	}
	
	
	
	
	
	

}
