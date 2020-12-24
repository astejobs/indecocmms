package com.aste.lsme.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aste.lsme.domain.Building;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.QuotationSearch;
import com.aste.lsme.domain.Quotations;
import com.aste.lsme.domain.RequestForApproval;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.domain.UserGroup;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.GroupServiceInterface;
import com.aste.lsme.service.QuotServiceInterface;
import com.aste.lsme.service.RequestForApprovalServiceInterface;
import com.aste.lsme.service.UserDetailsServiceInterface;
import com.aste.lsme.serviceImpl.QuotationserviceImpl;

@Controller
@RequestMapping(value = "/requestForApproval")
public class RequestForApprovalController {

	@Autowired
	RequestForApprovalServiceInterface reqservice;

	@Autowired
	QuotServiceInterface quotservice;

	@Autowired
	UserDetailsServiceInterface userdetail;

	@Autowired
	QuotationserviceImpl quotationservice;

	@Autowired
	GroupServiceInterface groupservice;

	@Autowired
	private JavaMailSender mailSender;

	@RequestMapping(value = "/createrfa", method = RequestMethod.GET)
	public String RequestForApproval(Model model) {
		if (!model.containsAttribute("asset"))
			model.addAttribute("req", new RequestForApproval());
		else
			model.addAttribute("req", model.asMap().get("asset"));

		model.addAttribute("org.springframework.validation.BindingResult.req", model.asMap().get("error"));

		return "requestforapproval";
	}

