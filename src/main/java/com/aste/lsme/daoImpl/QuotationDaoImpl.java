package com.aste.lsme.daoImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.QuotationDaoInteface;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.QuotEquipment;
import com.aste.lsme.domain.QuotLabourRate;
import com.aste.lsme.domain.QuotOtherVendorEquipment;
import com.aste.lsme.domain.QuotOtherVendorLabour;
import com.aste.lsme.domain.Quotation;
import com.aste.lsme.domain.QuotationKeygen;
import com.aste.lsme.domain.QuotationSearch;
import com.aste.lsme.domain.Workspace;



@Repository
public class QuotationDaoImpl implements QuotationDaoInteface {
	
	@PersistenceContext
	EntityManager em;
	
	
	public Quotation addQuotation(Quotation q) throws Exception
	{
		em.persist(q);
		return q;
	}
	
	@Override
	public QuotationKeygen getLastInsertedId(Workspace workspace) {
		Calendar cal = Calendar.getInstance();
		long month=month=(long) (cal.get(Calendar.MONTH)+ 1);
		TypedQuery<QuotationKeygen> d = (TypedQuery<QuotationKeygen>) em.createQuery(
				"Select l from QuotationKeygen l where workspace=:workspace and month=:month").setParameter("workspace", workspace).setParameter("month", month);		
		
		 List<QuotationKeygen> list=d.getResultList();
		 if(list.isEmpty())
			 return new QuotationKeygen();
		 else
		 {
			 QuotationKeygen key=new QuotationKeygen();
			 for(QuotationKeygen g:list)
			 {
				 key=g; 
			 }
		return key;
		 }
			 
	}
	
	@Override
	public Quotation update(Quotation qh) {
		return em.merge(qh);
	}
	
	
	@Override
	public boolean removeAllQuotationVendourLabours(long id, Workspace w) {
		/*TypedQuery<QuotOtherVendorLabour> d = (TypedQuery<QuotOtherVendorLabour>) entitymanager
				.createQuery(
						"Delete from QuotOtherVendorLabour l where l.quotHeader.id=:qid",
						QuotOtherVendorLabour.class);
		d.setParameter("qid", id);
		d.executeUpdate();*/
		try{
			Query query = em.createQuery(
				      "DELETE FROM QuotOtherVendorEquipment c WHERE c.quotation.id=:qid");
				  int deletedCount = query.setParameter("qid", id).executeUpdate();
				  System.out.println("deleted QOtherVLabrs="+deletedCount);
			}catch(Exception e){e.printStackTrace();}
			return true;
	}
	
	@Override
	public boolean removeAllQuotationVendourEquipments(long id, Workspace w) {
		/*TypedQuery<QuotOtherVendorEquipment> d = (TypedQuery<QuotOtherVendorEquipment>) entitymanager
				.createQuery(
						"Delete from QuotOtherVendorLabour l where l.quotHeader.id=:qid",
						QuotOtherVendorEquipment.class);
		d.setParameter("qid", id);
		d.executeUpdate();*/
		try{
		Query query = em.createQuery(
			      "DELETE FROM QuotOtherVendorLabour c WHERE c.quotation.id=:qid");
			  int deletedCount = query.setParameter("qid", id).executeUpdate();
			  System.out.println("deleted QuotOtherVendorLabour="+deletedCount);
		}catch(Exception e){e.printStackTrace();}
		return true;
	}
	
	@Override
	public boolean removeAllQuotationLabours(long id, Workspace w) {
		/*TypedQuery<QuotLabourRate> d = (TypedQuery<QuotLabourRate>) entitymanager
				.createQuery(
						"Delete from QuotOtherVendorEquipment l where l.quotHeader.id=:qid",
						QuotLabourRate.class);
		d.setParameter("qid", id);
		d.executeUpdate();*/
		try{
		Query query = em.createQuery(
			      "DELETE FROM QuotLabourRate c WHERE c.quotation.id=:qid");
			  int deletedCount = query.setParameter("qid", id).executeUpdate();
		}catch(Exception e){e.printStackTrace();}
		return true;
	}

