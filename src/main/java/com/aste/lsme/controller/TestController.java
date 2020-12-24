package com.aste.lsme.controller;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping("/test")
	public String test(){
		
		System.err.println(Timestamp.from(Instant.now()));
		return "good";
	}
}
 