	@RequestMapping(value = "/createrfa", method = RequestMethod.POST)
	public String saveRequestForApproval(@Valid @ModelAttribute("req") RequestForApproval rfa, BindingResult err,
			@RequestParam(value = "supportingdoc", required = false) MultipartFile[] multipartFile,
			RedirectAttributes att, Model model,
			@RequestParam(value = "requestorsigniture", required = false) MultipartFile signiture,
			@RequestParam(value = "poDoc", required = false) MultipartFile podocument,
			HttpServletRequest RequestForApproval, HttpSession session, HttpServletRequest request)
			throws MessagingException, ParseException {

		
		String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

		List<Long> ids = new ArrayList<Long>();
		Double totalprice = 0.0;
		if (err.hasErrors()) {
			att.addFlashAttribute("asset", rfa);
			att.addFlashAttribute("error", err);
			return "redirect:/requestForApproval/createrfa";
		}
		for (int i = 0; i < rfa.getQuotationheader().size(); i++) {
			ids.add(rfa.getQuotationheader().get(i).getId());
		}

		rfa.setQuotationheader(quotationservice.getQuotationListonids(ids));
		if (rfa.getQuotation() == null) {
			att.addFlashAttribute("asset", rfa);
			att.addFlashAttribute("fail", "Add atleast one Quotation");
			return "redirect:/requestForApproval/createrfa";
		}
		if(rfa.getQuotation().size()== 1){
			   if(rfa.getQuotation().get(0).getPriceQuoted()> 500){
				    att.addFlashAttribute("asset", rfa);
				    att.addFlashAttribute("fail", "The Cost u entered is Exceeding 500");
					att.addFlashAttribute("quotatoinheaders", quotationservice.getQuotationListonids(ids));
					return "redirect:/requestForApproval/createrfa";
				}
		   }
		 else if(rfa.getQuotation().size()== 2){
			   if(rfa.getQuotation().get(0).getPriceQuoted()+rfa.getQuotation().get(1).getPriceQuoted()> 1000
					     || rfa.getQuotation().get(1).getPriceQuoted()+rfa.getQuotation().get(1).getPriceQuoted()<=500){
				    att.addFlashAttribute("asset", rfa);
				    att.addFlashAttribute("fail", "The Cost u entered is Exceeding 1000 or Less than or Equal  500");
					att.addFlashAttribute("quotatoinheaders", quotationservice.getQuotationListonids(ids));
					return "redirect:/requestForApproval/createrfa";
				}
			   
		   }
		 else if(rfa.getQuotation().size()== 3){
			   if(rfa.getQuotation().get(0).getPriceQuoted()+rfa.getQuotation().get(1).getPriceQuoted()+rfa.getQuotation().get(2).getPriceQuoted()< 1000 ){
				    att.addFlashAttribute("asset", rfa);
				    att.addFlashAttribute("fail", "The Cost u entered is Less than 1000");
					att.addFlashAttribute("quotatoinheaders", quotationservice.getQuotationListonids(ids));
					return "redirect:/requestForApproval/createrfa";
				}
			   
		   }
		for (int i = 0; i < rfa.getQuotation().size(); i++) {
			try {
				totalprice += rfa.getQuotation().get(i).getPriceQuoted();
			} catch (Exception ex) {
				att.addFlashAttribute("asset", rfa);
				att.addFlashAttribute("fail", "Please Enter a proper Price for Quotation " + (i + 1));
				att.addFlashAttribute("quotatoinheaders", quotationservice.getQuotationListonids(ids));
				return "redirect:/requestForApproval/createrfa";
			}
			if (!multipartFile[i].isEmpty()) {
				String imageName = String.valueOf(System.currentTimeMillis()) + "."
						+ FilenameUtils.getExtension(multipartFile[i].getOriginalFilename());
				if (uploaddoc(multipartFile[i], att, imageName, i)) {
					att.addFlashAttribute("asset", rfa);
					att.addFlashAttribute("quotatoinheaders", quotationservice.getQuotationListonids(ids));
					return "redirect:/requestForApproval/createrfa";
				}
				rfa.getQuotation().get(i).setFilePath(imageName);
			} else {
				att.addFlashAttribute("asset", rfa);
				att.addFlashAttribute("fail", "Upload Document For The Quotation " + (i + 1));
				att.addFlashAttribute("quotatoinheaders", quotationservice.getQuotationListonids(ids));
				return "redirect:/requestForApproval/createrfa";
			}
			rfa.getQuotation().get(i).setRequestor(rfa);
		}
            
		if (!signiture.isEmpty()) {
			String imageName = String.valueOf(
					System.currentTimeMillis() + "." + FilenameUtils.getExtension(signiture.getOriginalFilename()));
			if (uploadsigniture(signiture, att, imageName)) {
				att.addFlashAttribute("asset", rfa);
				att.addFlashAttribute("quotatoinheaders", quotationservice.getQuotationListonids(ids));
				return "redirect:/requestForApproval/createrfa";
			}
			rfa.setSigniture(imageName);
		} else {
			att.addFlashAttribute("asset", rfa);
			att.addFlashAttribute("fail", " Please Upload The Signiture");
			att.addFlashAttribute("quotatoinheaders", quotationservice.getQuotationListonids(ids));
			return "redirect:/requestForApproval/createrfa";
		}

		if (podocument != null) {

			if (!podocument.isEmpty()) {
				String imageName = String.valueOf(System.currentTimeMillis() + "."
						+ FilenameUtils.getExtension(podocument.getOriginalFilename()));
				if (uploadpodocument(podocument, att, imageName)) {
					att.addFlashAttribute("asset", rfa);
					att.addFlashAttribute("quotatoinheaders", quotationservice.getQuotationListonids(ids));
					return "redirect:/requestForApproval/createrfa";
				}
				rfa.setPoDocument(imageName);
			} else {
				att.addFlashAttribute("asset", rfa);
				att.addFlashAttribute("fail", " Please Upload The PO Document");
				att.addFlashAttribute("quotatoinheaders", quotationservice.getQuotationListonids(ids));
				return "redirect:/requestForApproval/createrfa";
			}
		}

		rfa.setWorkspace((Workspace) session.getAttribute("workspace"));
		rfa.setSubmittionDate(new Date());
		rfa.setReqStatus("Pending At Site Reviewer");
		rfa.setSiteApproverStatus("Pending");
		rfa.setRequestor((UserDetail) session.getAttribute("user"));
		rfa.setTotalPriceQuoted(totalprice);

		try {
			
			reqservice.persist(rfa);
			Workspace w = (Workspace) session.getAttribute("workspace");
			if(w.getWorkspaceId().contains("ICA")){
				String htmlMsg = "Hello Sir/Madam ,A new Rfa with <br/> Reference No. " + rfa.getRefnumber()
				+ ", Job Site: " + rfa.getJobSite() + ", <br/> Total Price Quoted: " + rfa.getTotalPriceQuoted()
				+ " has been Created"
				+ " <br/> This email needs an immediate action.Please Click on the Link, <br/> Thanks";
		      String subject = "Rfa Created";
		      constructEmail(appUrl + "/requestForApproval/viewRequest?id=" + rfa.getId() + "&user=siteApprover", rfa,
		    		  groupservice.findGroupbyName("SiteReviewerICA").getUserdetails().get(0), htmlMsg, subject,
				(Workspace) session.getAttribute("workspace"));
		       att.addFlashAttribute("success", "Request Successfully  Submitted");
				
			}
			else if(w.getWorkspaceId().contains("SCDF")){
				String htmlMsg = "Hello Sir/Madam ,A new Rfa with <br/> Reference No. " + rfa.getRefnumber()
				+ ", Job Site: " + rfa.getJobSite() + ", <br/> Total Price Quoted: " + rfa.getTotalPriceQuoted()
				+ " has been Created"
				+ " <br/> This email needs an immediate action.Please Click on the Link, <br/> Thanks";
		      String subject = "Rfa Created";
		      constructEmail(appUrl + "/requestForApproval/viewRequest?id=" + rfa.getId() + "&user=siteApprover", rfa,
		    		  groupservice.findGroupbyName("SiteReviewerSCDF").getUserdetails().get(0), htmlMsg, subject,
				(Workspace) session.getAttribute("workspace"));
		       att.addFlashAttribute("success", "Request Successfully  Submitted");
				
			}
			else if(w.getWorkspaceId().contains("SPF")){
				String htmlMsg = "Hello Sir/Madam ,A new Rfa with <br/> Reference No. " + rfa.getRefnumber()
				+ ", Job Site: " + rfa.getJobSite() + ", <br/> Total Price Quoted: " + rfa.getTotalPriceQuoted()
				+ " has been Created"
				+ " <br/> This email needs an immediate action.Please Click on the Link, <br/> Thanks";
		      String subject = "Rfa Created";
		      constructEmail(appUrl + "/requestForApproval/viewRequest?id=" + rfa.getId() + "&user=siteApprover", rfa,
		    		  groupservice.findGroupbyName("SiteReviewerSPF").getUserdetails().get(0), htmlMsg, subject,
				(Workspace) session.getAttribute("workspace"));
		       att.addFlashAttribute("success", "Request Successfully  Submitted");
				
			}
			else if(w.getWorkspaceId().contains("SPS")){
				String htmlMsg = "Hello Sir/Madam ,A new Rfa with <br/> Reference No. " + rfa.getRefnumber()
				+ ", Job Site: " + rfa.getJobSite() + ", <br/> Total Price Quoted: " + rfa.getTotalPriceQuoted()
				+ " has been Created"
				+ " <br/> This email needs an immediate action.Please Click on the Link, <br/> Thanks";
		      String subject = "Rfa Created";
		      constructEmail(appUrl + "/requestForApproval/viewRequest?id=" + rfa.getId() + "&user=siteApprover", rfa,
		    		  groupservice.findGroupbyName("SiteReviewerSPS").getUserdetails().get(0), htmlMsg, subject,
				(Workspace) session.getAttribute("workspace"));
		       att.addFlashAttribute("success", "Request Successfully  Submitted");
				
				
			}
			else if(w.getWorkspaceId().contains("MHAEast")){
				String htmlMsg = "Hello Sir/Madam ,A new Rfa with <br/> Reference No. " + rfa.getRefnumber()
				+ ", Job Site: " + rfa.getJobSite() + ", <br/> Total Price Quoted: " + rfa.getTotalPriceQuoted()
				+ " has been Created"
				+ " <br/> This email needs an immediate action.Please Click on the Link, <br/> Thanks";
		      String subject = "Rfa Created";
		      constructEmail(appUrl + "/requestForApproval/viewRequest?id=" + rfa.getId() + "&user=siteApprover", rfa,
		    		  groupservice.findGroupbyName("SiteReviewerMHAEast").getUserdetails().get(0), htmlMsg, subject,
				(Workspace) session.getAttribute("workspace"));
		        att.addFlashAttribute("success", "Request Successfully  Submitted");
				
			}
			else if(w.getWorkspaceId().contains("DEMO")){
				String htmlMsg = "Hello Sir/Madam ,A new Rfa with <br/> Reference No. " + rfa.getRefnumber()
				+ ", Job Site: " + rfa.getJobSite() + ", <br/> Total Price Quoted: " + rfa.getTotalPriceQuoted()
				+ " has been Created"
				+ " <br/> This email needs an immediate action.Please Click on the Link, <br/> Thanks";
		      String subject = "Rfa Created";
		      constructEmail(appUrl + "/requestForApproval/viewRequest?id=" + rfa.getId() + "&user=siteApprover", rfa,
		    		  groupservice.findGroupbyName("SiteReviewerDEMO").getUserdetails().get(0), htmlMsg, subject,
				(Workspace) session.getAttribute("workspace"));
		        att.addFlashAttribute("success", "Request Successfully  Submitted");
				
			}
			
		} catch (Exception ex) {
			att.addFlashAttribute("asset", rfa);
			att.addFlashAttribute("quotatoinheaders", quotationservice.getQuotationListonids(ids));
			att.addFlashAttribute("fail",
					"Request with the Reference Number " + rfa.getRefnumber() + " already Submitted");
		}

		return "redirect:/requestForApproval/createrfa";

	}

