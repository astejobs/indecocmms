package com.aste.lsme.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.FaultReport;
import com.aste.lsme.domain.InventoryPartSearch;
import com.aste.lsme.domain.PartBatch;
import com.aste.lsme.domain.PartTransactionSearch;
import com.aste.lsme.domain.PartTransaction;
import com.aste.lsme.domain.PartTransferSearch;
import com.aste.lsme.domain.PartTransfer;
import com.aste.lsme.domain.PartsInWarehouse;
import com.aste.lsme.domain.Task;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.domain.Warehouse;
import com.aste.lsme.service.FaultReportService;
import com.aste.lsme.service.ManufacturerService;
import com.aste.lsme.service.PMTaskService;
import com.aste.lsme.service.PartBatchService;
import com.aste.lsme.service.PartTransactionService;
import com.aste.lsme.service.PartTransferService;
import com.aste.lsme.service.PartsInWarehouseService;
import com.aste.lsme.service.VendorService;
import com.aste.lsme.service.WarehouseService;


@Controller
@RequestMapping({"/inventory","/inventory/reports"})
public class InventoryController {
	int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS;
	Long records = 0L;
	
	@Autowired
	PartTransactionService partTransactionService;
	@Autowired
	PartsInWarehouseService partsInWarehouseService;
	@Autowired
	PartBatchService partBatchService;
	@Autowired
	WarehouseService warehouseService;
	@Autowired
	PartTransferService partTransferService;
	@Autowired
	PMTaskService pmTaskService;
	@Autowired
	FaultReportService faultReportService;
	
	@Autowired
	VendorService vendorService;
	
	@Autowired
	ManufacturerService manufacturerService;
	
	
	@RequestMapping(value="/reserve",method=RequestMethod.POST)
	public String reserve(@Valid @ModelAttribute("partTransaction") PartTransaction partTransaction,BindingResult result,
									RedirectAttributes redirectAttributes,HttpSession session){
		
		UserDetail userDetail = (UserDetail) session.getAttribute("user");
		if(result.hasErrors()){
			redirectAttributes.addFlashAttribute("partTransaction", partTransaction);
			redirectAttributes.addFlashAttribute("result",result);
			redirectAttributes.addFlashAttribute("fail","Please enter Reserve Quantity");
			if(partTransaction.getReportTaskId().contains("PMTASK"))
				return "redirect:/task/"+partTransaction.getReportTaskId()+"/parts";
			return "redirect:/faultReport/"+partTransaction.getReportTaskId()+"/parts";	
		}
				
		partTransaction.setPendingQuantity(partTransaction.getReservedQuantity());
		partTransaction.setReservedBy(userDetail);
		partTransaction.setReservedDate(new Date());
		partTransaction.setStatus(Constants.RESERVED);
		
		partTransactionService.persist(partTransaction);
		if(partTransaction.getReportTaskId().contains("PMTASK"))
			return "redirect:/task/"+partTransaction.getReportTaskId()+"/parts";
		return "redirect:/faultReport/"+partTransaction.getReportTaskId()+"/parts";
	}
	
	@RequestMapping(value="/unreserve/{frId}/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable("frId") String frId,@PathVariable("id") long id,RedirectAttributes redirectAttributes){
		
		partTransactionService.delete(id);
		redirectAttributes.addFlashAttribute("success","Part unreserved successfully");
		if(frId.contains("PMTASK"))
			return "redirect:/task/"+frId+"/parts";
		return "redirect:/faultReport/"+frId+"/parts";
	}
	
	@RequestMapping(value="/issue",method=RequestMethod.GET)
	public String issue(Model model,HttpSession session){
		
		UserDetail userDetail = (UserDetail) session.getAttribute("user");
		List<Warehouse> warehouses = warehouseService.getWarehousesOfStorekeeper(userDetail);
		
		if(!warehouses.isEmpty())
		  model.addAttribute("reservedList",partTransactionService.getAllReservedParts(warehouses));
		
		return "partissue";
	}
	
