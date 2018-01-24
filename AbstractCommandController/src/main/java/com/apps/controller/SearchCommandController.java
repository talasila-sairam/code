package com.apps.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.apps.BO.CommandClass;

public class SearchCommandController extends AbstractCommandController{

	@Override
	protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {
		CommandClass cc=(CommandClass)command;
		List<CommandClass> data=new ArrayList<>();
		data.add(cc);
		System.out.println(cc.toString());
		ModelAndView mav=new ModelAndView();
		mav.addObject("data", data);
		mav.setViewName("Searched-Data");
		return mav;
	}

}
