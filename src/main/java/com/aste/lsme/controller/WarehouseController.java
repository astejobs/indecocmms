package com.aste.lsme.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aste.lsme.domain.Part;
import com.aste.lsme.domain.PartBatch;
import com.aste.lsme.domain.PartsInWarehouse;
import com.aste.lsme.domain.StoreKeeper;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.domain.Warehouse;
import com.aste.lsme.domain.WarehousePartList;
import com.aste.lsme.service.PartService;
import com.aste.lsme.service.PartsInWarehouseService;
import com.aste.lsme.service.UserDetailsServiceInterface;
import com.aste.lsme.service.WarehouseService;

@Controller
@RequestMapping(value="/warehouse")
public class WarehouseController {

	@Autowired
	WarehouseService warehouseService;
	@Autowired
	UserDetailsServiceInterface userService;
	@Autowired
	PartsInWarehouseService partsInWarehouseService;
	@Autowired
	PartService partService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String warehouse(Model model){
		
		model.addAttribute("userList",userService.getNotAdminUsers());

		if(!model.asMap().containsKey("warehouse"))
			model.addAttribute("warehouse",new Warehouse());
		if(model.asMap().containsKey("result"))
			model.addAttribute("org.springframework.validation.BindingResult.warehouse", model.asMap().get("result"));
                    return "warehousecreate";
	}
	
	//Save warehouse
	@RequestMapping(method=RequestMethod.POST)
	public String warehousePost(@Valid @ModelAttribute("warehouse") Warehouse warehouse,BindingResult result,
									@RequestParam(value="users",required=false,defaultValue="") List<UserDetail> userDetailList, RedirectAttributes redirectAttributes,HttpSession session) {
		
		if(result.hasErrors()){
			redirectAttributes.addFlashAttribute("warehouse", warehouse);
			redirectAttributes.addFlashAttribute("result", result);
			redirectAttributes.addFlashAttribute("fail", "Please enter all fields properly");
			return "redirect:/warehouse";
			                 
		}
		userDetailList.addAll(userService.getAdminUsers());
				
				for (UserDetail userDetail:userDetailList) 
				{
					StoreKeeper storeKeeper = new StoreKeeper();
					storeKeeper.setUserDetail(userDetail);
					storeKeeper.setStatus(true);
					storeKeeper.setWarehouse(warehouse);
					warehouse.getStoreKeepers().add(storeKeeper);
				}
		
		if(warehouseService.persist(warehouse))
			redirectAttributes.addFlashAttribute("success","Warehouse added successfully");
		else 
			redirectAttributes.addFlashAttribute("fail", "Warehouse already exists");
		
		return "redirect:/warehouse";
	}
	//Get List of warehouses
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String warehouseSearch(Model model ,HttpSession session )
	{
		
		UserDetail userDetail=(UserDetail) session.getAttribute("user");
		List<Warehouse> warehouses = warehouseService.getWarehousesOfStorekeeper(userDetail);
		model.addAttribute("warehouseList",warehouses);
		return "warehousesearch";
	}
	
	//Get warehouse for updation
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String warehouseGet(@PathVariable("id") long id,Model model) 
	{
		
		Warehouse warehouse = warehouseService.get(id);
		model.addAttribute("warehouse",warehouse );
		List<UserDetail> userDetailsList = userService.getallusers();

		List<UserDetail> storeKeep= warehouseService.getUserDetail(id);
		System.out.println(storeKeep +"present");

		
		userDetailsList.removeAll(storeKeep);
		model.addAttribute("userList",userDetailsList);
		

		return "warehouseedit";
	}
	
	//Update warehouse
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String warehouseUpdate(@Valid @ModelAttribute("warehouse") Warehouse warehouse,BindingResult result,
										@RequestParam(value="users",required=false,defaultValue="") List<UserDetail> userDetailList,RedirectAttributes redirectAttributes) {
		if(result.hasErrors()){
			redirectAttributes.addFlashAttribute("warehouse", warehouse);
			redirectAttributes.addFlashAttribute("result", result);
			redirectAttributes.addFlashAttribute("fail", "Please enter all fields properly");
			return "redirect:/warehouse/update/"+warehouse.getId();
		}
			
			for (UserDetail userDetail:userDetailList) {
				StoreKeeper storeKeeper = new StoreKeeper();
				storeKeeper.setUserDetail(userDetail);
				storeKeeper.setStatus(true);
				storeKeeper.setWarehouse(warehouse);
				warehouse.getStoreKeepers().add(storeKeeper);
			}
		
			if(warehouseService.update(warehouse))
				redirectAttributes.addFlashAttribute("success","Warehouse updated successfully");
			else{
				redirectAttributes.addFlashAttribute("fail", "Warehouse already exists");
				return "redirect:/warehouse/update/"+warehouse.getId();
			}
		
		return "redirect:/warehouse/search";
	}
	