	@RequestMapping(value="/issue",method=RequestMethod.POST)
	public String issuePost(@ModelAttribute("partTransaction") PartTransaction partTransaction,@RequestParam("batch") PartBatch partBatch,Model model,
										HttpSession session,RedirectAttributes redirectAttributes){
		
		UserDetail userDetail = (UserDetail) session.getAttribute("user");

		PartTransaction pTransaction = partTransactionService.get(partTransaction.getId());
		
		if(partTransaction.getIssuedQuantity()>pTransaction.getReservedQuantity() || partTransaction.getIssuedQuantity() > partBatch.getQuantity()){
			redirectAttributes.addFlashAttribute("fail","Issue appropriate quantity");
			return "redirect:/inventory/issue";
		}
		pTransaction.setIssuedDate(new Date());
		pTransaction.setIssuedBy(userDetail);
		pTransaction.setBatchId(partBatch.getId());
		
		PartsInWarehouse partsInWarehouse = partBatch.getPartsInWarehouse();
		partsInWarehouse.setTotalPartQuantity(partsInWarehouse.getTotalPartQuantity()-partTransaction.getIssuedQuantity());
		partBatch.setQuantity(partBatch.getQuantity()-partTransaction.getIssuedQuantity());
		partsInWarehouse.getPartBatchs().add(partBatch);

		
		if(!pTransaction.isPartialIssue())
		{
			pTransaction.setIssuedQuantity(partTransaction.getIssuedQuantity());
			pTransaction.setPendingQuantity(pTransaction.getReservedQuantity()-pTransaction.getIssuedQuantity());
			
			if(pTransaction.getReservedQuantity() == pTransaction.getIssuedQuantity())
				pTransaction.setStatus(Constants.ISSUED);
		
			if(pTransaction.getReservedQuantity()>pTransaction.getIssuedQuantity())
			{
				pTransaction.setPendingQuantity(pTransaction.getReservedQuantity()-pTransaction.getIssuedQuantity());
				pTransaction.setStatus(Constants.PARTIAL_ISSUE);
				pTransaction.setPartialIssue(true);
			}	
		}
		else{
			
			pTransaction.setIssuedQuantity(pTransaction.getIssuedQuantity()+partTransaction.getIssuedQuantity());
			pTransaction.setPendingQuantity(pTransaction.getReservedQuantity()-pTransaction.getIssuedQuantity());
				if(pTransaction.getReservedQuantity() == pTransaction.getIssuedQuantity())
				{
					pTransaction.setStatus(Constants.ISSUED);
					pTransaction.setPartialIssue(false);
				}
				if(pTransaction.getReservedQuantity()>pTransaction.getIssuedQuantity())
				{
					pTransaction.setPendingQuantity(pTransaction.getReservedQuantity()-pTransaction.getIssuedQuantity());
					pTransaction.setStatus(Constants.PARTIAL_ISSUE);
					pTransaction.setPartialIssue(true);
				}	
			}

	     pTransaction.setTransactionCost(pTransaction.getIssuedQuantity()*partBatch.getUnitCost());
	     if(pTransaction.getReportTaskId().contains("PMTASK")){
	    	Task task = pmTaskService.findByTaskId(pTransaction.getReportTaskId());
	    	task.setTotalCost(pTransaction.getTransactionCost());
	    	pmTaskService.update(task);
	     }	 
	     else{
	    	 
	    	FaultReport faultReport = faultReportService.get(pTransaction.getReportTaskId());
	    	faultReport.setTotalPartCost(pTransaction.getTransactionCost());
	    	faultReportService.update(faultReport);
	     }
		 partsInWarehouseService.update(partsInWarehouse);
		 partTransactionService.update(pTransaction);
		
		return "redirect:/inventory/issue";
	}
	
	@RequestMapping(value="/recieve/{frId}/{id}",method=RequestMethod.GET)
	public String  recieve(@PathVariable("frId") String frId,@PathVariable("id") PartTransaction partTransaction,HttpSession session) {
		
		UserDetail userDetail = (UserDetail) session.getAttribute("user");

		partTransaction.setRecievedBy(userDetail);
		partTransaction.setRecievedDate(new Date());
		partTransaction.setStatus(Constants.RECEIVED);
		
		partTransactionService.update(partTransaction);
		if(frId.contains("PMTASK"))
			return "redirect:/task/"+frId+"/parts";
		return "redirect:/faultReport/"+frId+"/parts";
	}
	
