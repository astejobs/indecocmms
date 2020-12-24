package com.aste.lsme.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aste.lsme.domain.AssetType;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.EquipmentFieldData;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Location;
import com.aste.lsme.domain.QrCodeGenerator;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.AssetSubtypeService;
import com.aste.lsme.service.AssetTypeService;
import com.aste.lsme.service.BuildingService;
import com.aste.lsme.service.EquipmentPropertyDesInterface;
import com.aste.lsme.service.EquipmentService;
import com.aste.lsme.service.LocationService;
import com.aste.lsme.service.WorkspaceService;
import com.aste.lsme.utils.CommonMethods;
import com.google.zxing.WriterException;


@Controller
@RequestMapping(value = "/equipment")
public class EquipmentDynamicController {

	@Autowired
	LocationService locationService;

	@Autowired
	AssetSubtypeService equipmentSubSystemServiceInterface;

	@Autowired
	AssetTypeService systemService;

	@Autowired
    EquipmentPropertyDesInterface equipmentPropertyDesInterface;

	@Autowired
	WorkspaceService workspaceService;
	@Autowired
	EquipmentService equipmentService;

	@Autowired
	BuildingService buildingServiceInterface;

	

	private Integer total = 0;
	private Integer from = 0;
	public static final Integer ROWS = Constants.ROWS;
	private Long records = 0l;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String create_AC(Model model, HttpSession session) {
		Workspace w = (Workspace) session.getAttribute("workspace");
		Equipment ee = new Equipment();
		ee.setWorkspace(w); 
		model.addAttribute("frequencies", Constants.frequency);
		model.addAttribute("buildingList", buildingServiceInterface.getWorkspaceBuildings(w));

		List<AssetType> listSystem = systemService.getAll();
		model.addAttribute("listSystem", listSystem);
		// model.addAttribute("subSystems", equipmentSubSystemServiceInterface
		// .getEquipmentSubSystems(Constants.ACSUBSYSTEM));
		if (model.asMap().containsKey("error"))
			model.addAttribute("org.springframework.validation.BindingResult.equipment", model.asMap().get("error"));
		else {

			model.addAttribute("equipment", ee);
		}
		return "equipmentCreate";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addACEquipment(@Valid @ModelAttribute("equipment") Equipment equipment, final BindingResult result,
			Model model, HttpSession session, HttpServletRequest request, final RedirectAttributes redirectAttributes,
			@RequestParam(value = "temp_image", required = false) MultipartFile equip_image,
			@RequestParam(value = "temp_drawingImage", required = false) MultipartFile drawing_Image) throws IOException, WriterException {

		
		Workspace workspace =  (Workspace) session.getAttribute("workspace");
		equipment.setWorkspace(workspace);
	/*	if (!equip_image.isEmpty()) {
			if (checkExtention(equip_image.getContentType())) {
				String imageName = uploadImage(equipment, equip_image, result);
				if (!imageName.isEmpty())
					equipment.setImage(imageName);
			}
		}
		*/

	/*	if (!drawing_Image.isEmpty()) {

			if (checkExtention(drawing_Image.getContentType())) {
				String imageName = uploadImage(equipment, drawing_Image, result);
				if (!imageName.isEmpty())

					equipment.setDrawingImage(imageName);
			}
		}
		
		*/

		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("fieldsnull", "Error");
			redirectAttributes.addFlashAttribute("error", result);
			redirectAttributes.addFlashAttribute("equipment", equipment);
			return "redirect:/equipment/add";
		}
		
		String equipmentCode=CommonMethods.randomString(equipment.getAssetSubtype().getAssetSubTypeName());
		  QrCodeGenerator.generateQrcode(equipmentCode,Constants.QR_IMAGES_PATH);
		  equipment.setEquipmentCode(equipmentCode);
	/*	  if(equipment.getAssetNo().isEmpty())
		  {
		    String assetCode=CommonMethods.randomString(equipment.getAssetSubtype().getAssetSubTypeName());
			  equipment.setAssetNo(assetCode);
		  
		  }
		

		
		List<EquipmentFieldData> eqlist = equipment.getListData();
		if (eqlist != null) {
			for (int i = 0; i < eqlist.size(); i++) {
				equipment.getListData().get(i).setEquipment(equipment);
			}
		}
		*/
		if (equipmentService.saveEquipment(equipment)) {
			
			redirectAttributes.addFlashAttribute("success", "Equipment added successfully");
		} else {

			redirectAttributes.addFlashAttribute("fail", "Oops! failed to save  :Please Enter Data Properly");
		}

		return "redirect:/equipment/search";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable("id") long id, final RedirectAttributes redirectAttributes,
			HttpSession session)