	@Override
	public boolean removeAllQuotationSors(long id, Workspace w) {
		/*TypedQuery<QuotEquipment> d = (TypedQuery<QuotEquipment>) entitymanager.createQuery(
						"Delete  from QuotEquipment l where l.quotHeader.id=:qid",QuotEquipment.class);
		d.setParameter("qid", id);
		d.executeUpdate();*/try{
		Query query = em.createQuery(
			      "DELETE FROM QuotEquipment c WHERE c.quotation.id=:qid");
			  int deletedCount = query.setParameter("qid", id).executeUpdate();
		}catch(Exception e){e.printStackTrace();}
		return true;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Quotation> getQuotationListonids(List<Long> id)
	{
		Query query = em.createQuery("Select q from Quotation q where q.id in :ids")
				.setParameter("ids", id);
		return query.getResultList();
	}
	
	@Override
	public  Quotation findquotation(Long id)
	{
		 return em.find(Quotation.class,id);
	}
	
	@Override
	public  void saveQuotationKeygen(QuotationKeygen quotationkeygen)
	{
		em.persist(quotationkeygen);
	}
	
	@Override
	public  void updateQuotationKeygen(QuotationKeygen quotationkeygen)
	{
		em.merge(quotationkeygen);
	}
	
	@Override
	public QuotEquipment findQuotEquipment(long id) {
		// TODO Auto-generated method stub
		return em.find(QuotEquipment.class, id);
	}

	@Override
	public QuotLabourRate findQuotLabourRate(long id) {
		// TODO Auto-generated method stub
		return em.find(QuotLabourRate.class, id);
	}

	@Override
	public QuotOtherVendorEquipment findOtherVendorEquipment(long id) {
		// TODO Auto-generated method stub
		return em.find(QuotOtherVendorEquipment.class, id);
	}

	@Override
	public QuotOtherVendorLabour findOtherVendorLabour(long id) {
		// TODO Auto-generated method stub
		return em.find(QuotOtherVendorLabour.class, id);
	}
	

	@Override
	public List<QuotEquipment> getSORListForQuotaion(Long qId) {
		TypedQuery<QuotEquipment> q = (TypedQuery<QuotEquipment>) em
				.createQuery(
						"Select e from QuotEquipment e where e.quotation.id=:qId",
						QuotEquipment.class);
		q.setParameter("qId", qId);
		return q.getResultList();
	}

	@Override
	public List<QuotLabourRate> getLabourRateListForQuotaion(Long qId) {
		TypedQuery<QuotLabourRate> q = (TypedQuery<QuotLabourRate>) em
				.createQuery(
						"Select e from QuotLabourRate e where e.quotation.id=:qId",
						QuotLabourRate.class);
		q.setParameter("qId", qId);
		return q.getResultList();
	}

	@Override
	public List<QuotOtherVendorLabour> getVendourLabourListForQuotaion(Long qId) {
		TypedQuery<QuotOtherVendorLabour> q = (TypedQuery<QuotOtherVendorLabour>) em
				.createQuery(
						"Select e from QuotOtherVendorLabour e where e.quotation.id=:qId",
						QuotOtherVendorLabour.class);
		q.setParameter("qId", qId);
		return q.getResultList();
	}

	@Override
	public List<QuotOtherVendorEquipment> getVendorEquipListForQuotaion(Long qId) {
		TypedQuery<QuotOtherVendorEquipment> q = (TypedQuery<QuotOtherVendorEquipment>) em
				.createQuery(
						"Select e from QuotOtherVendorEquipment e where e.quotation.id=:qId",
						QuotOtherVendorEquipment.class);
		q.setParameter("qId", qId);
		return q.getResultList();
	}
	
	public List<Quotation> getallquotations(Workspace w)
	{
		TypedQuery<Quotation> d = (TypedQuery<Quotation>) em.createQuery(
				"Select l from Quotation l where l.workspace=:w ").setParameter("w", w);
		return d.getResultList();
		
	}
	
	@Override
	public Long countSearchPage(QuotationSearch qsc) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Quotation> qRoot = cq.from(Quotation.class);
		List<Predicate> pList = getPredicatesOnCriteria(qsc, cb, qRoot);
		cq.select(cb.count(qRoot)).where(
				cb.and(pList.toArray(new Predicate[0])));
		return em.createQuery(cq).getSingleResult();
	}
	