	//Delete warehouse
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String warehouseDelete(@RequestParam(value="id",required=false) Long[] id,RedirectAttributes redirectAttributes) {
		
		if(id != null){
			for (int i = 0; i < id.length; i++){
				try {
					warehouseService.delete(id[i]);
				} catch (Exception e) {
					
					redirectAttributes.addFlashAttribute("fail","Some Warehouse(s) cannot be  deleted ");
					return "redirect:/warehouse";

				}
				redirectAttributes.addFlashAttribute("success","Warehouse(s) deleted successfully");
			}
		}
		else{
				redirectAttributes.addFlashAttribute("fail", "Select Warehouse(s) to delete");
		} 		
		
		return "redirect:/warehouse";
	}
	
	
	//Ajax request for changing status of storekeeper
	@RequestMapping(value="/status/{storeKeeperId}",method=RequestMethod.GET)
	@ResponseBody
	public String changeStatus(@PathVariable("storeKeeperId") StoreKeeper storeKeeper,HttpSession session) {
		
		UserDetail userDetail = (UserDetail) session.getAttribute("user");
		if(userService.getAdminUsers().contains(userDetail)){
			if(!userService.getAdminUsers().contains(storeKeeper)){
			   storeKeeper.setStatus(!storeKeeper.getStatus());
		
			   return String.valueOf(warehouseService.changeStatusStoreKeeper(storeKeeper).getStatus());
		    }	
		}	
		return "unauthorized";
	}
	
	
	
	//Code for View Warehouse List
	@RequestMapping(value="/view",method=RequestMethod.GET)
	public String warehouseView(Model model,HttpSession session) {
		UserDetail userDetail=(UserDetail) session.getAttribute("user");
		
        List<Warehouse> warehouses = warehouseService.getWarehousesOfStorekeeper(userDetail);
		
	
		model.addAttribute("warehouseList", warehouses);
		
		return "warehouseview";
	
	}
	
	
	//View Detailed Warehouse 
	@RequestMapping(value="/view/{id}",method=RequestMethod.GET)
	public String warehouseAddPartsView(@PathVariable("id") Long id,Model model) {
		
		model.addAttribute("warehouse", warehouseService.get(id));
		model.addAttribute("partsInWarehouseList",partsInWarehouseService.getPartsInWarehouse(id));
		List<Part> parts = partService.getAll();
		List<Part> partsInWarehouse=partsInWarehouseService.getParts(id);
		
			parts.remove(partsInWarehouse);
		
		model.addAttribute("warehouseParts",new WarehousePartList());
		model.addAttribute("partList",parts);
		return "warehouseaddparts";
	}
	
	//Add parts to warehouse
	@RequestMapping(value="/addparts",method=RequestMethod.POST)
	public String warehouseAddParts(@ModelAttribute("warehouseParts") WarehousePartList warehousePartList){
		Long id = null;
		for(int i=0;i<warehousePartList.getPartsInWarehouses().size();i++){
			PartsInWarehouse partsInWarehouse  = warehousePartList.getPartsInWarehouses().get(i);
			if(partsInWarehouse.getPart() != null){
				id=partsInWarehouse.getWarehouse().getId();
				partsInWarehouse.getPartBatchs().get(0).setPartsInWarehouse(partsInWarehouse);
				partsInWarehouse.setTotalPartQuantity(partsInWarehouse.getPartBatchs().get(0).getQuantity());
				partsInWarehouseService.persist(partsInWarehouse);
			}	
		}
		
		if(id == null)
			return "redirect:/warehouse/view";
		else
			return "redirect:/warehouse/view/"+id;
	}
	
	//Get Warehouse for adding batch
	@RequestMapping(value="/{id}/batch",method=RequestMethod.GET)
	public String batch(@PathVariable("id") long id,Model model) {
	
		 model.addAttribute("partsInWarehouse",partsInWarehouseService.get(id));
		 
		if(!model.asMap().containsKey("batch"))
			model.addAttribute("batch", new PartBatch());
		if(model.asMap().containsKey("result"))
			model.addAttribute("org.springframework.validation.BindingResult.batch", model.asMap().get("result"));

		return "warehousebatch";
	}
	
	//Adds Batch to warehouse
	@RequestMapping(value="/batch",method=RequestMethod.POST)
	public String addBatch(@Valid @ModelAttribute("batch") PartBatch partBatch,BindingResult result,RedirectAttributes redirectAttributes){

		if(result.hasErrors()){
			redirectAttributes.addFlashAttribute("batch",partBatch);
			redirectAttributes.addFlashAttribute("result",result);
			redirectAttributes.addFlashAttribute("fail","Please enter all fields correctly");
			return "redirect:/warehouse/"+partBatch.getPartsInWarehouse().getId()+"/batch";
		}
		if(partsInWarehouseService.checkBatchNo(partBatch)){
			redirectAttributes.addFlashAttribute("fail","Batch Number already exists");
			return "redirect:/warehouse/"+partBatch.getPartsInWarehouse().getId()+"/batch";
		}
		
		PartsInWarehouse partsInWarehouse = partsInWarehouseService.get(partBatch.getPartsInWarehouse().getId()); 
		partsInWarehouse.setTotalPartQuantity(partsInWarehouse.getTotalPartQuantity()+partBatch.getQuantity());
		partsInWarehouse.getPartBatchs().add(partBatch);
		partsInWarehouseService.update(partsInWarehouse);
		redirectAttributes.addFlashAttribute("success","Batch added Successfully");
		return "redirect:/warehouse/view/"+partsInWarehouse.getWarehouse().getId();
	}
	
}