	{

		Equipment equipment = null;
		equipment = equipmentService.find(id);

		Workspace w = (Workspace) session.getAttribute("workspace");

		model.addAttribute("frequencies", Constants.frequency);

		model.addAttribute("buildingList", buildingServiceInterface.getWorkspaceBuildings(w));

		model.addAttribute("locationList",
				locationService.getLocationList(equipment.getBuilding().getId()));

	//	model.addAttribute("subSystems",equipment.getAssetSubtype());
		model.addAttribute("subSystems",equipmentSubSystemServiceInterface.getSubSystems(equipment.getAssetType().getId()));
		model.addAttribute("systems",equipment.getAssetSubtype().getAssetType());
  
		List<AssetType> listSystem = systemService.getAll();
		model.addAttribute("listSystem", listSystem);

	//	model.addAttribute("listDes", equipment.getListData());

		if (model.asMap().containsKey("error"))
			model.addAttribute("org.springframework.validation.BindingResult.equipment", model.asMap().get("error"));
		else {
			model.addAttribute("equipment", equipment);

		}

		// equipment.setWorkspace(w);
		

		return "equipmentedit";
	}
	
	/*nadeem*/
	@RequestMapping(value= "/search",method=RequestMethod.GET)
	public String search(Model model,HttpSession session){
		Workspace w = (Workspace) session.getAttribute("workspace");
		
		model.addAttribute("equipmentSearch", new EquipmentSearchCriteria());
		model.addAttribute("buildingList", buildingServiceInterface.getWorkspaceBuildings(w));
		model.addAttribute("assetTypeList",systemService.getAll());
		
		return "equipmentSearch";
	}
	
	@RequestMapping(value= "/search",method=RequestMethod.POST)
	public String searchPost(EquipmentSearchCriteria esc,Model model,HttpSession session){
		Workspace w = (Workspace) session.getAttribute("workspace");
		esc.setWorkspace(w);
		session.setAttribute("esc", esc);
		int page=1;
		getLists(model, w);
		pagination(page, model, esc, w);
		model.addAttribute("equipmentSearch", esc);

		return "equipmentSearch";
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editACEquipment(@Valid @ModelAttribute("equipment") Equipment equipment, final BindingResult result,
			Model model, HttpSession session, HttpServletRequest request, final RedirectAttributes redirectAttributes,
			@RequestParam(value = "temp_image", required = false) MultipartFile equip_image,
			@RequestParam(value = "temp_drawingImage", required = false) MultipartFile drawing_Image)
		 {
		Workspace w = (Workspace) session.getAttribute("workspace");


	/*	if (!equip_image.isEmpty()) {

			if (checkExtention(equip_image.getContentType())) {
				String imageName = uploadImage(equipment, equip_image, result);
				if (!imageName.isEmpty())

					equipment.setImage(imageName);
			}
		}

		if (!drawing_Image.isEmpty()) {

			if (checkExtention(drawing_Image.getContentType())) {
				String imageName = uploadImage(equipment, drawing_Image, result);
				if (!imageName.isEmpty())

					equipment.setDrawingImage(imageName);
			}
		}

*/
	
		
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("equipment", equipment);
			redirectAttributes.addFlashAttribute("fieldsnull", "Error");
			redirectAttributes.addFlashAttribute("error", result);
			System.out.println("errors are: " + result.getAllErrors().toString());
			redirectAttributes.addFlashAttribute("fail",
					  "Update not successfull");
			
			return "redirect:/equipment/edit/" + equipment.getId();
		}
		

		/*	List<EquipmentFieldData> eqlist = equipment.getListData();
			if(eqlist!=null)
			{
				for (int i = 0; i < eqlist.size(); i++) {
					equipment.getListData().get(i).setEquipment(equipment);
				}
			}
			
		*/
			try
			{
				    equipment.setWorkspace(w);
					equipmentService.update(equipment);
					redirectAttributes.addFlashAttribute("success", "Equipment Updated");
			
			}
			catch (Exception e) {
			  redirectAttributes.addFlashAttribute("fail",
			  "Update Not successfull");
			}
		 
			return "redirect:/equipment/search";

	}
	
