package com.aste.lsme.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aste.lsme.domain.AssetSubtype;
import com.aste.lsme.domain.AssetType;
import com.aste.lsme.domain.DynamicFieldsOfEquiment;
import com.aste.lsme.domain.EquipmentFieldDes;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.AssetSubtypeService;
import com.aste.lsme.service.AssetTypeService;
import com.aste.lsme.service.EquipmentPropertyDesInterface;


@Controller
@RequestMapping(value = "/addproperty")
public class EquipmentDynamicPropertyController {
	
	@Autowired
	AssetSubtypeService subSystemService;
	@Autowired
	AssetTypeService systemService;
	
	@Autowired
	EquipmentPropertyDesInterface equipmentPropertyService;
	
	
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String view(Model model)
	{
		List<AssetType> listSystem=systemService.getAll();
		
		model.addAttribute("listSystem", listSystem);
		
		model.addAttribute("dynamicFieldsOfEquiment",new DynamicFieldsOfEquiment());
		return "assetTemplate";
		
	}
	
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String view(Model model,@ModelAttribute("dynamicFieldsOfEquiment")DynamicFieldsOfEquiment  dynamicFieldsOfEquiment,HttpSession session)
	{

		//System.out.println("here in the event "+equipmentSubType.getSubSystemName().getId());
		Workspace w = (Workspace) session.getAttribute("workspace");
		if(!(dynamicFieldsOfEquiment==null))
		{
		
		
			DynamicFieldsOfEquiment equipmentTemp=equipmentPropertyService.getUnique(dynamicFieldsOfEquiment.getAssetSubType().getId(), w);
		if(!(equipmentTemp==null))
				{
					equipmentTemp.setAssetSubType(null);
					equipmentPropertyService.update(equipmentTemp);
		        }
		}
		
		
		if(!(dynamicFieldsOfEquiment.getListFieldDes()==null))
		{
		List<EquipmentFieldDes> eqlist=dynamicFieldsOfEquiment.getListFieldDes();
		for(int i=0;i<eqlist.size();i++)
		{
			dynamicFieldsOfEquiment.getListFieldDes().get(i).setDynamicFieldsOfEquipment(dynamicFieldsOfEquiment);
		}
		
		System.err.println("here in update");
		dynamicFieldsOfEquiment.setWorkspace(w);
		equipmentPropertyService.save(dynamicFieldsOfEquiment);
		}
		
		return "redirect:/addproperty/add";
		
		
	}
	
	@RequestMapping(value="/getsubtype/{id}",method=RequestMethod.GET)
	@ResponseBody
	public List<EquipmentFieldDes> getSubType(@PathVariable("id")Long id,HttpSession session)
	{
		Workspace w = (Workspace) session.getAttribute("workspace");
		AssetSubtype equip=subSystemService.get(id);
		
		DynamicFieldsOfEquiment equp=equipmentPropertyService.getUnique(equip.getId(),w);
		if(!(equp==null))
		{
		List<EquipmentFieldDes>listEquip=equp.getListFieldDes();
		return  listEquip;
		}
		return null;
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value="/getsubsystems/{id}",method=RequestMethod.GET)
	@ResponseBody
	public List<AssetSubtype> getSubSystems(@PathVariable("id")Long id)
	{
		
		List<AssetSubtype> list=subSystemService.getAssetSubtype(id);
		return list;
		
	}
	
	
	
	
	
	
	
	
	
	
	

	
	
}