	@RequestMapping(value="/return",method=RequestMethod.POST)
	public String returnPart(@ModelAttribute("partTransaction") PartTransaction partTransaction,HttpSession session){
		
		UserDetail userDetail = (UserDetail) session.getAttribute("user");
		
		PartTransaction pTransaction = partTransactionService.get(partTransaction.getId());
		pTransaction.setReturnedQuantity(partTransaction.getReturnedQuantity());
		pTransaction.setReturnedDate(new Date());
		pTransaction.setReturnedBy(userDetail);
		pTransaction.setPartialIssue(false);
		pTransaction.setStatus(Constants.RETURNED);
		
		partTransactionService.update(pTransaction);
		
		if(pTransaction.getReportTaskId().contains("PMTASK"))
			return "redirect:/task/"+pTransaction.getReportTaskId()+"/parts";
		return "redirect:/faultReport/"+pTransaction.getReportTaskId()+"/parts";	}
	
	@RequestMapping(value="/recieve",method=RequestMethod.GET)
	public String recieveReturn(Model model,HttpSession session) {
		UserDetail userDetail = (UserDetail) session.getAttribute("user");
		List<Warehouse> warehouses = warehouseService.getWarehousesOfStorekeeper(userDetail);	
		if(!warehouses.isEmpty())
			model.addAttribute("returnedList", partTransactionService.getAllReturnedParts(warehouses));
		return "partrecieve";
	}
	
	@RequestMapping(value="/recieve/{id}",method=RequestMethod.GET)
	public String recieve(@PathVariable("id") PartTransaction partTransaction,HttpSession session){
		
		UserDetail userDetail = (UserDetail) session.getAttribute("user");

		PartTransaction pTransaction = partTransactionService.get(partTransaction.getId());
		pTransaction.setReturnRecievedBy(userDetail);
		pTransaction.setReturnRecievedDate(new Date());
		pTransaction.setStatus(Constants.COMPLETED);
		
		
		PartBatch partBatch = partBatchService.get(partTransaction.getBatchId());
		partBatch.setQuantity(partBatch.getQuantity()+pTransaction.getReturnedQuantity());
		
		
		PartsInWarehouse partsInWarehouse = partsInWarehouseService.get(partBatch.getPartsInWarehouse().getId());
		partsInWarehouse.setTotalPartQuantity(partsInWarehouse.getTotalPartQuantity()+pTransaction.getReturnedQuantity());
		pTransaction.setTransactionCost(pTransaction.getTransactionCost()-(pTransaction.getReturnedQuantity()*partBatch.getUnitCost()));
		if(pTransaction.getReportTaskId().contains("PMTASK")){
	    	Task task = pmTaskService.findByTaskId(pTransaction.getReportTaskId());
	    	task.setTotalCost(pTransaction.getTransactionCost());
	    	pmTaskService.update(task);
	     }	 
	     else{
	    	FaultReport faultReport = faultReportService.get(pTransaction.getReportTaskId());
	    	faultReport.setTotalPartCost(pTransaction.getTransactionCost());
	    	faultReportService.update(faultReport);
	     }
		
		partTransactionService.update(pTransaction);
		partBatchService.update(partBatch);
		partsInWarehouseService.update(partsInWarehouse);
		
		return "redirect:/inventory/recieve";
	}
	
	@RequestMapping(value="/parttransaction/{id}",method=RequestMethod.GET)
	@ResponseBody
	public List<PartBatch> batches(@PathVariable("id") PartTransaction partTransaction)
	{
		
		PartsInWarehouse partsInWarehouse = partsInWarehouseService.get(partTransaction.getPartsInWarehouse().getId());
		return partsInWarehouse.getPartBatchs();
	} 
	
	
	/*********************Part Transfer between Warehouses *************************************/
	@RequestMapping(value="/transfer",method=RequestMethod.GET)
	public String transfer(Model model,HttpSession session){
		
		UserDetail userDetail = (UserDetail) session.getAttribute("user");
		List<Warehouse> warehouses = warehouseService.getWarehousesOfStorekeeper(userDetail);
		if(!warehouses.isEmpty()){
			model.addAttribute("reservedList",partTransferService.getReservedParts(warehouses));
			model.addAttribute("issueList", partTransferService.getIssuingParts(warehouses));
			model.addAttribute("recievedList", partTransferService.getRecievedParts(warehouses));
			model.addAttribute("warehouseList", warehouseService.getWarehousesOfStorekeeper(userDetail));
		}
		return "parttransfer";
	}
	
	@RequestMapping(value="/parts/{id}",method=RequestMethod.GET)
	@ResponseBody
	public List<PartsInWarehouse> partList(@PathVariable("id") long id){
	   return partsInWarehouseService.getPartsNotInWarehouse(id);
	}
	