	@RequestMapping("/building/{bId}/location/{lId}")
	public @ResponseBody ResponseEntity<?> equipmentListOnBuildingAndLocation(@PathVariable("bId") long buildingId,@PathVariable("lId") long locationId){
		return ResponseEntity.ok(equipmentService.getEquipmentsByBuildingIdAndLocationId(buildingId, locationId));
	}
	

	@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
    public String paginate(@PathVariable("page") int page,Model model,HttpSession session){
		
	   EquipmentSearchCriteria esc=(EquipmentSearchCriteria) session.getAttribute("esc");
       Workspace w=(Workspace)session.getAttribute("workspace");
       pagination(page, model, esc,w);
       model.addAttribute("equipmentSearch",esc);
       getLists(model,w);
       
       return "equipmentSearch";
    }
	
	private void pagination(int page, Model model,EquipmentSearchCriteria esc,Workspace w) {
		
		page = (page > 0) ? page : 1;
		from = ROWS*(page-1);
		records = (long) equipmentService.getEquipmentCount(esc,w);
    	total = (int) Math.ceil((double) records / (double) ROWS);
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("equipmentList", equipmentService.getEquipmentPaginated(from, esc,w));
		
	}
	
	private void getLists(Model model,Workspace w) {
		model.addAttribute("buildingList", buildingServiceInterface.getWorkspaceBuildings(w));
		model.addAttribute("assetTypeList",systemService.getAll());		
	}

	
	@RequestMapping(value = "/locations/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<Location> ajaxsearchFR(@PathVariable("id") Long id) {

		System.out.println("in the ajax search checking building values");
		List<Location> locationlist = locationService.getLocationList(id);
		System.out.println("locationList size is " + locationlist.size());
		return locationlist;
	}


	public String uploadImage(Equipment equipment, MultipartFile image, Errors errors) {
		String imageName = equipment.getName() + System.currentTimeMillis() + "."
				+ image.getContentType().split("/")[1];
		if (saveImageToPath(image, imageName)) {
			return imageName;
		} else {
			errors.getFieldError("Failed to upload Image");
			return "redirect:/equipment/add";
		}
	}

	public boolean saveImageToPath(MultipartFile image, String imageName) {
		try {

			FileUtils.writeByteArrayToFile(new File(Constants.PATH + imageName), image.getBytes());

		} catch (Exception e) {

			System.out.println("taha");
			return false;
		}
		
		return true;

	}

	@RequestMapping(value = "/getimage/{imageName:.+}", method = RequestMethod.GET)
	public void getImage(@PathVariable("imageName") String imageName, Model model, HttpServletRequest req,
			HttpServletResponse rep) throws IOException, WriterException {
		try {
			InputStream is = new FileInputStream(Constants.PATH+ imageName);

			byte[] bytes = IOUtils.toByteArray(is);
			rep.setContentType(getContentType(imageName));
			OutputStream os = rep.getOutputStream();
			os.write(bytes);
			os.close();
			is.close();
		} catch (Exception e) {
		}

	}
	
	@RequestMapping(value = "/getqrimage/{imageName:.+}", method = RequestMethod.GET)
	public void getQrImage(@PathVariable("imageName") String imageName, Model model, HttpServletRequest req,
			HttpServletResponse rep) throws IOException, WriterException {
		try {
			InputStream is = new FileInputStream(Constants.QR_IMAGES_PATH+ imageName);

			byte[] bytes = IOUtils.toByteArray(is);
			rep.setContentType(getContentType(imageName));
			OutputStream os = rep.getOutputStream();
			os.write(bytes);
			os.close();
			is.close();
		} catch (Exception e) {
		}

	}

	public static String getContentType(String imageName) {
		if (imageName.contains(".pdf"))
			return "application/pdf";
		else if (imageName.contains(".dwg"))
			return "imsage/vnd.dwg";
		else if (imageName.contains(".gif"))
			return "image/gif";
		else
			return "image/jpeg";
	}

	private Boolean checkExtention(String extension) {
		if (extension.equals("image/jpeg"))
			return true;
		else if (extension.equals("image/jpg"))
			return true;
		else if (extension.equals("image/png"))
			return true;
		else

			return false;

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(Model model, @PathVariable("id") long id, final RedirectAttributes redirectAttributes)
	{

		try {
			equipmentService.delete(id);
			redirectAttributes.addFlashAttribute("success", "Deleted Successfully");
			System.err.println("error");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("fail", "Deletion unSuccessfully");
		}

		return "redirect:/equipment/search";
	}

}
