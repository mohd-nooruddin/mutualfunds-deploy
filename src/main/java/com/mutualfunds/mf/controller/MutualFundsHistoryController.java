package com.mutualfunds.mf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mutualfunds.mf.entity.MutualFundsHistory;
import com.mutualfunds.mf.service.MutualFundsHistoryService;

@Controller
@RequestMapping("/mutualfunds/history")
public class MutualFundsHistoryController {
	
	@Autowired
	MutualFundsHistoryService mutualFundsHistoryService;
	
	
//	@GetMapping (value = "/symbol",produces = {MediaType.APPLICATION_JSON_VALUE})
////	@ResponseBody
//	public List<MutualFundsHistory> fetchHistory(@RequestParam ("s") String symbol){
//		System.out.println(mutualFundsHistoryService.getHistory(symbol).get(0));
//		return mutualFundsHistoryService.getHistory(symbol);
//	}
	
}
