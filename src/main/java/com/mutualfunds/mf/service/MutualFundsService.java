package com.mutualfunds.mf.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mutualfunds.mf.entity.MutualFunds;
import com.mutualfunds.mf.entity.MutualFundsHistory;
import com.mutualfunds.mf.repository.MutualFundsRepository;

@Service
public class MutualFundsService {
	@Autowired
	MutualFundsRepository mutualFundsRepository;
	
//	Finding all mutual funds data which has highest delta
	public List<MutualFunds> topMutualFundsDetails(int limit) {
		return mutualFundsRepository.fetchTopMutualFunds(limit);
	}
	
//	Finding all mutual funds data which has lowest delta
	public List<MutualFunds> bottomMutualFundsDetails(int limit) {
		return mutualFundsRepository.bottomMutualFundsDetails(limit);
	}
	
//	Checking whether given symbol present in table or not 
	public Boolean checkSymbol(String symbol) {
		MutualFunds mutualFunds = null;
		mutualFunds= mutualFundsRepository.checkSymbol(symbol);
		if (mutualFunds== null) {
			return false;
		}
		return true;
	}
	
	@Transactional
	public Boolean updateDeltaPrice(Double delta, String symbol) {
		if (checkSymbol(symbol)) {
			MutualFunds mutualFunds = mutualFundsRepository.checkSymbol(symbol);
			Double currPrice = mutualFunds.getCurrentPrice();
//			Double profitorloss;
//			profitorloss = (currPrice * delta)/100;
//			if (mutualFunds.getCurrentPrice() >= 0 ) {
				currPrice = mutualFunds.getCurrentPrice() + (currPrice * delta)/100;
//			}
//			else {
//				currPrice = mutualFunds.getCurrentPrice() - profitorloss; 
//			}	
//			return mutualFundsRepository.updateData(currPrice, delta, symbol);
			currPrice = Math.round(currPrice*100.0)/100.0;
//			Calling the history function in same class so that that can call other functions
			history(delta, currPrice, mutualFunds);
			mutualFundsRepository.updateData(currPrice, delta, symbol);
//			mutualFundsRepository.
			System.out.println("Current Price : " +currPrice + " Profit or loss " + (currPrice * delta)/100 + "Row Updated : ");
			
			return true;
		}
		return false;
	}
	
	@Autowired
	MutualFundsHistoryService mutualFundsHistoryService;
	public void history(Double delta, Double Price, MutualFunds mutualFunds) {
		MutualFundsHistory mutualFundsHistory = new MutualFundsHistory();
		mutualFundsHistory.setDateTime(LocalDateTime.now());
		mutualFundsHistory.setDelta(delta);
		mutualFundsHistory.setPrice(Price);
		mutualFundsHistory.setMutualFunds(mutualFunds);
		
//		Calling the function available in History service class 
		mutualFundsHistoryService.insertData(mutualFundsHistory);
	}
	
	public List<MutualFunds> fetchHistoryBySymbol(String symbol){
		if (checkSymbol(symbol)) {
			return mutualFundsRepository.fetchHistory(symbol);
		}
		return null;
	}
}