	@RequestMapping(value="/transfer/reserve",method=RequestMethod.POST)
	public String transferReserve(@ModelAttribute("partTransfer") PartTransfer partTransfer,Model model,HttpSession session,RedirectAttributes redirectAttributes){
		
		UserDetail userDetail = (UserDetail) session.getAttribute("user");

		if(partTransfer.getReservedQuantity()>partTransfer.getPartsInWarehouse().getTotalPartQuantity()){
			redirectAttributes.addFlashAttribute("fail","Reserved Quantity cannot be more than Available Quantity");
			return "redirect:/inventory/transfer";		
		}
		
		partTransfer.setIssuer(partTransfer.getPartsInWarehouse().getWarehouse());
		partTransfer.setReservedBy(userDetail);
		partTransfer.setReservedDate(new Date());
		partTransfer.setStatus(Constants.RESERVED);
		
		partTransferService.persist(partTransfer);
		
		return "redirect:/inventory/transfer";
		
	}
	
	@RequestMapping(value="/transfer/issue/{id}",method=RequestMethod.GET)
	public String transferIssue(@PathVariable("id") PartTransfer partTransfer,Model model){
		
		model.addAttribute("partTransfer",partTransfer);
		model.addAttribute("batches",partTransfer.getPartsInWarehouse().getPartBatchs());
		return "parttransferissue";
	}
	
	@RequestMapping(value="/transfer/issue/{id}",method=RequestMethod.POST)
	public String transferIssuePost(PartTransfer partTransfer,@PathVariable("id") PartTransfer pTransfer,
												RedirectAttributes redirectAttributes,HttpSession session){
		
		UserDetail userDetail = (UserDetail) session.getAttribute("user");
		PartBatch partBatch = partBatchService.get(partTransfer.getBatchId());
		
		if(partBatch.getQuantity()<partTransfer.getIssuedQuantity()){
			redirectAttributes.addFlashAttribute("fail","Issued Quantity cannot be more than Available Quantity");
			return "redirect:/transfer/issue/"+pTransfer.getId();
		}
		
		PartsInWarehouse partsInWarehouse = partsInWarehouseService.findPartInWarehouse(pTransfer.getPartsInWarehouse().getPart(), pTransfer.getRequestor());
		
		if(partsInWarehouse != null ){
			PartBatch partBatch2 = new PartBatch();
			partBatch2.setBatchNo(partTransfer.getBatchNo());
			partBatch2.setPartsInWarehouse(partsInWarehouse);
			if(partsInWarehouseService.checkBatchNo(partBatch2)){
				redirectAttributes.addFlashAttribute("fail","Batch No already exists");
				return "redirect:/inventory/transfer/issue"+pTransfer.getId();
			}
		}
			
		pTransfer.setBatchId(partTransfer.getBatchId());
		pTransfer.setBatchNo(partTransfer.getBatchNo());
		pTransfer.setIssuedQuantity(partTransfer.getIssuedQuantity());
		pTransfer.setIssuedDate(new Date());
		pTransfer.setIssuedBy(userDetail);
		pTransfer.setStatus(Constants.ISSUED);

		partBatch.setQuantity(partBatch.getQuantity()-pTransfer.getIssuedQuantity());
		
		PartsInWarehouse partsInWarehouse2 = partBatch.getPartsInWarehouse();
		partsInWarehouse2.setTotalPartQuantity(partsInWarehouse2.getTotalPartQuantity()-pTransfer.getIssuedQuantity());
		
		partBatchService.update(partBatch);
		partsInWarehouseService.update(partsInWarehouse2);
		partTransferService.update(pTransfer);
		
		redirectAttributes.addFlashAttribute("success","Part issued successfully");
		
		return "redirect:/inventory/transfer";
	}
	
