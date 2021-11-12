package com.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {

	@RequestMapping(value="contactus")
	public String contactUs(Model model) {
		model.addAttribute("fname", "Anand");
		return "contact-us";
	}
	
}
