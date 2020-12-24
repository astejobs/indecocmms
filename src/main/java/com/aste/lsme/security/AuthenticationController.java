package com.aste.lsme.security;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aste.lsme.domain.Building;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.Location;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.domain.VerifyCode;
import com.aste.lsme.service.BuildingService;
import com.aste.lsme.service.EquipmentService;
import com.aste.lsme.service.LocationService;
import com.aste.lsme.service.MailService;
import com.aste.lsme.service.UserDetailsServiceInterface;
import com.aste.lsme.utils.Encryptor;

@Controller
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired 
	private UserDetailsService jwtInMemoryUserDetailsService;
	@Autowired
	private UserDetailsServiceInterface userService;
	@Autowired
	MailService mailService;
	@Autowired
	EquipmentService equipService;
	@Autowired
	BuildingService bService;
	@Autowired
	LocationService lService;
	
	@RequestMapping(value = "/api/authenticate", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		try {
			if(userService.checkIf2FaEnabled(authenticationRequest.getUsername())){
				if(sendCodeInEmail(authenticationRequest.getUsername()))
				return ResponseEntity.ok(new JwtResponse(null, null,null));
			}else{
				JwtResponse response=getResponse(authenticationRequest.getUsername(), authenticationRequest.getDeviceToken());
				return ResponseEntity.ok(response);
			}
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/api/verify2fa",method=RequestMethod.POST)
	public ResponseEntity<?> verify2Fa(@RequestBody VerifyCode verifyCode){
		
		String actualCode = userService.fetch2FaCodeOnUsername(verifyCode.getUsername());
		if(verifyCode.getCode().equals(actualCode)){
		  
			final UserDetails userDetails = jwtInMemoryUserDetailsService
													.loadUserByUsername(verifyCode.getUsername());
			
			if(verifyCode.getDeviceToken()!=null)
				userService.updatedeviceToken(verifyCode.getUsername(),verifyCode.getDeviceToken());
			
			final String token = jwtTokenUtil.generateToken(userDetails);
			JwtResponse response = new JwtResponse(token,userService.getRoleOnUsername(verifyCode.getUsername()),verifyCode.getUsername());
			userService.updateCodeAndExpirationTime(null, null, verifyCode.getUsername());
			return new ResponseEntity<JwtResponse>(response,HttpStatus.OK);
		}
		 
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value={"/","/login"},method=RequestMethod.GET)
	public String login(){
		return "login";
	}

	@RequestMapping(value="/verify",method=RequestMethod.GET)
	public String verify2faWeb(Model model,HttpSession session){
		VerifyCode code =new VerifyCode();
		UserDetail user = (UserDetail) session.getAttribute("user");
		code.setUsername(user.getUsername());
		model.addAttribute("verifyCode",code);
		return "verifyotp";
	}
	
	@RequestMapping(value="/verify",method=RequestMethod.POST)
	public String verify2fa(VerifyCode verifyCode,RedirectAttributes redirectAttributes,HttpSession session){
		String actualCode = userService.fetch2FaCodeOnUsername(verifyCode.getUsername());
		if(verifyCode.getCode().equals(actualCode)){	
			session.removeAttribute(Constants.TWO_FA_VERIFICATION);
			return "redirect:/dashboard";
		}
		redirectAttributes.addFlashAttribute("error","This code is not valid.");
		return "redirect:/verify";
	}

	@RequestMapping(value="/api/resendCode",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> resendCode(@RequestBody VerifyCode code){
		sendCodeInEmail(code.getUsername());
		return ResponseEntity.ok("");
	}
	
	@RequestMapping(value="/api/logout",method=RequestMethod.POST)
	public ResponseEntity<?> logout(@RequestParam("deviceToken") String deviceToken){
		System.out.println(deviceToken+"jjjjjjjjjjjjjjjjjjjjjjjj");
		if(!deviceToken.equals(null)){
		userService.logout(deviceToken);
		return ResponseEntity.ok(null);
		}else{
			return (ResponseEntity<?>) ResponseEntity.badRequest();
		}
		
	}

	
	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try { 
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	} 
	
	public JwtResponse getResponse(String username,String deviceToken){
		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(username);

		if(deviceToken!=null)
			userService.updatedeviceToken(username,deviceToken);
		
		final String token = jwtTokenUtil.generateToken(userDetails);
		JwtResponse response = new JwtResponse(token,userService.getRoleOnUsername(username),username);
		userService.updateCodeAndExpirationTime(null, null, username);
		return response;
	}
	
	private boolean sendCodeInEmail(String username){
		String code = String.format("%04d",new Random().nextInt(10000));
		LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(30); 
		String message =  "Your login code is "+code+". It will expire in 30 minutes.";
		String subject="Login Code"; 
		String email = userService.getEmailOnUsername(username);
		userService.updateCodeAndExpirationTime(code, expirationTime,username);
		mailService.send2faAuthCode(message, email, subject);
		return true;
	}
	
	
	@RequestMapping(value="/api/test/",method=RequestMethod.GET)
	public ResponseEntity<?> test(@RequestParam("query") String search){
		
		List<Equipment> eList=equipService.getAll();
		for (Equipment equipment : eList) {
			System.err.println(equipment.getId()+"---"+equipment.getEquipmentCode());
			equipment.setName(equipment.getName());
			equipService.update(equipment);
		}
		
		for (Building b : bService.getAll()) {
			System.err.println(b.getId()+"---"+b.getDescription());
			b.setName(b.getName());
			bService.update(b, b.getWorkspace());
		}
		
		for (Location l : lService.getAll()) {
			System.err.println(l.getId()+"---"+l.getDescription());
			l.setName(l.getName());
			lService.update(l,l.getWorkspace());
		}
		
		System.err.println("called on"+LocalTime.now());
		System.out.println(search+"jjjjjjjj");
		System.out.println(Encryptor.encrypt(search));
		System.out.println(Encryptor.encrypt(search));
		System.out.println(Encryptor.encrypt(search));
		System.out.println(Encryptor.encrypt(search));
		System.out.println(Encryptor.encrypt(search));
	//	CommonMethods.sendNotification(java.util.Arrays.asList(token),"test background","success");
		return null;
	}

	
}
 