	@RequestMapping(value="/transfer/recieve/{id}",method=RequestMethod.GET)
	public String transferRecieve(@PathVariable("id") PartTransfer partTransfer,HttpSession session,RedirectAttributes redirectAttributes){
	
		UserDetail userDetail = (UserDetail) session.getAttribute("user");
		
		PartBatch partBatch = new PartBatch();
		partBatch.setBatchNo(partTransfer.getBatchNo());
		partBatch.setQuantity(partTransfer.getIssuedQuantity());
		partBatch.setUnitCost(partBatchService.get(partTransfer.getBatchId()).getUnitCost());
		PartsInWarehouse partsInWarehouse = partsInWarehouseService.findPartInWarehouse(partTransfer.getPartsInWarehouse().getPart(), partTransfer.getRequestor());
		if(!(partsInWarehouse == null)){
			partsInWarehouse.getPartBatchs().add(partBatch);
			partsInWarehouse.setTotalPartQuantity(partsInWarehouse.getTotalPartQuantity()+partBatch.getQuantity());
		}
		else{
			partsInWarehouse = new PartsInWarehouse();
			partsInWarehouse.setPart(partTransfer.getPartsInWarehouse().getPart());
			partsInWarehouse.setWarehouse(partTransfer.getRequestor());
			partsInWarehouse.getPartBatchs().add(partBatch);
			partsInWarehouse.setTotalPartQuantity(partBatch.getQuantity());
			
		}
		
		partBatch.setPartsInWarehouse(partsInWarehouse);
		partTransfer.setRecievedBy(userDetail);
		partTransfer.setRecievedDate(new Date());
		partTransfer.setStatus(Constants.COMPLETED);
		
		partsInWarehouseService.update(partsInWarehouse);
		partTransferService.update(partTransfer);
		
		redirectAttributes.addFlashAttribute("success","Part Recieved successfully");
		
		return "redirect:/inventory/transfer"; 
		
	}
	
	
	/*---------------------------Standard Inventory Reporting--------------------------------------------------*/

	@RequestMapping(method=RequestMethod.GET)
	public String StandardInventoryReport(){
			return "reportinventory";	
}
	
	
	@RequestMapping(value="/consumption" ,method=RequestMethod.GET)
	public String ConsumptionReport(){
			return "reportinventoryconsumption";	
}


@RequestMapping(value="/parts", method=RequestMethod.GET)
public String partsReport(Model model){
	
	model.addAttribute("InventoryPartSearch", new InventoryPartSearch());
	
	model.addAttribute("manufacturer", manufacturerService.getAll());
	model.addAttribute("vendor",vendorService.getAll());
	model.addAttribute("warehouseList",warehouseService.getAll());
	return "reportinventoryparts";	


}

	/********Report for Part Quantity in Warehouse************/


@RequestMapping(value="/parts",method=RequestMethod.POST,params="excel")
public ModelAndView exportToExcel(InventoryPartSearch inPartSearch,ModelMap modelMap)
{
	List<PartsInWarehouse> partsInWarehouse=partsInWarehouseService.searchPartsInWareHouse(inPartSearch);
	
	modelMap.put("datasource",partsInWarehouse);
	modelMap.put("format", "xls");
	return new ModelAndView("Inventory",modelMap);
	
	
}



@RequestMapping(value="/parts",method=RequestMethod.POST,params="search")
public String reportSearch(InventoryPartSearch inPartSearch,Model model)
{
	List<PartsInWarehouse> partsInWarehouse=partsInWarehouseService.searchPartsInWareHouse(inPartSearch);
		
	model.addAttribute("partsInWarehouseList",partsInWarehouse);
    model.addAttribute("InventoryPartSearch", new InventoryPartSearch());
	model.addAttribute("manufacturer", manufacturerService.getAll());
	model.addAttribute("vendor",vendorService.getAll());
	model.addAttribute("warehouseList",warehouseService.getAll());
	return "reportinventoryparts";
}

	/********Report for Part Transaction**************/

	@RequestMapping(value="/parttransaction", method=RequestMethod.GET)
	public String partConsumption(Model model){
			model.addAttribute("partTransactionSearch",new  PartTransactionSearch());
			model.addAttribute("warehouseList", warehouseService.getAll());
			return "reportinventoryparttransaction";	
	}


	@RequestMapping(value = "/parttransaction",params = "_xls", method = { RequestMethod.POST })
	public ModelAndView partQuantityUsedsearch(HttpSession session,PartTransactionSearch partTransactionSearch,Model model,ModelMap modelMap)
{
			List<PartTransaction> partsQty=partTransactionService.search(partTransactionSearch); //search all using Criteria
			for(PartTransaction p:partsQty){
				if(p.getBatchId()!=null)
				  p.setBatchNo(partBatchService.get(p.getBatchId()).getBatchNo());
			}
			modelMap.put("datasource",partsQty);
			modelMap.put("format", "xls");
			return new ModelAndView("PartTransactionReport",modelMap);

}
	@RequestMapping(value="/parttransaction",params="search",  method=RequestMethod.POST)
	public String partConsumptionPost(@ModelAttribute("partTransactionSearch") PartTransactionSearch partTransactionSearch,Model model,HttpSession session){
			session.setAttribute("partsession", partTransactionSearch);
			
			return "redirect:/inventory/reports/pageno=1";	
}


	
	@RequestMapping(value="/pageno={pageno}", method=RequestMethod.GET)
	public String paginate(@PathVariable("pageno") int page,Model model,HttpSession session){
		    PartTransactionSearch partTransactionSearch = (PartTransactionSearch) session.getAttribute("partsession");
			pagination(page, model,partTransactionSearch);
			model.addAttribute("warehouseList", warehouseService.getAll());
			return "reportinventoryparttransaction";	
}

