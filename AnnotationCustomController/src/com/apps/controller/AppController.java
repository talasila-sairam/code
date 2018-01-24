package com.apps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
	
	public AppController() {
		System.out.println("Init");
	}

	@RequestMapping("/vissonview.htm")
	public String renderVissionView() {
		System.out.println("chandu");
		return "About";
	}
}
