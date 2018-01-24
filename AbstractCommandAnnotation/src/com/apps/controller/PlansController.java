package com.apps.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apps.dto.Plan;
import com.apps.dto.SearchPlans;

@Controller
public class PlansController {
	
	@RequestMapping("/plans.htm")
	public String showPlans(@RequestParam("provider") String provider,Model model) {
		List<Plan> plans=null;
		plans=new ArrayList<>();
		if("airtel".equals(provider)) {
			plans.add(new Plan("Airtel ABC", 50, 34));
			plans.add(new Plan("Airtel CDF", 100, 89));
		}else if("vodaphone".equals(provider)) {
			plans.add(new Plan("Voda ABC", 50, 34));
			plans.add(new Plan("Voda CDF", 100, 89));
		}
		model.addAttribute("plans", plans);
		return "Provider-plans";
	}
	@RequestMapping("/search-plans.htm")
	public void searchedPlans(@ModelAttribute SearchPlans plans,ModelMap map) {
		System.out.println(plans.toString());
	}
}
