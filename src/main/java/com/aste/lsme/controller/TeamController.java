package com.aste.lsme.controller;



import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.Team;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.TeamService;
import com.aste.lsme.service.TechnicianService;


@Controller
@RequestMapping(value = "/team")
public class TeamController {
	
	
	int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS;
	Long records = 0L;
	
	
	@Autowired
	TechnicianService tecnicianservice;
	
	@Autowired
	TeamService teamservice;
	
	@RequestMapping(value = "/add",method = RequestMethod.GET)
	public String get(Model model,HttpSession session) {
    
	Workspace w=(Workspace) session.getAttribute("workspace");
	
	if(!model.asMap().containsKey("team")){
		   model.addAttribute("team", new Team());
		} 

		
	model.addAttribute("tecnicianlist",tecnicianservice.getTechnician());
	
   
	if(model.asMap().containsKey("result")){
		model.addAttribute(
				"org.springframework.validation.BindingResult.team",
				model.asMap().get("result"));
	}
	
	int page = 1;
	pagination(page, model,w);
    
	return "Team";
	}
	
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public String saveteam(@Valid @ModelAttribute("team") Team team,BindingResult result,Model model,
			HttpSession session,RedirectAttributes redirectAttributes) {
		
		
		
	Workspace w=(Workspace) session.getAttribute("workspace");
	team.setWorkspace(w);
	
	model.addAttribute("tecnicianlist",tecnicianservice.getTechnician());
	model.addAttribute("list", teamservice.getTeamList(w));
	
	if(result.hasErrors()){
		
	    redirectAttributes.addFlashAttribute("team",team);
	    redirectAttributes.addFlashAttribute("result",result);
	    redirectAttributes.addFlashAttribute("fail","Please enter all fields correctly");
		return "redirect:/team/add/";
	}
	else if(teamservice.save(team)){
		redirectAttributes.addFlashAttribute("success","Team saved successfully");
		return "redirect:/team/add/";
	}
	else{
		redirectAttributes.addFlashAttribute("fail", "Team already exists");

		redirectAttributes.addFlashAttribute("team",team);
		return "redirect:/team/add/";
	}
	
	
	}
	
	
	
	@RequestMapping(value = "/edit/{id}/{page}" , method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id,@PathVariable("page")int page,Model model,HttpSession session,RedirectAttributes redirectAttributes) {
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		
		if(!model.asMap().containsKey("team")){
			  model.addAttribute("team", teamservice.getTeam(id));
			}
			if(model.asMap().containsKey("result")){
				model.addAttribute(
						"org.springframework.validation.BindingResult.team",
						model.asMap().get("result"));
			}
	
			
			if(model.asMap().containsKey("page")){
				page = (Integer) model.asMap().get("page");
				pagination(page, model,w);
			}
			else{
				pagination(page, model,w);
			}
			model.addAttribute("tecnicianlist",tecnicianservice.getTechnician());
			
			model.addAttribute("edit", true);
			
		
		
		return "Team";
	}
	
	@RequestMapping(value = "/update" ,method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("team")Team team,BindingResult result,RedirectAttributes redirectAttributes,HttpServletRequest request,HttpSession session) {
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		team.setWorkspace(w);
		
		if(result.hasErrors()){
			
		    redirectAttributes.addFlashAttribute("team",team);
		    redirectAttributes.addFlashAttribute("result",result);
		    return "redirect:/team/edit/"+team.getId();
			
		}
		else if(teamservice.update(team, w)){
			redirectAttributes.addFlashAttribute("success","Team saved successfully");
			redirectAttributes.addFlashAttribute("tecnicianlist",tecnicianservice.getTechnician());
			redirectAttributes.addFlashAttribute("list", teamservice.getTeamList(w));
			
			return "redirect:/team/add/";
		}
		else{
			redirectAttributes.addFlashAttribute("fail", "Team already exists");

			redirectAttributes.addFlashAttribute("team",team);
			redirectAttributes.addFlashAttribute("result",result);
			 return "redirect:/team/edit/"+team.getId();
		}
		
		
		
	}
	
	
	@RequestMapping(value="delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable("id") Long id,Model model,RedirectAttributes redirectAttributes){
		try{
			teamservice.delete(id);
			
			redirectAttributes.addFlashAttribute("success","Deleted successfully");
			
			
			
		}catch(Exception e){
			redirectAttributes.addFlashAttribute("fail","Unable to delete");
			
		}
		
		return "redirect:/team/add";
		
	}
	
	
	@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
	public String paginate(@PathVariable("page") int page,Model model,HttpSession session){
		Workspace w = (Workspace) session.getAttribute("workspace");
		pagination(page, model,w);
		model.addAttribute("team", new Team() );
		return "Team";
	}
	
	
public void pagination(int page,Model model,Workspace w){
		
		page = (page > 0) ? page : 1;
		from = ROWS*(page-1);
		records = (long) teamservice.getTeamCount(w);
		total = (int) Math.ceil((double) records / (double) ROWS);
		System.out.println(total+"----------------------------------------------------------------------");
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("list", teamservice.getTeamPaginated(from, w));
	}

	
	
	  
}