	@RequestMapping(value = "/viewallrequests", method = RequestMethod.GET)
	public String viewmyRequests(Model model, HttpSession session) {

		model.addAttribute("req", reqservice.getAll((Workspace) session.getAttribute("workspace"),
				(UserDetail) session.getAttribute("user")));
		return "viewallrequests";
	}
	
	@RequestMapping(value = "/viewallrequestsforsiteapprover", method = RequestMethod.GET)
	public String rfaforSiteApprover(Model model, HttpSession session) {

		model.addAttribute("req", reqservice.getrfaforsiteapprover((Workspace) session.getAttribute("workspace")));
		return "rfaSiteApprover";
	}

	@RequestMapping(value = "/updaterequestforsiteapprover", method = RequestMethod.POST)
	public String updaterfaforSiteApprover(@RequestParam("siteapproversigniture") MultipartFile signiture,
			@RequestParam("id") Long id, HttpServletRequest request, HttpSession session, RedirectAttributes att) {

		String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		RequestForApproval req = reqservice.getOne(id);

		if (request.getParameter("value").equals("Reject")) {
			req.setReqStatus("Rejected By  Site Reviewer");
			req.setSiteApproverStatus("Rejected");
			req.setSiteapprover((UserDetail) session.getAttribute("user"));
			req.setSiteApproverDate(new Date());
			reqservice.merge(req);
			String htmlMsg = "Hello Sir/Madam ,Rfa with <br/> Reference No. " + req.getRefnumber() + ", Job Site: "
					+ req.getJobSite() + ", <br/> Total Price Quoted: " + req.getTotalPriceQuoted()
					+ " has been Rejected At Site Approver" + " <br/><br/> Thanks";
			String subject = "Rfa Rejected";
			constructEmail(null, req, req.getRequestor(), htmlMsg, subject,
					(Workspace) session.getAttribute("workspace"));
			return "redirect:/requestForApproval/viewallrequestsforsiteapprover";
		}
		if (!signiture.isEmpty()) {
			String imageName = String.valueOf(System.currentTimeMillis()) + "."
					+ FilenameUtils.getExtension(signiture.getOriginalFilename());
			if (uploadsigniture(signiture, att, imageName))
				return "redirect:/requestForApproval/viewRequest?id=" + id + "&user=" + "siteApprover";

			req.setSiteApproverSig(imageName);
		} else {
			att.addFlashAttribute("fail", "Please Upload Your Signiture");
			return "redirect:/requestForApproval/viewRequest?id=" + id + "&user=" + "siteApprover";
		}
		req.setSiteApproverDate(new Date());
		req.setReqStatus("Pending At HQ Reviewer");
		req.setSiteApproverStatus("Accepted");
		req.setCostCenterStatus("Pending");
		req.setSiteapprover((UserDetail) session.getAttribute("user"));
		reqservice.merge(req);
		String htmlMsg = "Hello Sir/Madam ,A new Rfa with <br/> Reference No. " + req.getRefnumber() + ", Job Site: "
				+ req.getJobSite() + ", <br/> Total Price Quoted: " + req.getTotalPriceQuoted()
				+ "has been Approved By Site Approver"
				+ " <br/> This email needs an immediate action.Please Click on the Link, <br/> Thanks";
		String subject = "Rfa Accepted";
		constructEmail(appUrl + "/requestForApproval/viewRequest?id=" + req.getId() + "&user=costCenter", req,
				groupservice.findGroupbyName("Cost Center").getUserdetails().get(0), htmlMsg, subject,
				(Workspace) session.getAttribute("workspace"));
		return "redirect:/requestForApproval/viewallrequestsforsiteapprover";
	}