	public List<Quotation> getSearchPage(QuotationSearch qsc) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Quotation> cq = cb
				.createQuery(Quotation.class);
		Root<Quotation> qRoot = cq.from(Quotation.class);
		List<Predicate> pList = getPredicatesOnCriteria(qsc, cb, qRoot);
		cq.select(qRoot).where(cb.and(pList.toArray(new Predicate[0])));
		cq.orderBy(cb.desc(qRoot.get("quotationDate")));
		return em.createQuery(cq).setFirstResult(qsc.getFromPage())
				.setMaxResults(Constants.ROWS).getResultList();
	}
	
	
	
	public List<Predicate> getPredicatesOnCriteria(QuotationSearch qsc,
			CriteriaBuilder cb, Root<Quotation> qRoot) {

		List<Predicate> predicateList = new ArrayList<Predicate>();
		predicateList.add(cb.equal(qRoot.<Workspace> get("workspace"),
				qsc.getWorkspace()));
		
		if(!(qsc.getReferenceNumber().isEmpty()))
			predicateList.add(cb.equal(qRoot.get("refrenceno"),
					qsc.getReferenceNumber()));
		if(!(qsc.getQuotationCode().isEmpty()))
			predicateList.add(cb.equal(qRoot.get("quotationCode"),
					qsc.getQuotationCode()));
		if(!(qsc.getValue().isEmpty()))
			predicateList.add(cb.equal(qRoot.get("value"),
					qsc.getValue()));
		if(!(qsc.getType().isEmpty()))
			predicateList.add(cb.equal(qRoot.get("type"),
					qsc.getType()));
		
		if (qsc.getClientName() != null && qsc.getClientName() != "")
			predicateList.add(cb.like(
					((Expression<String>) qRoot.<String> get("quotationFor")),
					"%" + qsc.getClientName() + "%"));
		if(qsc.getFrom() != null && qsc.getTo() != null)
		{
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		    Date from = null;
		    Date to = null;
			try {
				from = formatter.parse(formatter.format(qsc.getFrom()));
				to=formatter.parse(formatter.format(qsc.getTo()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		    predicateList.add(cb.greaterThanOrEqualTo(qRoot.<Date>get("quotationDate"),from));
		    predicateList.add(cb.lessThanOrEqualTo(qRoot.<Date>get("quotationDate"),to));
		
		}
		return predicateList;
	}
	
	public TypedQuery<Quotation> getPredicatesOnCriteria1(
			QuotationSearch esc) throws ParseException {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Quotation> cq = cb.createQuery(Quotation.class);
		Root<Quotation> fromEE = cq.from(Quotation.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		predicates.add(cb.equal(fromEE.<Workspace>get("workspace"), esc.getWorkspace()));
			
		if(!(esc.getReferenceNumber().isEmpty()))
		     predicates.add(cb.equal(fromEE.get("refrenceno"),
					esc.getReferenceNumber()));
		if(!(esc.getQuotationCode().isEmpty()))
		   predicates.add(cb.equal(fromEE.get("quotationCode"),
					esc.getQuotationCode()));
		if((!(esc.getClientName().isEmpty())))
	       predicates.add(cb.equal(fromEE.get("quotationFor"),
					esc.getClientName()));
		if(esc.getFrom() != null)
		{
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		    Date from=formatter.parse(esc.getFrom().toString());
		    Date to=formatter.parse(esc.getTo().toString());
		    predicates.add(cb.greaterThanOrEqualTo(fromEE.<Date>get("quotationDate"),from));
		    predicates.add(cb.lessThanOrEqualTo(fromEE.<Date>get("quotationDate"),to));
		
		}
		cq.select(fromEE).where(predicates.toArray(new Predicate[] {}));
		cq.orderBy(cb.desc(fromEE.get("quotationCode")));
		return em.createQuery(cq);
	}	
	
	public void remove(Quotation q) throws Exception
	{
		 em.remove(em.find(Quotation.class, q.getId()));
	}

}