	private void pagination(int page, Model model, PartTransactionSearch partTransactionSearch) {
			page = (page > 0) ? page : 1;
			from = ROWS*(page-1);
			records = (long) partTransactionService.count(partTransactionSearch);
			 if(records==0){
				model.addAttribute("fail", "No record found");
				model.addAttribute("partTransactionSearch", partTransactionSearch) ; 
				model.addAttribute("page", page);
			 } 
			else{
			total = (int) Math.ceil((double) records / (double) ROWS);
			model.addAttribute("total", total);
			model.addAttribute("page", page);
			model.addAttribute("partTransactionSearch", partTransactionSearch) ; 
			List<PartTransaction> partTransaction=partTransactionService.search(from,partTransactionSearch);
			for(PartTransaction p:partTransaction){
				if(p.getBatchId()!=null)
					p.setBatchNo(partBatchService.get(p.getBatchId()).getBatchNo());
			}
			model.addAttribute("partQuantityList",partTransaction); 
			 }
}
	

	
	
	
	/***************Part Transaction Reports*******************/
	@RequestMapping(value="/parttransfer", method=RequestMethod.GET)
	public String partTransactionReport(Model model){
		model.addAttribute("partTransferSearch", new PartTransferSearch());
		model.addAttribute("source",warehouseService.getAll() );
		model.addAttribute("destination", warehouseService.getAll());
		return "reportinventoryparttransfer";
	}
	
	
	
	@RequestMapping(value="/parttransfer",params="search",method=RequestMethod.POST)
	public String partTransactionSearch(@ModelAttribute("partTransferSearch") PartTransferSearch partTransferSearch,Model model,HttpSession session)
	{
		session.setAttribute("partTransferSession", partTransferSearch);

		return "redirect:/inventory/reports/parttransfer/pageno=1";
		
	}
	
	@RequestMapping(value="/parttransfer",params="excel",method=RequestMethod.POST)
	public ModelAndView partTransferReport(@ModelAttribute("partTransferSearch") PartTransferSearch partTransferSearch,Model model,HttpSession session,ModelMap modelMap){
		
		modelMap.addAttribute("datasource",partTransferService.getAll(partTransferSearch));
		modelMap.addAttribute("format", "xls");
		
		return new ModelAndView("PartTransferReport",modelMap);
	}
	
	@RequestMapping(value="/parttransfer/pageno={pageno}", method=RequestMethod.GET)
	public String partTransacPaginate(@PathVariable("pageno") int page,Model model,HttpSession session){
	PartTransferSearch partTransactionSearch = (PartTransferSearch) session.getAttribute("partTransferSession");
	paginationPartTrans(page, model,partTransactionSearch);
	model.addAttribute("source", warehouseService.getAll());
	model.addAttribute("destination", warehouseService.getAll());

	return "reportinventoryparttransfer";	
	}
	
	
	
	private void paginationPartTrans(int page, Model model, PartTransferSearch partTransferSearch) {
		page = (page > 0) ? page : 1;
		from = ROWS*(page-1);
		records = (long) partTransferService.count(partTransferSearch);
		System.out.println(records);
		 if(records==0){
			model.addAttribute("fail", "No record found");
			model.addAttribute("partTransferSearch", partTransferSearch) ; 
			model.addAttribute("page", page);
		 } 
		else{
		total = (int) Math.ceil((double) records / (double) ROWS);
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("partTransferSearch", partTransferSearch) ; 
		List<PartTransfer> partsTransfer=partTransferService.search(from,partTransferSearch);
			for(PartTransfer p:partsTransfer){
				System.err.println(p.getStatus());
			}
		  model.addAttribute("partsTransactionList",partsTransfer); 
		 }
		 
	}
}