	@RequestMapping(value = "/viewallrequestsforcostcenter", method = RequestMethod.GET)
	public String rfaforCostcenter(Model model, HttpSession session) {

		model.addAttribute("req", reqservice.getrfaforcostcenter((Workspace) session.getAttribute("workspace")));
		return "rfaCostCenter";
	}

	@RequestMapping(value = "/updaterequestforcostcenter", method = RequestMethod.POST)
	public String updaterfaforCostcenter(@RequestParam("costcentersigniture") MultipartFile signiture,
			@RequestParam("id") Long id, HttpServletRequest request, HttpSession session, RedirectAttributes att) {

		String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		RequestForApproval req = reqservice.getOne(id);

		if (request.getParameter("value").equals("Reject")) {
			req.setReqStatus("Rejected By HQ Reviewer");
			req.setCostCenterStatus("Rejected");
			req.setCostcenter((UserDetail) session.getAttribute("user"));
			req.setCostCenterdate(new Date());
			reqservice.merge(req);
			String htmlMsg = "Hello Sir/Madam ,Rfa with <br/> Reference No. " + req.getRefnumber() + ", Job Site: "
					+ req.getJobSite() + ", <br/> Total Price Quoted: " + req.getTotalPriceQuoted()
					+ " has been Rejected At Cost Center" + " <br/><br/> Thanks";
			String subject = "Rfa Rejected";
			constructEmail(null, req, req.getRequestor(), htmlMsg, subject,
					(Workspace) session.getAttribute("workspace"));
			return "redirect:/requestForApproval/viewallrequestsforcostcenter";
		}
		if (!signiture.isEmpty()) {
			String imageName = String.valueOf(System.currentTimeMillis()) + "."
					+ FilenameUtils.getExtension(signiture.getOriginalFilename());
			if (uploadsigniture(signiture, att, imageName))
				return "redirect:/requestForApproval/viewRequest?id=" + id + "&user=" + "costCenter";

			req.setCostCenterSig(imageName);
		} else {
			att.addFlashAttribute("fail", "Please Upload Your Signiture");
			return "redirect:/requestForApproval/viewRequest?id=" + id + "&user=" + "costCenter";
		}
		req.setCostCenterdate(new Date());
		req.setReqStatus("Pending At Approver");
		req.setCostCenterStatus("Accepted");
		req.setAppStatus("Pending");
		req.setCostcenter((UserDetail) session.getAttribute("user"));
		reqservice.merge(req);
		String htmlMsg = "Hello Sir/Madam ,A new Rfa with <br/> Reference No. " + req.getRefnumber() + ", Job Site: "
				+ req.getJobSite() + ", <br/> Total Price Quoted: " + req.getTotalPriceQuoted()
				+ "has been Approved By Cost Center"
				+ " <br/> This email needs an immediate action.Please Click on the Link, <br/> Thanks";
		String subject = "Rfa Accepted";
		constructEmail(appUrl + "/requestForApproval/viewRequest?id=" + req.getId() + "&user=approver", req,
				groupservice.findGroupbyName("Approver").getUserdetails().get(0), htmlMsg, subject,
				(Workspace) session.getAttribute("workspace"));
		return "redirect:/requestForApproval/viewallrequestsforcostcenter";
	}

