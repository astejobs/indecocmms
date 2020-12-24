package com.aste.lsme.controller;

import javax.servlet.http.HttpServletRequest;
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
import com.aste.lsme.domain.UnitOfMeasure;
import com.aste.lsme.service.UnitOfMeasureService;


@Controller
@RequestMapping("/measure")
public class UnitOfMeasureController {
	
	int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS; 
	Long records = 0L;	
	@Autowired
	UnitOfMeasureService unitOfMeaasureService;
	
	
	@RequestMapping( method = RequestMethod.GET)
	public String unitOfMeasureCenter(Model model)  
				
			{
							if(!model.asMap().containsKey("unitmeasure"))
							{
								model.addAttribute("unitmeasure", new UnitOfMeasure());
							} 
		
							if(model.asMap().containsKey("result"))
							{
								model.addAttribute(
								"org.springframework.validation.BindingResult.unitmeasure",
								model.asMap().get("result"));
							}
							
		//					List<UnitOfMeasure> unitMeasureList =unitOfMeaasureService.getUnitOfMeasure();
		//					model.addAttribute("unitMeasureList",unitMeasureList);
							//System.out.println(unitMeasureList+"listhere");
							int page = 1;
							pagination(page, model);
							return "unitofmeasure";
						
			}
	@RequestMapping(method=RequestMethod.POST)
	     public String addunitOfMeasureCenter(@Valid@ModelAttribute("unitmeasure") UnitOfMeasure unitmeasure,BindingResult result,RedirectAttributes redirectAttributes)
				
				
	{
					if(result.hasErrors())
					{
						redirectAttributes.addFlashAttribute("unitmeasure", unitmeasure);
						redirectAttributes.addFlashAttribute("result", result);
			
						return "redirect:/measure";
					}
					else if(unitOfMeaasureService.addMeasureUnit(unitmeasure))
						
					{
						redirectAttributes.addFlashAttribute("success", "unitOfMeaasure added successfully");
			
						return "redirect:/measure";
					}
					else
					{
						redirectAttributes.addFlashAttribute("fail", "unitOfMeaasure  already exists");
						redirectAttributes.addFlashAttribute("unitmeasure", unitmeasure);
						return "redirect:/measure";
						
					}
							
						
		
				
	}
	@RequestMapping(value="/edit/{id}/{page}" ,method=RequestMethod.GET)
				public String edit(@PathVariable ("id") Long id,@PathVariable("page") int page,Model model)
				{
			
					    if(!model.asMap().containsKey("unitmeasure"))
					    {
						  model.addAttribute("unitmeasure",unitOfMeaasureService.get(id));
						}
						if(model.asMap().containsKey("result"))
						{
							model.addAttribute(
									"org.springframework.validation.BindingResult.unitmeasure",
									model.asMap().get("result"));
						}
						model.addAttribute("edit", true);
						if(model.asMap().containsKey("page")){
							page = (Integer) model.asMap().get("page");
							pagination(page, model);
						}
							else
							{
								pagination(page, model);
							}
						
						
					
				
					    return "unitofmeasure";
					
				}
	
	@RequestMapping(value="/update" ,method=RequestMethod.POST)
			public String edit(@Valid@ModelAttribute("unitmeasure") UnitOfMeasure unitmeasure,RedirectAttributes redirectAttribute,Model model,BindingResult result,HttpServletRequest  request)
			{
					int page = Integer.parseInt(request.getParameter("p"));
					redirectAttribute.addFlashAttribute("page", page);
					        if(result.hasErrors())
					        {
					        	redirectAttribute.addFlashAttribute("unitmeasure", unitmeasure);
					        	redirectAttribute.addFlashAttribute("result", result);
					    		return "redirect:/measure/edit/"+unitmeasure.getId()+"/"+page;
					        }
					        else if(unitOfMeaasureService.update(unitmeasure))
					        {
					        	 redirectAttribute.addFlashAttribute("success", "unitOfMeaasure added Updated");
					        	 return "redirect:/measure";
					        }
					        else
					        {
					        	redirectAttribute.addFlashAttribute("unitmeasure", unitmeasure);
					        	redirectAttribute.addFlashAttribute("fail", "unitOfMeaasure  already exists");
					        	 return "redirect:/measure/edit/"+unitmeasure.getId()+"/"+page;
					        }
				
					        
			}
	 @RequestMapping(value="/delete", method=RequestMethod.POST)
	
				public String delete( HttpServletRequest request ,RedirectAttributes redirectAttributes) 
				{
						System.out.println("saba");
						String[] id = request.getParameterValues("id");
						System.out.println(id+"ayaa");
						if(id != null){
								for (int i = 0; i < id.length; i++)
								{
								
									try
									{
											unitOfMeaasureService.delete(Long.valueOf(id[i]));
											System.out.println(id+"hua");
										}catch (Exception e) 
										{
											redirectAttributes.addFlashAttribute("fail","Some items cannot be  deleted ");
										}
									}
							   }
									else
									{
										redirectAttributes.addFlashAttribute("fail", "Select items to delete");
									} 
								       	return "redirect:/measure";	
								
								
							}
	 @RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
		public String paginate(@PathVariable("page") int page,Model model)
	   {
			pagination(page, model);
			model.addAttribute("unitmeasure", new UnitOfMeasure());
			return "unitofmeasure";
		}
		
		public void pagination(int page,Model model){
			
			page = (page > 0) ? page : 1;
			from = ROWS*(page-1);
			records = (long) 	unitOfMeaasureService.getUnitOfMeaasureCount();
			total = (int) Math.ceil((double) records / (double) ROWS);
			model.addAttribute("total", total);
			model.addAttribute("page", page);
			model.addAttribute("unitMeasureList", unitOfMeaasureService.getUnitOfMeaasurePaginated(from));
//			model.addAttribute("unitMeasureList ", unitOfMeaasureService.getUnitOfMeasure());

			
		}
				
			

}
