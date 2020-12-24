package com.aste.lsme;

import java.util.Collections;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@SpringBootApplication
@EnableSwagger2
@EnableAsync
public class LsmeApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(LsmeApplication.class, args);
	}
	
	String[] viewNames = {"FaultReport","CivilReport","FireReport","MechanicalReport","ElectricalReport","AcmvReport","quotationreport","FaultReportMaintenanceGroup","FaultReportFaultCategory","PartTransactionReport","PartTransferReport"};
	
	@Bean
	public JasperReportsViewResolver getJasperReportsViewResolver() {
	  JasperReportsViewResolver resolver = new JasperReportsViewResolver();
	  resolver.setPrefix("classpath:/jrxmlTemplates/");
	  resolver.setSuffix(".jasper");
	  resolver.setReportDataKey("datasource");
	  resolver.setViewNames(viewNames);
	  resolver.setViewClass(JasperReportsMultiFormatView.class);
	  resolver.setOrder(0); 
	  return resolver;
	}  
	
	@Bean
	public VelocityEngineFactoryBean velocityEngine() throws Exception {
	    VelocityEngineFactoryBean velocityBean = new VelocityEngineFactoryBean();
	    Properties velocityProperties= new Properties();
	    velocityProperties.setProperty("resource.loader", "class");
	    velocityProperties.setProperty("class.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
	    velocityBean.setVelocityProperties(velocityProperties);
	    return velocityBean;
	}
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		System.err.println("in tiles config");
		String def[] = {"/WEB-INF/views/**/views.xml"};
		tilesConfigurer.setDefinitions(def); 
		return tilesConfigurer;
	} 
	
	@Bean
	public UrlBasedViewResolver tilesViewResolver() {
		UrlBasedViewResolver tilesViewResolver = new UrlBasedViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		return tilesViewResolver;
	}
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	@Bean
	public ReloadableResourceBundleMessageSource messageSource(){
		ReloadableResourceBundleMessageSource rrbms = new ReloadableResourceBundleMessageSource();
		rrbms.setBasename("classpath:messages");
		rrbms.setDefaultEncoding("UTF-8");
		return rrbms;
	}
	
	@Bean
	public Docket productApi(){
		return new Docket(DocumentationType.SWAGGER_2).select()
					.apis(RequestHandlerSelectors.basePackage("com.aste.lsme.webservices"))
						.paths(PathSelectors.any()).build().apiInfo(metaData());
	}
	
	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo("MHA Rest API",
									  "Documentation for api's of MHA App", 
									  "1.0",
									  "Terms of Service",
									  new Contact("Nadeem Baba","http://aste.co.in","nadym.baba@gmail.com"),
									  "MHA License 1.0",
									  "http://ifarms.com.sg:9000/mha", Collections.emptyList());
		return apiInfo;
	} 
	
}