	@RequestMapping(value = "/viewallrequestsforapprover", method = RequestMethod.GET)
	public String rfaforApprover(Model model, HttpSession session) {
		if (session.getAttribute("workspace") == null)
			return "redirect:/dashboard/";
		List<RequestForApproval> req = reqservice.getRfaListForApprover((Workspace) session.getAttribute("workspace"));
		model.addAttribute("req", req);
		return "rfaApprover";
	}

	@RequestMapping(value = "/updaterequestforapprover", method = RequestMethod.POST)
	public String updaterfaforApprover(@RequestParam("approversigniture") MultipartFile signiture,
			@RequestParam("id") Long id, HttpServletRequest request, HttpSession session, RedirectAttributes att) {
		String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		RequestForApproval req = reqservice.getOne(id);

		if (request.getParameter("value").equals("Reject")) {
			req.setReqStatus("Rejected By Approver");
			req.setAppStatus("Rejected");
			req.setApproverDate(new Date());
			req.setApprover((UserDetail) session.getAttribute("user"));
			reqservice.merge(req);
			String htmlMsg = "Hello Sir/Madam , Rfa with <br/> Reference No. " + req.getRefnumber() + ", Job Site: "
					+ req.getJobSite() + ", <br/> Total Price Quoted: " + req.getTotalPriceQuoted()
					+ "has been Rejected By Approver" + " <br/><br/> Thanks";
			String subject = "Rfa Rejected";
			constructEmail(null, req, req.getRequestor(), htmlMsg, subject,
					(Workspace) session.getAttribute("workspace"));
			 constructEmail(null,req,req.getCostcenter(),htmlMsg,subject,(Workspace)session.getAttribute("workspace"));
			return "redirect:/requestForApproval/viewallrequestsforapprover";
		}
		if (!signiture.isEmpty()) {
			String imageName = String.valueOf(System.currentTimeMillis()) + "."
					+ FilenameUtils.getExtension(signiture.getOriginalFilename());
			if (uploadsigniture(signiture, att, imageName))
				return "redirect:/requestForApproval/viewRequest?id=" + id + "&user=" + "approver";

			req.setApproverSig(imageName);
		} else {
			att.addFlashAttribute("fail", "Please Upload Your Signiture");
			return "redirect:/requestForApproval/viewRequest?id=" + id + "&user=" + "approver";
		}
		req.setApproverDate(new Date());
		req.setReqStatus("Pending At PO");
		req.setAppStatus("Accepted");
		req.setFinStatus("Pending");
		req.setApprover((UserDetail) session.getAttribute("user"));
		reqservice.merge(req);
		String htmlMsg = "Hello Sir/Madam ,A new Rfa with <br/> Reference No. " + req.getRefnumber() + ", Job Site: "
				+ req.getJobSite() + ", <br/> Total Price Quoted: " + req.getTotalPriceQuoted()
				+ "has been Approved By Approver"
				+ " <br/> This email needs an immediate action.Please Click on the Link, <br/> Thanks";
		String subject = "Rfa Accepted";
		constructEmail(appUrl + "/requestForApproval/viewRequest?id=" + req.getId() + "&user=PO", req,
				groupservice.findGroupbyName("PO").getUserdetails().get(0), htmlMsg, subject,
				(Workspace) session.getAttribute("workspace"));
		return "redirect:/requestForApproval/viewallrequestsforapprover";
	}

