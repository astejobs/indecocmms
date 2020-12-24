package com.aste.lsme.daoImpl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.RequestForApprovalDaoInterface;
import com.aste.lsme.domain.RequestForApproval;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.domain.Workspace;



@Repository
public class RequestForApprovalDaoImpl implements RequestForApprovalDaoInterface {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public RequestForApproval persist(RequestForApproval req) throws Exception {
	
		    em.persist(req);
		    return req;
	 }

	@Override
	public List<RequestForApproval> getAll(Workspace w, UserDetail user) {
		
		Query query=em.createQuery("select l from RequestForApproval l where l.workspace.id=:w AND l.requestor.id=:user ORDER BY l.submittionDate DESC")
				.setParameter("w", w.getId()).setParameter("user", user.getId());
         return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<RequestForApproval> getrfaforsiteapprover(Workspace w)
	{
		Query query=em.createQuery("select l from RequestForApproval l where l.workspace.id=:w ORDER BY l.submittionDate DESC")
				.setParameter("w", w.getId());
         return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<RequestForApproval> getrfaforcostcenter(Workspace w)
	{
		Query query=em.createQuery("select l from RequestForApproval l where l.siteApproverStatus='Accepted' AND  l.workspace.id=:w ORDER BY l.submittionDate DESC")
				.setParameter("w", w.getId());
         return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<RequestForApproval> getRfaListForApprover(Workspace w)
	{
		Query query = em.createQuery("select l from RequestForApproval l where l.costCenterStatus = 'Accepted' AND l.workspace.id=:w ORDER BY l.submittionDate DESC")
				.setParameter("w", w.getId());
		return query.getResultList();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<RequestForApproval> getRfaListforPo(Workspace w) {
		Query query=em.createQuery("select l from RequestForApproval l where l.appStatus='Accepted' AND l.workspace.id=:w ORDER BY l.submittionDate DESC")
				.setParameter("w", w.getId());
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<RequestForApproval> getRfaListForAdmin(Workspace w)
	{
		Query query = em.createQuery("select l from RequestForApproval l where l.finStatus = 'Accepted' AND l.workspace.id=:w ORDER BY l.submittionDate DESC")
				.setParameter("w", w.getId());
		return query.getResultList();
	}
	
	@Override
	public RequestForApproval getOne(long id) {
		
		return em.find(RequestForApproval.class, id);
	}
	

	public  List<RequestForApproval> getResult(int from,int to)
	{
		TypedQuery<RequestForApproval> r= (TypedQuery<RequestForApproval>) em
				.createQuery("Select l from RequestForApproval l");
		
		return r.setFirstResult(from).setMaxResults(to).getResultList();
	
	}

	
	
	public void setStatus(String status, Long id , UserDetail user,Date d)
	{
		RequestForApproval req = em.find(RequestForApproval.class, id);
		req.setReqStatus(status);
		req.setApprover(user);
		
		System.out.println(status+"----------------------------------------");
		if(status.equals("Accepted by approver"))
		{	
		req.setAppStatus("Accepted");
		req.setFinStatus("Pending");
		}
		else
		{
		req.setAppStatus("Rejected");
		}
		
		req.setApproverDate(d);
		em.merge(req);
		
	}
	
	
	
	
	public void setAdminComments(String comments, Long id , Date d)
	{
		RequestForApproval req = em.find(RequestForApproval.class, id);
		req.setAdminComments(comments);
		req.setReqStatus("Accepted by Admin");
		req.setAdStatus("Accepted");
		req.setAdminDate(d);
		em.merge(req);
		
	}
	
	
	public  List<RequestForApproval> getRAdmin(int from,int to)
	{
		TypedQuery<RequestForApproval> r= (TypedQuery<RequestForApproval>) em
				.createQuery("select l from RequestForApproval l where l.finStatus = 'Accepted'");
		
		return r.setFirstResult(from).setMaxResults(to).getResultList();
	
	}
	
	public long countforadmin() {
		TypedQuery d = (TypedQuery) em
				.createQuery("select count(l) from RequestForApproval l where l.finStatus = 'Accepted'");
		
		return (Long) d.getResultList().get(0);
	}


	public List<RequestForApproval> getApprovedRequests(int from, int to) {
		TypedQuery<RequestForApproval> r= (TypedQuery<RequestForApproval>)em.createQuery("select l from RequestForApproval l where l.appStatus='Accepted'");
		return  r.setFirstResult(from).setMaxResults(to).getResultList();
		
	}
	
	public long countApprovedRequests() {
		TypedQuery r= (TypedQuery)em.createQuery("select count(l) from RequestForApproval l where l.appStatus='Accepted'");
		return (Long) r.getResultList().get(0);
	}

	@Override
	public void setFinanceDetails(String comment, Long id, UserDetail user, String financername, String status,Date date) {
		RequestForApproval req = em.find(RequestForApproval.class, id);
		req.setFinaceapprover(user);
		req.setComments(comment);
		req.setFinancername(financername);
		req.setFinancerDate(date);
		req.setReqStatus(status);
		req.setFinStatus("Accepted");
		req.setAdStatus("Pending");
		em.merge(req);
		
	}
	
	public void merge(RequestForApproval req)
	{
		em.merge(req);
	}
	
	
	
	public long count() {
		TypedQuery d = (TypedQuery) em
				.createQuery("Select count(l) from  RequestForApproval l");
		
		return (Long) d.getResultList().get(0);
	}

}
