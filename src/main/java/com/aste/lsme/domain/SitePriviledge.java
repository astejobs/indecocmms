package com.aste.lsme.domain;

import java.util.List;

public class SitePriviledge {
	
    UserGroup userGroup;	
	
    private List<Long> wspaces;

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public List<Long> getWspaces() {
		return wspaces;
	}

	public void setWspaces(List<Long> wspaces) {
		this.wspaces = wspaces;
	}


}
