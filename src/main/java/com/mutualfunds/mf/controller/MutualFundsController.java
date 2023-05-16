package com.mutualfunds.mf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mutualfunds.mf.entity.MutualFunds;
import com.mutualfunds.mf.service.MutualFundsService;

@Controller
@RequestMapping("/mutualfunds/details")
public class MutualFundsController {
	@Autowired
	MutualFundsService mutualFundsService;
	
//	http://localhost:8090/mutualfunds/details/top?limit=5
	@CrossOrigin("http://localhost:4200")
	@GetMapping(value = "/top",produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<MutualFunds>> topMutualFundsDetails(@RequestParam("limit") int limit){
		return ResponseEntity.ok(mutualFundsService.topMutualFundsDetails(limit));
	}
	
//	http://localhost:8090/mutualfunds/details/bottom?limit=5
	@CrossOrigin("http://localhost:4200")
	@GetMapping(value = "/bottom",produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<MutualFunds>> bottomMutualFundsDetails(@RequestParam("limit") int limit){
		return ResponseEntity.ok(mutualFundsService.bottomMutualFundsDetails(limit));
	}
	
//	http://localhost:8090/mutualfunds/details/update/price?delta=5&symbol=SBI-ESG
	@CrossOrigin("http://localhost:4200")
	@PatchMapping(value = "/update/price")
	@ResponseBody
	public Boolean updatePriceandDelta(@RequestParam("delta") Double delta,@RequestParam("symbol") String symbol){
		return mutualFundsService.updateDeltaPrice(delta,symbol);
	}
	
//	http://localhost:8090/mutualfunds/details/history?symbol=SBI-ESG
	@CrossOrigin("http://localhost:4200")
	@GetMapping(value = "/history",produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<MutualFunds>> fectHistoryBySymbol(@RequestParam("symbol") String symbol){
		return ResponseEntity.ok(mutualFundsService.fetchHistoryBySymbol(symbol));
	}
	
}
