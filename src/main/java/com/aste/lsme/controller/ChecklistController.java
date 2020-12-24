package com.aste.lsme.controller;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.aste.lsme.domain.ChecklistHeader;
import com.aste.lsme.domain.ChecklistSearch;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.AssetSubtypeService;
import com.aste.lsme.service.AssetTypeService;
import com.aste.lsme.service.BuildingService;
import com.aste.lsme.service.ChecklistService;
import com.aste.lsme.service.EquipmentService;
import com.aste.lsme.service.LocationService;

@Controller
@RequestMapping("/checklist")
public class ChecklistController {
	
	
	@Autowired
	BuildingService buildingService;
	
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	AssetTypeService assetTypeService;
	
	@Autowired
	AssetSubtypeService assetSubtypeService;
	
	@Autowired
	ChecklistService checklistservice;
	
	@Autowired
	EquipmentService equipmentService;
	
	
	@RequestMapping("/addchecklist")
	public String setform(Model model) 
	{
		if(!model.containsAttribute("checklistheader"))
		   model.addAttribute("checklistheader", new ChecklistHeader());
		
		if(model.asMap().containsKey("error")){
			model.addAttribute(
					"org.springframework.validation.BindingResult.checklistheader",
					model.asMap().get("error"));
		}
		return "addchecklist";
		
	}
	
	@RequestMapping(value="/addchecklist", method=RequestMethod.POST)
	public String getform(@Valid ChecklistHeader checklistheader,BindingResult result, Model model, RedirectAttributes attr){
	
		if(result.hasErrors()){
			attr.addAttribute("checklistheader", checklistheader);
			attr.addAttribute("error", result);
			return "redirect:/checklist/addchecklist";
		}
		
	    try {
	    	checklistservice.addChecklist(checklistheader);
	    	attr.addFlashAttribute("success","Checklist Saved Successfully");
	    }
	    catch(Exception ex){
	    	attr.addFlashAttribute("fail","Checklist Saved Failed");
	    	
	    }
	    
	    return "redirect:/checklist/addchecklist";
	

    }
	
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public String searchchecklist(Model model)
	{
		model.addAttribute("checklistsearch", new ChecklistSearch());
		return "checklistsearch";
		
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String getsearchedchecklist(ChecklistSearch search, @RequestParam( value="page", defaultValue="1") int page, Model model, HttpSession session)
	{
		session.setAttribute("search", search);
		return "redirect:/checklist/pageno="+page;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String editchecklist(Model model, @PathVariable("id") Long id)
	{
		System.out.println("Klskdlskdls");
		ChecklistHeader chklist = checklistservice.find(id);
		model.addAttribute("checklistheader", chklist);
		model.addAttribute("equipments", chklist.getEquipment());
		return "editchecklist";
		
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String updatechecklist(@Valid ChecklistHeader checklistheader,BindingResult result, Model model, RedirectAttributes attr)
	{
		if(result.hasErrors()){
			attr.addAttribute("checklistheader", checklistheader);
			System.out.println(result+"baseraa");
			attr.addAttribute("error", result);
			return "editchecklist";
		}
		try{
			checklistservice.update(checklistheader);
			attr.addFlashAttribute("success", "Checklist Updated Successfully");
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
			attr.addFlashAttribute("fail", "Checklist Update Failed");
		}
		
		return "redirect:/checklist/edit/"+checklistheader.getId();
		
	}
	
	@RequestMapping(value="/view/{id}",method=RequestMethod.GET)
	public String viewChecklist(@PathVariable("id") Long id, Model model)
	{
		ChecklistHeader chklist = checklistservice.find(id);
		model.addAttribute("checklistheader", chklist);
		
		return"viewchecklist";
	}
	
	@RequestMapping(value="/equipment",method=RequestMethod.GET)
	public String getEquipment(Model model,HttpSession session) {
		Workspace w = (Workspace) session.getAttribute("workspace");
		model.addAttribute("assetTypeList", assetTypeService.getAll());
		model.addAttribute("buildingList",buildingService.getWorkspaceBuildings(w));
		model.addAttribute("equipmentSearch", new EquipmentSearchCriteria());	
		return "checklistequipment";
	}
	
	@RequestMapping(value="/equipment",method=RequestMethod.POST)
	public String frEquipmentSearch(@ModelAttribute("equipmentSearch") EquipmentSearchCriteria equipmentSearch,HttpSession session,
									Model model){
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		equipmentSearch.setWorkspace(w);
		equipmentSearch.setCheckSchedule(true);
		session.setAttribute("equipmentSearch", equipmentSearch);
		int page=1;
		pagination(page, model, equipmentSearch,false,w);
		model.addAttribute("assetTypeList", assetTypeService.getAll());
		model.addAttribute("buildingList",buildingService.getWorkspaceBuildings(w));
		model.addAttribute("equipmentSearch", equipmentSearch);
		if(equipmentSearch.getBuilding()!=null)
			model.addAttribute("locationList",locationService.getLocationList(equipmentSearch.getBuilding().getId()));
		if(equipmentSearch.getAssetType()!=null){
			model.addAttribute("assetSubtypeList",assetSubtypeService.getEquipmentSubTypeList(equipmentSearch.getEquipmentType()));
		}
			return "checklistequipment";
	}
	
	
	@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
	public String paginate(@PathVariable("page") int page,Model model,HttpSession session){
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		pagination(page, model,w,session);
		model.addAttribute("checklistsearch", new ChecklistSearch());
		return "checklistsearch";
	}
	
	public void pagination(int page,Model model,Workspace w, HttpSession session){
		
		int from = 0;
		int total = 0;
		final int ROWS = Constants.ROWS;
		Long records = 0L;
		from = ROWS*(page-1);
		page = (page > 0) ? page : 1;
		ChecklistSearch search = (ChecklistSearch) session.getAttribute("search");
		search.setWorkspace(w);
		search.setFromPage(from);
		records = (long) checklistservice.countSearchPage(search);
		total = (int) Math.ceil((double) records / (double) ROWS);
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("listofChecklist", checklistservice.getSearchPage(search));

	}
	//Pagination
		public void pagination(int page,Model model,Object object,boolean b,Workspace w){
			
			int from = 0;
			int total = 0;
			final int ROWS = Constants.ROWS;
			Long records = 0L;
				
				page = (page > 0) ? page : 1;
				from = ROWS*(page-1);
				records = (long)equipmentService.getEquipmentCount((EquipmentSearchCriteria)object,w);
                total = (int) Math.ceil((double) records / (double) ROWS);
				model.addAttribute("total", total);
				model.addAttribute("page", page);
			    model.addAttribute("equipmentList", equipmentService.getEquipmentPaginated(from,(EquipmentSearchCriteria)object,w));
				
		}
		
		
		@ExceptionHandler
		public void exception(Exception e){
			
			e.printStackTrace();
		}
}