	@RequestMapping(value = "/viewallrequestsforpo", method = RequestMethod.GET)
	public String rfaforPO(Model model, HttpSession session) {
		if (session.getAttribute("workspace") == null)
			return "redirect:/dashboard/";
		List<RequestForApproval> req = reqservice.getRfaListforPo((Workspace) session.getAttribute("workspace"));
		model.addAttribute("req", req);
		return "rfaPO";
	}

	@RequestMapping(value = "/updaterequestforpo", method = RequestMethod.POST)
	public String updaterfaforPO(@RequestParam("porsigniture") MultipartFile signiture, @RequestParam("id") Long id,
			HttpServletRequest request, HttpSession session, RedirectAttributes att) {
		String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		RequestForApproval req = reqservice.getOne(id);

		if (request.getParameter("value").equals("Reject")) {
			req.setReqStatus("Rejected By PO");
			req.setFinStatus("Rejected");
			req.setFinancerDate(new Date());
			req.setFinaceapprover((UserDetail) session.getAttribute("user"));
			reqservice.merge(req);
			String htmlMsg = "Hello Sir/Madam , Rfa with <br/> Reference No. " + req.getRefnumber() + ", Job Site: "
					+ req.getJobSite() + ", <br/> Total Price Quoted: " + req.getTotalPriceQuoted()
					+ "has been Rejected By PO" + " <br/><br/> Thanks";
			String subject = "Rfa Rejected";
			constructEmail(null, req, req.getRequestor(), htmlMsg, subject,
					(Workspace) session.getAttribute("workspace"));
			constructEmail(null, req, req.getCostcenter(), htmlMsg, subject,
					(Workspace) session.getAttribute("workspace"));
			constructEmail(null, req, req.getApprover(), htmlMsg, subject,
					(Workspace) session.getAttribute("workspace"));
			return "redirect:/requestForApproval/viewallrequestsforpo";
		}
		if (!signiture.isEmpty()) {
			String imageName = String.valueOf(System.currentTimeMillis()) + "."
					+ FilenameUtils.getExtension(signiture.getOriginalFilename());
			if (uploadsigniture(signiture, att, imageName))
				return "redirect:/requestForApproval/viewRequest?id=" + id + "&user=" + "PO";

			req.setFinancerSig(imageName);
		} else {
			att.addFlashAttribute("fail", "Please Upload Your Signiture");
			return "redirect:/requestForApproval/viewRequest?id=" + id + "&user=" + "PO";
		}
		req.setFinancerDate(new Date());
		req.setReqStatus("Pending At Admin");
		req.setFinStatus("Accepted");
		req.setAdStatus("Pending");
		req.setFinaceapprover((UserDetail) session.getAttribute("user"));
		reqservice.merge(req);
		String htmlMsg = "Hello Sir/Madam ,A new Rfa with <br/> Reference No. " + req.getRefnumber() + ", Job Site: "
				+ req.getJobSite() + ", <br/> Total Price Quoted: " + req.getTotalPriceQuoted()
				+ "has been Approved By PO"
				+ " <br/> This email needs an immediate action.Please Click on the Link, <br/> Thanks";
		String subject = "Rfa Accepted";
		constructEmail(appUrl + "/requestForApproval/viewRequest?id=" + req.getId() + "&user=Admin", req,
				groupservice.findGroupbyName("Admin").getUserdetails().get(0), htmlMsg, subject,
				(Workspace) session.getAttribute("workspace"));
		return "redirect:/requestForApproval/viewallrequestsforpo";
	}

