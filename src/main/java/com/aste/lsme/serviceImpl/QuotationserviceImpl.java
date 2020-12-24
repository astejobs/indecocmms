package com.aste.lsme.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.QuotationDaoInteface;
import com.aste.lsme.domain.QuotEquipment;
import com.aste.lsme.domain.QuotLabourRate;
import com.aste.lsme.domain.QuotOtherVendorEquipment;
import com.aste.lsme.domain.QuotOtherVendorLabour;
import com.aste.lsme.domain.Quotation;
import com.aste.lsme.domain.QuotationKeygen;
import com.aste.lsme.domain.QuotationSearch;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.QuotationServiceInterface;



@Service
@Transactional
public class QuotationserviceImpl implements QuotationServiceInterface {
	@Autowired
	QuotationDaoInteface quotdao;
	
	public Quotation addQuotation(Quotation q) throws Exception
	{

		q.setQuotationCode(getRevisedQuotationCode(q));
		if (q.getEquipmentList() != null)
			for (QuotEquipment qe : q.getEquipmentList()) {
				qe.setQuotation(q);
				
			}
		if (q.getLabourRateList() != null)
			for (QuotLabourRate qr : q.getLabourRateList()) {
				qr.setQuotation(q);
				
			}
		if (q.getOtherVendorEquipmentList() != null)
			for (QuotOtherVendorEquipment qve : q
					.getOtherVendorEquipmentList()) {
				qve.setQuotation(q);;
				
			}
		if (q.getOtherVendorLabourList() != null)
			for (QuotOtherVendorLabour ql : q.getOtherVendorLabourList()) {
			    System.out.println(ql.getUnitPrice()+"0-0-0-0-0-0-00-0--0-00-");
				ql.setQuotation(q);
				
			}
		return quotdao.addQuotation(q);
		
				
		
		
	}
	
