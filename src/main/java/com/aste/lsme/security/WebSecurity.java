package com.aste.lsme.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@Order(2)
public class WebSecurity extends WebSecurityConfigurerAdapter{

	@Autowired
    private UserDetailsService userDetailsService;

	@Autowired
	private  AuthenticationSuccess authenticationSuccess;
	
	@Bean
	public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder;
    }
	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	String[] permittedUrls  = {"/swagger-ui.html","/webjars/**","/swagger-resources/**","/resources/**", "/registration","/v2/**","/login","/verify"};
        http.authorizeRequests()
                    .antMatchers(permittedUrls).permitAll()
                    .anyRequest().authenticated();
            
        http.authorizeRequests().antMatchers("/**").hasAnyRole().anyRequest().authenticated();
        
        http.formLogin()
                    .loginPage("/login")
                    .usernameParameter("username")
                    			.passwordParameter("password").successHandler(authenticationSuccess)
                    			.failureUrl("/login?error=true");
        http.logout().logoutUrl("/logout")
                    .permitAll();
        http.csrf().disable();
    }  

    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
}
