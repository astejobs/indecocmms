package com.aste.lsme.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.ChecklistDao;
import com.aste.lsme.domain.ChecklistHeader;
import com.aste.lsme.domain.ChecklistProperty;
import com.aste.lsme.domain.ChecklistPropertyTitle;
import com.aste.lsme.domain.ChecklistSearch;
import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.ChecklistService;

@Service
@Transactional
public class ChecklistServiceImpl implements ChecklistService {
	
	@Autowired
	ChecklistDao checklistdao;
	
	public void addChecklist(ChecklistHeader checklist) throws Exception
	{
		for (ChecklistPropertyTitle checklistpropertytitle : checklist.getPropertyTitles()) {
			  checklistpropertytitle.setChecklistHeader(checklist);
			for(ChecklistProperty checklistproperty :checklistpropertytitle.getProperties()) {
				checklistproperty.setChecklistPropertyTitle(checklistpropertytitle);
				     if(checklistproperty.getDescription().contains("___"))
				    	 checklistproperty.setDescriptionType("FILLTHEBLANKS");
			            else
			            	checklistproperty.setDescriptionType("QUESTIONANSWER");
				     
			          }
			          
			        }
				
		  for(Equipment equipment :checklist.getEquipment()){
			equipment.setChecklists(Arrays.asList(checklist));
		  }
		
		checklistdao.addChecklist(checklist);
		
	}
	
	public void update(ChecklistHeader checklist) throws Exception
	{
		for (ChecklistPropertyTitle checklistpropertytitle : checklist.getPropertyTitles()){
         checklistdao.removeChecklistproperties(checklistpropertytitle.getId());
		}
         checklistdao.removeChecklistTitle(checklist.getId());
		for (ChecklistPropertyTitle checklistpropertytitle : checklist.getPropertyTitles()) {
			 checklistpropertytitle.setChecklistHeader(checklist);
			for(ChecklistProperty checklistproperty :checklistpropertytitle.getProperties()) {
				checklistproperty.setChecklistPropertyTitle(checklistpropertytitle);
				if(checklistproperty.getDescription().contains("___"))
			    	 checklistproperty.setDescriptionType("FILLTHEBLANKS");
		            else
		            	checklistproperty.setDescriptionType("QUESTIONANSWER");
			     
		          }
			}
		
		for(Equipment equipment :checklist.getEquipment()){
			equipment.setChecklists(Arrays.asList(checklist));
		}
		
		checklistdao.update(checklist);
		
	}

	@Override
	public Long countSearchPage(ChecklistSearch qsc) {
		
		return checklistdao.countSearchPage(qsc);
	}

	@Override
	public List<ChecklistHeader> getSearchPage(ChecklistSearch qsc) {
		
		return checklistdao.getSearchPage(qsc);
	}
	
	public ChecklistHeader find(Long id)
	{
		return checklistdao.find(id);
	}

	@Override
	public List<ChecklistHeader> getAllWorkspaceBased(Workspace w) {
		// TODO Auto-generated method stub
		return checklistdao.getAllWorkspaceBased(w);
	}
	
	@Override
	public List<ChecklistHeader> listAll(Long id) {
		// TODO Auto-generated method stub
		return checklistdao.listAll(id);
	}

	@Override
	public List<ChecklistPropertyTitle> findPropertyTitles(ChecklistHeader chklistHeader) {		
		return checklistdao.findPropertyTitles(chklistHeader);
	}

	
	

}