	@RequestMapping(value = "/viewallrequestsforadmin", method = RequestMethod.GET)
	public String rfaforAdmin(Model model, HttpSession session) {
		if (session.getAttribute("workspace") == null)
			return "redirect:/dashboard/";
		List<RequestForApproval> req = reqservice.getRfaListForAdmin((Workspace) session.getAttribute("workspace"));
		model.addAttribute("req", req);
		return "rfaAdmin";
	}

	@RequestMapping(value = "/viewRequestforAdmin", method = RequestMethod.GET)
	public String viewRequestforadmin(@RequestParam("id") long id, @RequestParam("user") String user, Model model) {
		RequestForApproval req = reqservice.getOne(id);
		model.addAttribute("req", req);
		model.addAttribute("id", id);
		return "viewrfaAdmin";
	}

	@RequestMapping(value = "/updatefraforAdmin", method = RequestMethod.POST)
	public String updateRfaForAdmin(@RequestParam("comments") String comments, @RequestParam("id") Long id,
			@RequestParam("adminsigniture") MultipartFile signiture, Model model, RedirectAttributes att) {
		RequestForApproval req = reqservice.getOne(id);
		if (!signiture.isEmpty()) {
			String imageName = String.valueOf(System.currentTimeMillis()) + "."
					+ FilenameUtils.getExtension(signiture.getOriginalFilename());
			if (uploadsigniture(signiture, att, imageName))
				return "redirect:/requestForApproval/viewRequestforAdmin?id=" + id + "&user=" + "Admin";

			req.setAdminSig(imageName);
		} else {
			att.addFlashAttribute("fail", "Please Upload Your Signiture");
			return "redirect:/requestForApproval/viewRequestforAdmin?id=" + id + "&user=" + "Admin";
		}

		req.setAdminComments(comments);
		req.setAdminDate(new Date());
		req.setReqStatus("Approved");
		req.setAdStatus("Approved");
		reqservice.merge(req);
		return "redirect:/requestForApproval/viewallrequestsforadmin";
	}

	@RequestMapping(value = "/viewRequest", method = RequestMethod.GET)
	public String viewRequest(@RequestParam("id") long id, @RequestParam(value = "user", required = false) String user,
			Model model) {
		model.addAttribute("user", user);
		RequestForApproval req = reqservice.getOne(id);
		model.addAttribute("req", req);
		model.addAttribute("id", id);
		return "viewrfa";
	}

	@RequestMapping(value = "/listquotation", method = RequestMethod.GET)
	public String listQuotation(Model model, HttpSession session) {
		model.addAttribute("searchquotation", new QuotationSearch());
		return "quotationlist";
	}

	@RequestMapping(value = "/listquotation", method = RequestMethod.POST)
	public String listQuotation(QuotationSearch search, @RequestParam(value = "page", defaultValue = "1") int page,
			Model model, HttpSession session, RedirectAttributes att) {
		session.setAttribute("search", search);
		return "redirect:/requestForApproval/pageno=" + page;
	}

	@RequestMapping(value = "/pageno={page}", method = RequestMethod.GET)
	public String paginate(@PathVariable("page") int page, Model model, HttpSession session) {

		Workspace w = (Workspace) session.getAttribute("workspace");
		pagination(page, model, w, session);
		model.addAttribute("searchquotation", new QuotationSearch());
		return "quotationlist";
	}

	public void pagination(int page, Model model, Workspace w, HttpSession session) {

		int from = 0;
		int total = 0;
		final int ROWS = Constants.ROWS;
		Long records = 0L;
		from = ROWS * (page - 1);
		page = (page > 0) ? page : 1;
		QuotationSearch search = (QuotationSearch) session.getAttribute("search");
		search.setWorkspace(w);
		search.setFromPage(from);
		records = (long) quotationservice.countSearchPage(search);
		total = (int) Math.ceil((double) records / (double) ROWS);
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("listofQuotation", quotationservice.getSearchPage(search));

	}

	@RequestMapping("/download")
	public void download(@RequestParam("id") Long id, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Quotations q = quotservice.findbyid(id);
		File file = new File(q.getFilePath());
		InputStream is = new FileInputStream(Constants.PATH + file);

		// MIME type of the file
		response.setContentType("application/pdf");
		// Response header
		response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
		// Read from the file and write into the response
		OutputStream os = response.getOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		while ((len = is.read(buffer)) != -1) {
			os.write(buffer, 0, len);
		}
		os.flush();
		os.close();
		is.close();

	}

