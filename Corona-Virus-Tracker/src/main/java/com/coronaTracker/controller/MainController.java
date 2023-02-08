package com.coronaTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.coronaTracker.service.CoronaVirusDataService;
import com.coronaTracker.model.LocationStates;

@Controller
public class MainController {
	
	@Autowired
	private CoronaVirusDataService crnService;
	
	@GetMapping("/")
	public String home(Model model)
	{
		List<LocationStates> allstates=crnService.getAllStates();
		int totalDeathsReported=allstates.stream().mapToInt(stat->stat.getTotalDeaths()).sum();
		model.addAttribute("LocationStates",allstates);
		model.addAttribute("totalDeathsReported",totalDeathsReported);
		return "home";
	}

}
