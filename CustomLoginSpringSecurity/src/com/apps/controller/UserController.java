package com.apps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	@RequestMapping("/home.htm")
	public String loginPage(@RequestParam("error") String failure,Model model) {
		if(failure!="") {
			model.addAttribute("failure", "Invalid user name password");
		}
		return "Login";
	}
}