	@RequestMapping("/downloadPodocument")
	public void downloadPodocument(@RequestParam("id") Long id, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		RequestForApproval req = reqservice.getOne(id);
		File file = new File(req.getPoDocument());
		InputStream is = new FileInputStream(Constants.PATH + file);

		// MIME type of the file
		response.setContentType("application/pdf");
		// Response header
		response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
		// Read from the file and write into the response
		OutputStream os = response.getOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		while ((len = is.read(buffer)) != -1) {
			os.write(buffer, 0, len);
		}
		os.flush();
		os.close();
		is.close();

	}

	@RequestMapping(value = "/getsigniture", method = RequestMethod.GET)
	public void getUserImage(@RequestParam("imageName") String imageName, Model model, HttpServletRequest req,
			HttpServletResponse rep) {

		try {
			System.out.println("i m in getsig-----------------------------------------------");
			// MechanicalEquipment e = mechanicalEquipmentService.find(id);
			System.out.println(
					"imgeName=---------------------------------------------------------------------------" + imageName);
			InputStream is = new FileInputStream(Constants.PATH + imageName);

			byte[] bytes = IOUtils.toByteArray(is);
			if (imageName.contains(".pdf"))
				rep.setContentType("application/pdf");
			else if (imageName.contains(".png"))
				rep.setContentType("image/png");
			else if (imageName.contains(".gif"))
				rep.setContentType("image/gif");
			else if (imageName.contains(".jpg"))
				rep.setContentType("image/jpg");
			else
				rep.setContentType("image/jpeg");
			OutputStream os = rep.getOutputStream();
			os.write(bytes);
			os.close();
			is.close();

		} catch (Exception e) {// e.printStackTrace();
			System.out.println("Image " + imageName + " not present");
		}
	}

	public boolean uploaddoc(MultipartFile pimage, RedirectAttributes redirectAttributes, String imageName, int i) {
		if (!pimage.isEmpty()) {
			if (!new File(Constants.PATH).exists())
				;
			new File(Constants.PATH).mkdirs();

			if (!checkdocExtension(pimage.getContentType())) {
				redirectAttributes.addFlashAttribute("fail", "Allowed  Type (pdf) for Quotation " + (i + 1));
				return true;
			}
		}
		try {
			pimage.transferTo(new File(Constants.PATH + imageName));

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("fail", "Document Cannot be uploaded for Quotaion " + (i + 1));
			return true;
		}
		return false;
	}

	public Boolean checkdocExtension(String extension) {
		if (extension.equals("application/pdf"))
			return true;
		else
			return false;
	}

	public boolean uploadsigniture(MultipartFile pimage, RedirectAttributes redirectAttributes, String imageName) {
		if (!pimage.isEmpty()) {
			if (!new File(Constants.PATH).exists())
				;
			new File(Constants.PATH).mkdirs();

			if (!checkExtension(pimage.getContentType())) {
				redirectAttributes.addFlashAttribute("fail", "Allowed Image Type (png/jpg,jpeg/gif)");
				return true;
			}
		}
		try {
			pimage.transferTo(new File(Constants.PATH + imageName));

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("fail", "Image Cannot be uploaded");
			return true;
		}
		return false;
	}

	public boolean uploadpodocument(MultipartFile pimage, RedirectAttributes redirectAttributes, String imageName) {
		if (!pimage.isEmpty()) {
			if (!new File(Constants.PATH).exists())
				;
			new File(Constants.PATH).mkdirs();

			if (!checkdocExtension(pimage.getContentType())) {
				redirectAttributes.addFlashAttribute("fail", "Allowed Document Type (pdf)");
				return true;
			}
		}
		try {
			pimage.transferTo(new File(Constants.PATH + imageName));

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("fail", "Document Cannot be uploaded");
			return true;
		}
		return false;
	}

	public Boolean checkExtension(String extension) {
		if (extension.equals("image/gif"))
			return true;
		else if (extension.equals("image/jpg"))
			return true;
		else if (extension.equals("image/jpeg"))
			return true;
		else if (extension.equals("image/png"))
			return true;
		else
			return false;
	}

	private void constructEmail(String confirmationUrl, RequestForApproval rfa, UserDetail user, String message,
			String subject, Workspace w) {

		MimeMessage msg = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg, false, "utf-8");
			if (confirmationUrl != null)
				msg.setContent(message + " " + confirmationUrl, "text/html");
			else
				msg.setContent(message, "text/html");
			helper.setTo(user.getEmail());
			helper.setSubject(subject);
			helper.setFrom(w.getWorkspaceId().split("-")[1] + "<info@stie.com.sg>");
			mailSender.send(msg);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
