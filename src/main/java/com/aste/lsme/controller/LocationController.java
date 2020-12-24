package com.aste.lsme.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
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
import com.aste.lsme.domain.Location;
import com.aste.lsme.domain.QrCodeGenerator;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.BuildingService;
import com.aste.lsme.service.LocationService;
import com.aste.lsme.utils.CommonMethods;
import com.google.zxing.WriterException;

@Controller
@RequestMapping("/location")
public class LocationController {

	int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS;
	Long records = 0L;
	
	@Autowired
	LocationService locationService;
	@Autowired
	BuildingService buildingService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model,HttpSession session){
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		
		if(!model.asMap().containsKey("location")){
		model.addAttribute("location", new Location());
		}
		model.addAttribute("buildingList", buildingService.getWorkspaceBuildings(w));
		
		if(model.asMap().containsKey("result")){
			model.addAttribute(
					"org.springframework.validation.BindingResult.location",
					model.asMap().get("result"));
		}
		
		
		
		int page = 1;
		pagination(page, model,w);
		
		return "location";
	}
	
	@RequestMapping(value="/add" , method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("location") Location location,BindingResult result,
						RedirectAttributes redirectAttributes,HttpSession session) throws IOException, WriterException{
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		location.setWorkspace(w);
	
				
		if(result.hasErrors()){
			redirectAttributes.addFlashAttribute("location",location);
			redirectAttributes.addFlashAttribute("result",result);
			return "redirect:/location";
		}
		
		String locationCode=CommonMethods.randomString("");
		System.out.println("locationcode ==="+locationCode);
		location.setLocationCode(locationCode);
		
		if(locationService.persist(location,w)){
			QrCodeGenerator.generateQrcode(locationCode,Constants.LOCATION_QR_IMAGE_PATH);
			redirectAttributes.addFlashAttribute("success","Location saved successfully");
			return "redirect:/location";
		}
		else{
			redirectAttributes.addFlashAttribute("fail", "Location already exists");
			redirectAttributes.addFlashAttribute("location", location);
			return "redirect:/location";
		}
		
	}
	
	@RequestMapping(value = "/edit/{id}/{page}",method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id,@PathVariable("page") int page,Model model,HttpSession session) {
		
		Workspace w = (Workspace) session.getAttribute("workspace");

		if(!model.asMap().containsKey("location")){
		model.addAttribute("location",locationService.get(id));
		}
		if(model.asMap().containsKey("result")){
			model.addAttribute(
					"org.springframework.validation.BindingResult.location",
					model.asMap().get("result"));
		}
		model.addAttribute("buildingList", buildingService.getAll());
		model.addAttribute("edit", true);
		
		
		if(model.asMap().containsKey("page")){
			page = (Integer)model.asMap().get("page");
			pagination(page, model,w);
		}
		else{
			pagination(page, model,w);
		}
		
		return "location";
		
	}
	
	@RequestMapping(value = "/update" , method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("location") Location location,BindingResult result,
						RedirectAttributes redirectAttributes,HttpServletRequest request,HttpSession session) {
	
		Workspace w = (Workspace) session.getAttribute("workspace");
		location.setWorkspace(w);
		int page = Integer.parseInt(request.getParameter("p"));
		redirectAttributes.addFlashAttribute("page", page);

		if(result.hasErrors()){
			redirectAttributes.addFlashAttribute("location", location);
			redirectAttributes.addFlashAttribute("result", result);
			return "redirect:/location/edit/"+location.getId()+"/"+page;
		}
		else if(locationService.update(location,w)){
			redirectAttributes.addFlashAttribute("success", "Location updated successfully");
			return "redirect:/location";
		}
		else{
			redirectAttributes.addFlashAttribute("fail", "Location already exists");
			redirectAttributes.addFlashAttribute("location", location);
			return "redirect:/location/edit/"+location.getId()+"/"+page;
		}
		 
	}
	
	@RequestMapping(value = "/delete" , method = RequestMethod.POST)
	public String delete(HttpServletRequest request,Model model){
		String[] idList = request.getParameterValues("id");
		for (int i = 0; i < idList.length; i++) {
			locationService.delete(Long.valueOf(idList[i]));
		}
		model.addAttribute("locationList", locationService.getAll());
		return "redirect:/location";
	}
	
	@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
	public String paginate(@PathVariable("page") int page,Model model,HttpSession session){
		
		Workspace w = (Workspace) session.getAttribute("workspace");

		pagination(page, model,w);
		model.addAttribute("location", new Location());
		return "location";
	}
	
	@RequestMapping(value = "/getqrimage/{imageName:.+}", method = RequestMethod.GET)
	public void getQrImage(@PathVariable("imageName") String imageName, Model model, HttpServletRequest req,
			HttpServletResponse rep) throws IOException, WriterException
	{
		System.out.println("insideee");
		System.out.println("image name==="+imageName);
		try {
			InputStream is = new FileInputStream(Constants.LOCATION_QR_IMAGE_PATH+ imageName);

			byte[] bytes = IOUtils.toByteArray(is);
			rep.setContentType(getContentType(imageName));
			OutputStream os = rep.getOutputStream();
			os.write(bytes);
			os.close();
			is.close();
			}
		catch (Exception e) 
		{}
	}
	public void pagination(int page,Model model,Workspace w){
		
		page = (page > 0) ? page : 1;
		from = ROWS*(page-1);
		records = (long)locationService.getLocationCount(w);
		total = (int) Math.ceil((double) records / (double) ROWS);
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("locationList", locationService.getLocationPaginated(from,w));
		
	}
	
		public static String getContentType(String imageName) {
			System.out.println("inside contentypeeee"+imageName);
			if (imageName.contains(".pdf"))
				return "application/pdf";
			else if (imageName.contains(".dwg"))
				return "imsage/vnd.dwg";
			else if (imageName.contains(".gif"))
				return "image/gif";
			else
				return "image/jpeg";
		}

}