	String getRevisedQuotationCode(Quotation qh) {
		QuotationKeygen keygen = quotdao.getLastInsertedId(qh.getWorkspace());
		Calendar cal = Calendar.getInstance();
		Long lastId=keygen.getCouter(); 
		Long month=keygen.getMonth();
		
		if(month==null)
		{
			lastId=(long) 0;
			++lastId;
			month=(long) (cal.get(Calendar.MONTH)+ 1);
			QuotationKeygen quotationkey=new QuotationKeygen();
			quotationkey.setCouter(lastId);
			quotationkey.setMonth(month);
			quotationkey.setWorkspace(qh.getWorkspace());
			quotdao.saveQuotationKeygen(quotationkey);
		}
		else if(month==(cal.get(Calendar.MONTH)+ 1))
		{ 
		++lastId;
		
		keygen.setCouter(lastId);
		keygen.setMonth(month);
		keygen.setWorkspace(qh.getWorkspace());
		quotdao.updateQuotationKeygen(keygen);
		}
		else
		{
			lastId=(long) 0;
			++lastId;
			
			month=(long) (cal.get(Calendar.MONTH)+ 1);
					keygen.setCouter(lastId);
					keygen.setMonth(month);
			keygen.setWorkspace(qh.getWorkspace());
			quotdao.saveQuotationKeygen(keygen);
		}
		
		cal.setTime(new Date());
		String code = "";
	
		
		System.out.println("QuotationCode:" + qh.getQuotationCode());
		if (qh.getQuotationCode() == null || qh.getQuotationCode().isEmpty()
				|| qh.getQuotationCode().equals("")) {
			/*if (qh.getWorkspace().getId().contains("-MHA-"))
				code = "STIE-" + qh.getWorkspace().getId().split("-")[1]
						+ "/" + (cal.get(Calendar.YEAR) ) 
						+ String.format("%02d", (cal.get(Calendar.MONTH)+ 1)) + "/" +  String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) +"-"
						+ String.format("%04d", lastId);*/
			
			
			 if (qh.getWorkspace().getWorkspaceId().contains("lsme-DEMO-"))
				code = "STIE/" + qh.getWorkspace().getWorkspaceId().split("-")[1]
						+ "/" + (cal.get(Calendar.YEAR) ) 
						+ String.format("%02d", (cal.get(Calendar.MONTH)+ 1)) + "/" +  String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) +"-"
						+ String.format("%04d", lastId);
			
			
			else if (qh.getWorkspace().getWorkspaceId().contains("lsme-HTS-"))
				code = "STIE/" + qh.getWorkspace().getWorkspaceId().split("-")[1]
						+ "/" + (cal.get(Calendar.YEAR) ) 
						+ String.format("%02d", (cal.get(Calendar.MONTH)+ 1)) + "/" +  String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) +"-"
						+ String.format("%04d", lastId);
			
			
			else if (qh.getWorkspace().getWorkspaceId().contains("HDB-Bedok-"))
				code = "HDB/"+ qh.getWorkspace().getWorkspaceId().split("-")[1]
						+ "/" + (cal.get(Calendar.YEAR) ) 
						+ String.format("%02d", (cal.get(Calendar.MONTH)+ 1)) + "/" +  String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) +"-"
						+ String.format("%04d", lastId);
			
			
			else if (qh.getWorkspace().getWorkspaceId().contains("HDB-KAKI-BUKIT-AUTOBAY"))
				code = "HDB/"+ qh.getWorkspace().getWorkspaceId().split("-")[1]
						+ "-BUKIT-AUTOBAY"  + "/" + (cal.get(Calendar.YEAR) ) 
						+ String.format("%02d", (cal.get(Calendar.MONTH)+ 1)) + "/" +  String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) +"-"
						+ String.format("%04d", lastId);
			
			
			else if (qh.getWorkspace().getWorkspaceId().contains("HDB-KAKI-BUKIT-KB1-"))
				code = "HDB/"+ qh.getWorkspace().getWorkspaceId().split("-")[1]
						+ "-BUKIT-KB1"  + "/" + (cal.get(Calendar.YEAR) ) 
						+ String.format("%02d", (cal.get(Calendar.MONTH)+ 1)) + "/" +  String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) +"-"
						+ String.format("%04d", lastId);
			
			
			else if (qh.getWorkspace().getWorkspaceId().contains("HDB-KAKIBUKIT-AUTOHUB-"))
				code = "HDB/"+ qh.getWorkspace().getWorkspaceId().split("-")[1]
						+ "-AUTOHUB"  + "/" + (cal.get(Calendar.YEAR) ) 
						+ String.format("%02d", (cal.get(Calendar.MONTH)+ 1)) + "/" +  String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) +"-"
						+ String.format("%04d", lastId);
			
			
			else if (qh.getWorkspace().getWorkspaceId().contains("HDB-KAMPONG-UBI-"))
				code = "HDB/"+ qh.getWorkspace().getWorkspaceId().split("-")[1]
						+ "-UBI"  + "/" + (cal.get(Calendar.YEAR) ) 
						+ String.format("%02d", (cal.get(Calendar.MONTH)+ 1)) + "/" +  String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) +"-"
						+ String.format("%04d", lastId);
			
			
			else if (qh.getWorkspace().getWorkspaceId().contains("HDB-Tampines-"))
				code = "HDB/"+ qh.getWorkspace().getWorkspaceId().split("-")[1]
						 + "/" + (cal.get(Calendar.YEAR) ) 
						+ String.format("%02d", (cal.get(Calendar.MONTH)+ 1)) + "/" +  String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) +"-"
						+ String.format("%04d", lastId);
			
			
			else if (qh.getWorkspace().getWorkspaceId().contains("HDB-UBIPLEX1-"))
				code = "HDB/"+ qh.getWorkspace().getWorkspaceId().split("-")[1]
						 + "/" + (cal.get(Calendar.YEAR) ) 
						+ String.format("%02d", (cal.get(Calendar.MONTH)+ 1)) + "/" +  String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) +"-"
						+ String.format("%04d", lastId);
			
			
			else if (qh.getWorkspace().getWorkspaceId().contains("PUB-Chestnut-"))
				code = "PUB/"+ qh.getWorkspace().getWorkspaceId().split("-")[1]
						 + "/" + (cal.get(Calendar.YEAR) ) 
						+ String.format("%02d", (cal.get(Calendar.MONTH)+ 1)) + "/" +  String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) +"-"
						+ String.format("%04d", lastId);
			
			
			else if (qh.getWorkspace().getWorkspaceId().contains("-CAG-T1-"))
				code =  qh.getWorkspace().getWorkspaceId().split("-")[1]
						 + "/T1"+"/" + (cal.get(Calendar.YEAR) ) 
						+ String.format("%02d", (cal.get(Calendar.MONTH)+ 1)) + "/" +  String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) +"-"
						+ String.format("%04d", lastId);
			
			
			else if (qh.getWorkspace().getWorkspaceId().contains("-CE-01-"))
				code =  "STIE/"+qh.getWorkspace().getWorkspaceId().split("-")[1]
						 + "/" + (cal.get(Calendar.YEAR) ) 
						+ String.format("%02d", (cal.get(Calendar.MONTH)+ 1)) + "/" +  String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) +"-"
						+ String.format("%04d", lastId);
			
			
			else if (qh.getWorkspace().getWorkspaceId().contains("-DSTA-DEM-"))
				code =  qh.getWorkspace().getWorkspaceId().split("-")[1]
						 + "/DEM"+"/" + (cal.get(Calendar.YEAR) ) 
						+ String.format("%02d", (cal.get(Calendar.MONTH)+ 1)) + "/" +  String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) +"-"
						+ String.format("%04d", lastId);
			
			else if (qh.getWorkspace().getWorkspaceId().contains("-HDB-DEFU-"))
				code =  qh.getWorkspace().getWorkspaceId().split("-")[1]
						 + "/DEFU"+"/" + (cal.get(Calendar.YEAR) ) 
						+ String.format("%02d", (cal.get(Calendar.MONTH)+ 1)) + "/" +  String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) +"-"
						+ String.format("%04d", lastId);
			
			
			
			else if (qh.getWorkspace().getWorkspaceId().contains("-HDB-UBI-"))
				code =  qh.getWorkspace().getWorkspaceId().split("-")[1]
						 + "/UBI"+"/" + (cal.get(Calendar.YEAR) ) 
						+ String.format("%02d", (cal.get(Calendar.MONTH)+ 1)) + "/" +  String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) +"-"
						+ String.format("%04d", lastId);
			
			
			else if (qh.getWorkspace().getWorkspaceId().contains("-KTPH-"))
				code =  "STIE/"+qh.getWorkspace().getWorkspaceId().split("-")[1]
						 + "/" + (cal.get(Calendar.YEAR) ) 
						+ String.format("%02d", (cal.get(Calendar.MONTH)+ 1)) + "/" +  String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) +"-"
						+ String.format("%04d", lastId);
			
			
			
			else if (qh.getWorkspace().getWorkspaceId().contains("-MHA-ICA-Lavender-"))
				code =  qh.getWorkspace().getWorkspaceId().split("-")[1]
						 + "/ICA-Lavender"+"/" + (cal.get(Calendar.YEAR) ) 
						+ String.format("%02d", (cal.get(Calendar.MONTH)+ 1)) + "/" +  String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) +"-"
						+ String.format("%04d", lastId);
			 
			 
			else if (qh.getWorkspace().getWorkspaceId().contains("-MHA-ICA-"))
				code =  qh.getWorkspace().getWorkspaceId().split("-")[1]
						 + "/ICA"+"/" + (cal.get(Calendar.YEAR) ) 
						+ String.format("%02d", (cal.get(Calendar.MONTH)+ 1)) + "/" +  String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) +"-"
						+ String.format("%04d", lastId);
			 
			 
			else if (qh.getWorkspace().getWorkspaceId().contains("-MHA-NPPK-"))
				code =  qh.getWorkspace().getWorkspaceId().split("-")[1]
						 + "/NPPK"+"/" + (cal.get(Calendar.YEAR) ) 
						+ String.format("%02d", (cal.get(Calendar.MONTH)+ 1)) + "/" +  String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) +"-"
						+ String.format("%04d", lastId);
			 
			 
			 
			else if (qh.getWorkspace().getWorkspaceId().contains("-MHA-PCC-"))
				code =  qh.getWorkspace().getWorkspaceId().split("-")[1]
						 + "/PCC"+"/" + (cal.get(Calendar.YEAR) ) 
						+ String.format("%02d", (cal.get(Calendar.MONTH)+ 1)) + "/" +  String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) +"-"
						+ String.format("%04d", lastId);
			 
			 
			 
			else if (qh.getWorkspace().getWorkspaceId().contains("-MHA-SCDF-"))
				code =  qh.getWorkspace().getWorkspaceId().split("-")[1]
						 + "/SCDF"+"/" + (cal.get(Calendar.YEAR) ) 
						+ String.format("%02d", (cal.get(Calendar.MONTH)+ 1)) + "/" +  String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) +"-"
						+ String.format("%04d", lastId);
			 
			 
			 
			else if (qh.getWorkspace().getWorkspaceId().contains("-MHA-SPF-"))
				code =  qh.getWorkspace().getWorkspaceId().split("-")[1]
						 + "/SPF"+"/" + (cal.get(Calendar.YEAR) ) 
						+ String.format("%02d", (cal.get(Calendar.MONTH)+ 1)) + "/" +  String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) +"-"
						+ String.format("%04d", lastId);
			 
			 
			else if (qh.getWorkspace().getWorkspaceId().contains("-MHA-Woodlands-"))
				code =  qh.getWorkspace().getWorkspaceId().split("-")[1]
						 + "/WCP"+"/" + (cal.get(Calendar.YEAR) ) 
						+ String.format("%02d", (cal.get(Calendar.MONTH)+ 1)) + "/" +  String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) +"-"
						+ String.format("%04d", lastId);
			 
			 
			else if (qh.getWorkspace().getWorkspaceId().contains("-NLB-"))
				code = "STIE/"+ qh.getWorkspace().getWorkspaceId().split("-")[1]
						 + "/" + (cal.get(Calendar.YEAR) ) 
						+ String.format("%02d", (cal.get(Calendar.MONTH)+ 1)) + "/" +  String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) +"-"
						+ String.format("%04d", lastId);
			 
			 
			else if (qh.getWorkspace().getWorkspaceId().contains("-SHS-"))
				code =  "STIE/"+qh.getWorkspace().getWorkspaceId().split("-")[1]
						 +"/" + (cal.get(Calendar.YEAR) ) 
						+ String.format("%02d", (cal.get(Calendar.MONTH)+ 1)) + "/" +  String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) +"-"
						+ String.format("%04d", lastId);
			 
			 
			else if (qh.getWorkspace().getWorkspaceId().contains("-TTSH-"))
				code =  "STIE/"+qh.getWorkspace().getWorkspaceId().split("-")[1]
						 + "/" + (cal.get(Calendar.YEAR) ) 
						+ String.format("%02d", (cal.get(Calendar.MONTH)+ 1)) + "/" +  String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) +"-"
						+ String.format("%04d", lastId);
			
			
			
			else if (qh.getWorkspace().getWorkspaceId().contains("-MOM-"))
				code =  "STIE/"+qh.getWorkspace().getWorkspaceId().split("-")[1]
						 + "/" + (cal.get(Calendar.YEAR) ) 
						+ String.format("%02d", (cal.get(Calendar.MONTH)+ 1)) + "/" +  String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) +"-"
						+ String.format("%04d", lastId);
			else
				code = "no/" + qh.getWorkspace().getWorkspaceId().split("-")[1]
						+ "/" + (cal.get(Calendar.YEAR) ) 
						+ String.format("%02d", (cal.get(Calendar.MONTH)+ 1)) + "/" +  String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) +"-" + String.format("%04d", lastId);
			return code;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		if (qh.getQuotationCode().contains("REVISED"))
			code = qh.getQuotationCode().split("REVISED")[0] + "REVISED("
					+ sdf.format(new Date()) + ")";
		else
			code = qh.getQuotationCode() + "-REVISED(" + sdf.format(new Date())
					+ ")";
		return code;

	}
	
	public Quotation update(Quotation qh) {
		try {
			
			quotdao.removeAllQuotationSors(qh.getId(), qh.getWorkspace());
			quotdao.removeAllQuotationLabours(qh.getId(),
					qh.getWorkspace());
			quotdao.removeAllQuotationVendourEquipments(qh.getId(),
					qh.getWorkspace());
			quotdao.removeAllQuotationVendourLabours(qh.getId(),
					qh.getWorkspace());

			if (qh.getEquipmentList() != null)
				for (QuotEquipment qe : qh.getEquipmentList()) {
					System.out.println("i m adding12121212212112121122 equipment");
					qe.setQuotation(qh);
					
				}
			if (qh.getLabourRateList() != null)
				for (QuotLabourRate qr : qh.getLabourRateList()) {
					qr.setQuotation(qh);
					
				}
			if (qh.getOtherVendorEquipmentList() != null)
				for (QuotOtherVendorEquipment qve : qh
						.getOtherVendorEquipmentList()) {
					qve.setQuotation(qh);
					
				}
			if (qh.getOtherVendorLabourList() != null)
				for (QuotOtherVendorLabour ql : qh.getOtherVendorLabourList()) {
					ql.setQuotation(qh);
					
				}
			
			quotdao.update(qh);
			return qh;
		} catch (Exception e) {
			System.out.println("" + qh + " id=" + qh.getId());
			e.printStackTrace();
			return null;
		}

	}

	
	public List<Quotation> getallquotations(Workspace w)
	{
		return quotdao.getallquotations(w);
	}
	
	public  Quotation findquotation(Long id)
	{
		return quotdao.findquotation(id);
	}
	
	public QuotEquipment findQuotEquipment(long id)
	{
		return quotdao.findQuotEquipment(id);
		
	}
	public QuotLabourRate findQuotLabourRate(long id)
	{
		return quotdao.findQuotLabourRate(id);
		
	}
	public QuotOtherVendorEquipment findOtherVendorEquipment(long id)
	{
		return quotdao.findOtherVendorEquipment(id);
	}
	public QuotOtherVendorLabour findOtherVendorLabour(long id)
	{
		return quotdao.findOtherVendorLabour(id);
		
	}

	
	public TypedQuery<Quotation> getPredicatesOnCriteria1(
			QuotationSearch esc) throws ParseException 
	{
		return quotdao.getPredicatesOnCriteria1(esc);
		
	}
	
	public Long countSearchPage(QuotationSearch qsc) 
	{
		return quotdao.countSearchPage(qsc);
	}

	@Override
	public List<QuotEquipment> getSORListForQuotaion(Long qId) {
		
		return  quotdao.getSORListForQuotaion(qId);
	}

	@Override
	public List<QuotLabourRate> getLabourRateListForQuotaion(Long qId) {
		return quotdao.getLabourRateListForQuotaion(qId);
	}

	@Override
	public List<QuotOtherVendorLabour> getVendourLabourListForQuotaion(Long qId) {
		return quotdao.getVendourLabourListForQuotaion(qId);
	}

	@Override
	public List<QuotOtherVendorEquipment> getVendorEquipListForQuotaion(Long qId) {
		return quotdao.getVendorEquipListForQuotaion(qId);
	}
	
	public List<Quotation> getSearchPage(QuotationSearch qsc)
	{
		
		return quotdao.getSearchPage(qsc);
	}
	
	public List<Quotation> getQuotationListonids(List<Long> id)
	{
		return quotdao.getQuotationListonids(id);
	}
	
	public void remove(Quotation q) throws Exception
	{
		 quotdao.remove(q);
	}

}
