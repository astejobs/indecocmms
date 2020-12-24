package com.aste.lsme.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.aste.lsme.domain.Constants;

@Component
public class TwoFactorAuthenticationFilter extends OncePerRequestFilter{

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String verification = (String) request.getSession().getAttribute(Constants.TWO_FA_VERIFICATION);
		String uri = request.getRequestURI().split("/").length>2?request.getRequestURI().split("/")[1]:request.getRequestURI();
		
		String [] permittedUrls = {"api","swagger-ui.html","webjars","swagger-resources","resources", "registration","v2","login","verify"};
		boolean check=true;
		for (String url : permittedUrls) {
			if(uri.contains(url)){
				check=false;
				break;
			}
				
		}
		if(verification==null && uri.equalsIgnoreCase("verify")){
			String url = "/login";
			redirectStrategy.sendRedirect(request, response,url);
		}
		
		if(verification != null && check){
			String url = "/login";
			redirectStrategy.sendRedirect(request, response,url);
		}
		
		
		filterChain.doFilter(request, response);
	}
 
}
