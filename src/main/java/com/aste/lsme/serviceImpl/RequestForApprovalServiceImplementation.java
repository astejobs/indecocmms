package com.aste.lsme.serviceImpl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.aste.lsme.dao.QuotationDaoInteface;
import com.aste.lsme.dao.RequestForApprovalDaoInterface;
import com.aste.lsme.domain.RequestForApproval;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.RequestForApprovalServiceInterface;



@Transactional
@Service
public class RequestForApprovalServiceImplementation implements
		RequestForApprovalServiceInterface {

	@Autowired
	private RequestForApprovalDaoInterface dao;
	
	@Autowired 
	QuotationDaoInteface quotationdao;
	
	@Override
	public RequestForApproval persist(RequestForApproval req) throws Exception {
		
		return  dao.persist(req);

	}

	@Override
	public List<RequestForApproval> getAll(Workspace w, UserDetail user) {
		// TODO Auto-generated method stub
		return dao.getAll(w,user);
	}

	@Override
	public RequestForApproval getOne(long id) {
		
		return dao.getOne(id);
	}
	
	public void setStatus(String status, Long id, UserDetail user ,Date d)
	{
		dao.setStatus(status, id,user, d);
	}
	
	public List<RequestForApproval> getRfaListForAdmin(Workspace w)
	{
		return dao.getRfaListForAdmin(w);
	}
	
	public long count()
	{
		return dao.count();
	}
	public  List<RequestForApproval> getResult(int from,int to)
	{
		 return dao.getResult(from, to);
	}
	public void setAdminComments(String comments, Long id , Date d)
	{
		dao.setAdminComments(comments, id, d);
	}
	
	public List<RequestForApproval> getApprovedRequests(int from, int to)
	{
		return dao.getApprovedRequests(from, to);
	}
	
	public long countApprovedRequests()
	{
		return dao.countApprovedRequests();
	}
	public  List<RequestForApproval> getRAdmin(int from,int to)
	{
		return dao.getRAdmin(from, to);
	}
	
	public long countforadmin()
	{
		return dao.countforadmin();
	}
	
	public void setFinanceDetails(String comment, Long id,UserDetail user, String financername, String status,Date date) {
		
		dao.setFinanceDetails(comment, id, user, financername, status, date);
	}
	public List<RequestForApproval> getRfaListforPo(Workspace w)
	{
		return dao.getRfaListforPo(w);
	}
	public void merge(RequestForApproval req)
	{
		dao.merge(req);
	}
	public List<RequestForApproval> getRfaListForApprover(Workspace w)
	{
		return dao.getRfaListForApprover(w);
	}
	public List<RequestForApproval> getrfaforcostcenter(Workspace w)
	{
		return dao.getrfaforcostcenter(w);
	}
	public List<RequestForApproval> getrfaforsiteapprover(Workspace w)
	{
		return dao.getrfaforsiteapprover(w);
	}

}
