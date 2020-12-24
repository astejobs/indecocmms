package com.aste.lsme.security;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.service.MailService;
import com.aste.lsme.service.UserDetailsServiceInterface;

@Component
public class AuthenticationSuccess implements AuthenticationSuccessHandler{

	@Autowired
	UserDetailsServiceInterface userService;
	@Autowired
	HttpSession session;
	
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	MailService mailService;
	
	
	 
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }
 
    protected void handle(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
 
        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
 
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
    protected String determineTargetUrl(Authentication authentication) {
    	
        UserDetails userDetails =
   				(UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetail user = userService.findUserByUsername(userDetails.getUsername());
    	session.setAttribute("user",user);
        if(user.isTwoFaEnabled()){
        	sendEmail(user.getUsername());
        	session.setAttribute(Constants.TWO_FA_VERIFICATION,Constants.TWO_FA_VERIFICATION);
        	return "/verify";
        }
        session.removeAttribute(Constants.TWO_FA_VERIFICATION);
    	return "/dashboard";
    }
 
    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
 
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
    
    private boolean sendEmail(String username){
		String code = String.format("%04d",new Random().nextInt(10000));
		LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(30); 
		String message =  "Your login code is "+code+". It will expire in 30 minutes.";
		String subject="Login Code"; 
		String email = userService.getEmailOnUsername(username);
		userService.updateCodeAndExpirationTime(code, expirationTime,username);
		mailService.send2faAuthCode(message, email, subject);
		return true;
	}
    
    
}
