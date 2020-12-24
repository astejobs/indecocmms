package com.aste.lsme.dao;

import java.util.Date;
import java.util.List;

import com.aste.lsme.domain.RequestForApproval;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.domain.Workspace;


public interface RequestForApprovalDaoInterface {

	public RequestForApproval persist(RequestForApproval req) throws Exception;
	public List<RequestForApproval> getAll(Workspace w, UserDetail user);
	public RequestForApproval getOne(long id);
	public void setStatus(String status, Long id,UserDetail user, Date d);
	public List<RequestForApproval> getRfaListForAdmin(Workspace w);
	public void setAdminComments(String comments, Long id , Date d);
	public List<RequestForApproval> getRfaListforPo(Workspace w);
	public  List<RequestForApproval> getResult(int from,int to);
	public long count();
	public List<RequestForApproval> getApprovedRequests(int from, int to);
	public long countApprovedRequests();
	public  List<RequestForApproval> getRAdmin(int from,int to);
	public long countforadmin();
	public void setFinanceDetails(String comment, Long id,UserDetail user, String financername, String status,Date date);
	public void merge(RequestForApproval req);
	public List<RequestForApproval> getRfaListForApprover(Workspace w);
	public List<RequestForApproval> getrfaforcostcenter(Workspace w);
	public List<RequestForApproval> getrfaforsiteapprover(Workspace w);
}
