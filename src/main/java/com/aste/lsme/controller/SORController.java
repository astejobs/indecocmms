package com.aste.lsme.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSpinnerUI;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aste.lsme.domain.AssetSubtype;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.EquipmentBaseline;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Location;
import com.aste.lsme.domain.Mechanical;
import com.aste.lsme.domain.SOR;
import com.aste.lsme.domain.SORSearchCriteria;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.AssetSubtypeService;
import com.aste.lsme.service.AssetTypeService;
import com.aste.lsme.service.EquipmentService;
import com.aste.lsme.service.SORService;

@Controller
@RequestMapping("/SOR")
public class SORController {
	
	
	int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS;
	Long records = 0L;
	
	
	
	
	@Autowired
	EquipmentService EqpService;
	
	@Autowired
	SORService SorService;
	
	
	@Autowired
	AssetTypeService assetService;
	
	@Autowired
	AssetSubtypeService assetsubtypeservice;

	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model,RedirectAttributes redirect,HttpSession session) {
		Workspace w = (Workspace) session.getAttribute("workspace");	
		if (model.asMap().containsKey("result")){
		   	model.addAttribute("org.springframework.validation.BindingResult.SOR",model.asMap().get("result"));
		}
		if(!model.asMap().containsKey("SOR")){	
			model.addAttribute("SOR",new SOR());	
		}	
        model.addAttribute("assetType", EqpService.getEqpTypes(w));
        model.addAttribute("eqpNameList", SorService.getEqpName(((SOR) model.asMap().get("SOR")).getEquipmentType(),w));
		 return "SORCreate";
	}
	

	
	@RequestMapping(method = RequestMethod.POST)
	public String addSOR(@Valid @ModelAttribute("SOR") SOR sor,BindingResult result,RedirectAttributes redirect,HttpSession session,Model m) {
		Workspace w = (Workspace) session.getAttribute("workspace");
		if(result.hasErrors()){		
		    redirect.addFlashAttribute("SOR",sor);
		    redirect.addFlashAttribute("result",result);	    
			return "redirect:/SOR";
		}
		else{
			sor.setWorkspace(w);
				try{
					SorService.save(sor);
					redirect.addFlashAttribute("success"," SOR saved Successfully");
					
					}
				catch (Exception e) {
                  
					redirect.addFlashAttribute("SOR",sor);
					redirect.addFlashAttribute("fail"," SOR already exists");
				}
					
					return "redirect:/SOR";
		}
				
	}
	
	
	@RequestMapping(value="/search",method = RequestMethod.GET)
	public String search (Model model,RedirectAttributes redirect,HttpSession session){
		Workspace w = (Workspace) session.getAttribute("workspace");	
		model.addAttribute("AssetTypeList", EqpService.getEqpTypes(w));
	    model.addAttribute("SORSearch", new SORSearchCriteria());	
		return "SORSearch";		
	}
	
	
	
	
	@RequestMapping(value="/search",method = RequestMethod.POST)
	public String searchpost (@ModelAttribute("SORSearch")SORSearchCriteria SORSearch,Model model,RedirectAttributes redirect,HttpSession session,HttpServletRequest request){
		Workspace w = (Workspace) session.getAttribute("workspace");
		SORSearch.setWorkspace(w);
		
		session.setAttribute("sorsearch", SORSearch);
		return "redirect:/SOR/pageno=1";
		
		
		
	}
	
	
	@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
	public String paginate(@PathVariable("page") int page, Model model,RedirectAttributes redirect,HttpSession session){  
			SORSearchCriteria SOR = (SORSearchCriteria) session.getAttribute("sorsearch");
			Workspace w=(Workspace)session.getAttribute("workspace");		
			pagination(page, model,SOR,w);
			model.addAttribute("AssetTypeList", EqpService.getEqpTypes(w));
			model.addAttribute("eqpNameList", SorService.getEqpName(SOR.getEquipmentType(),w));
			return "SORSearch";
		}
	
	
	public void pagination(int page,Model model,SORSearchCriteria SOR,Workspace w){
		page = (page > 0) ? page : 1;
		from = ROWS*(page-1);
		records = (long) SorService.count(SOR,w);
		total = (int) Math.ceil((double) records / (double) ROWS);
		model.addAttribute("total", total);
		model.addAttribute("page", page);
	    model.addAttribute("SORSearch", SOR) ; 
		model.addAttribute("SORList", SorService.getSearchList(from,SOR,w));
	}

	
	@RequestMapping(value="/delete/{id}")
	public String deleteSOR(@PathVariable("id") Long id,Model model,RedirectAttributes redirectAttributes) {	
		SOR sor=SorService.find(id);
			if(SorService.remove(sor)){		
				redirectAttributes.addFlashAttribute("success", "Deleted Mechanical Equipment Successfully");
			}
			else{
				redirectAttributes.addFlashAttribute("fail", "Deletion Failed");
			}
		return "redirect:/SOR/pageno="+1;	
	}

	
	@RequestMapping(value = "/edit/{id}" , method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id,Model model,RedirectAttributes redirect,HttpSession session) {
		Workspace w=(Workspace)session.getAttribute("workspace"); 
		SOR s = SorService.find(id);
		try{
		if(!model.asMap().containsKey("SOR")){	
			
			model.addAttribute("SOR", SorService.find(id));
			model.addAttribute("EqpList",EqpService.getEqpTypes(w));  
		}
		if(model.asMap().containsKey("result")){
			model.addAttribute("org.springframework.validation.BindingResult.SOR",model.asMap().get("result"));
		}
		//model.addAttribute("AssetType",assetService.getAssetTypeOnSubtypeName(s.getEquipmentType()));
		model.addAttribute("AssetTypeList", EqpService.getEqpTypes(w));
		//model.addAttribute("AssetsubTypeList", assetsubtypeservice.getEquipmentSubSystems(assetService.getAssetTypeOnSubtypeName(s.getEquipmentType()).getAssetTypeName()));
		//model.addAttribute("eqpNameList",EqpService.getEquipmentBySubtype(assetsubtypeservice.getEquipmentSubSystemOnName(s.getEquipmentType()).get(0)));
		model.addAttribute("eqpNameList", SorService.getEqpName(s.getEquipmentType(),w));

		model.addAttribute("edit",true);
		return "SOREdit";	
		}catch (Exception e) {
			e.printStackTrace();
			redirect.addFlashAttribute("fail", "No such record exists");
			return "redirect:/SOR/search";
		}
	}
	
	
	
	@RequestMapping(value = "/update" , method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("SOR") SOR sor,BindingResult result,@RequestParam("id") Long id,
			Model model,RedirectAttributes redirect,HttpSession session) throws IOException {
			if(result.hasErrors()){
				redirect.addFlashAttribute("SOR",sor);
				redirect.addFlashAttribute("result",result);
				return "redirect:/SOR/edit/"+sor.getId();
			}
			Workspace w = (Workspace) session.getAttribute("workspace");		
			sor.setWorkspace(w);  
			
			try{
				SorService.update(sor,w);
				redirect.addFlashAttribute("success","SOR Updated successfully");
				}	
			catch(Exception ex){
				redirect.addFlashAttribute("fail", "SOR already exists");
				redirect.addFlashAttribute("SOR",sor);
			}
		  
			return "redirect:/SOR/edit/"+sor.getId();
		 }
	
	
	@RequestMapping(value="/{value}",method = RequestMethod.GET)
    public @ResponseBody List<Equipment> getEqpName(@PathVariable("value") String assetId,Model model,RedirectAttributes redirect,HttpSession session) {		
	    Workspace w = (Workspace) session.getAttribute("workspace");
		List<Equipment> list=SorService.getEqpName(assetId,w);
		model.addAttribute("eqpNameList", list);
		return list;
    }

	
	@RequestMapping(value="getSOR",method = RequestMethod.GET)
    public @ResponseBody  List<SOR> getSOR(@RequestParam("value") Long[] id,Model model,HttpSession session) {
	   return SorService.find(id) ;
		
    }

	@RequestMapping(value="/assetType/{value}",method = RequestMethod.GET)
    public @ResponseBody List<AssetSubtype> getAssetSubtype(@PathVariable("value") long assetId,Model model,RedirectAttributes redirect,HttpSession session) {
	    Workspace w = (Workspace) session.getAttribute("workspace");
		List<AssetSubtype> list=SorService.getAssetSubtype(assetId);
		return list;
    }
	
	@RequestMapping(value="getallsor",method = RequestMethod.GET)
    public @ResponseBody  List<SOR> getSOR(Model model,HttpSession session) {
		 Workspace w = (Workspace) session.getAttribute("workspace");
		List<SOR> sorList= SorService.getWorkspaceSor(w);
		return sorList ;
		
    }
	
	
}