package com.mutualfunds.mf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutualfunds.mf.entity.MutualFunds;
import com.mutualfunds.mf.entity.MutualFundsHistory;
import com.mutualfunds.mf.repository.MutualFundsHistoryRepository;
import com.mutualfunds.mf.repository.MutualFundsRepository;



@Service
public class MutualFundsHistoryService {
	
	@Autowired
	MutualFundsHistoryRepository mutualFundsHistoryRepository;
	
	@Autowired
	MutualFundsRepository mutualFundsRepository;
	
//	This function is getting called from MutualFundsService's function 
	public Boolean insertData(MutualFundsHistory mutualFundsHistory) {
		System.out.println(mutualFundsHistory);
		System.out.println(mutualFundsHistory.getPrice());
		mutualFundsHistoryRepository.insertHistory(mutualFundsHistory.getDateTime(), mutualFundsHistory.getDelta(), mutualFundsHistory.getPrice(), mutualFundsHistory.getMutualFunds().getSchemaId());
//		mutualFundsHistoryRepository.insertHistory(mutualFundsHistory.getDateTime(), mutualFundsHistory.getDelta(), mutualFundsHistory.getMutualFunds().getSchemaId(),mutualFundsHistory.getPrice());
//		mutualFundsHistoryRepository.save(mutualFundsHistory);
		return true;
	}
	
	
	public List<MutualFundsHistory> getHistory(String symbol){
		MutualFunds mutualFunds = null;
		mutualFunds = mutualFundsRepository.checkSymbol(symbol);
		if (mutualFunds != null ) {
			System.out.println(mutualFunds.getSchemaId());
			List<MutualFundsHistory> list = mutualFundsHistoryRepository.fetchHistory(mutualFunds.getSchemaId());
//			System.out.println(list(0));
			System.out.println("Hello");
			System.out.println(list.get(0));
			return list;
		}
		return null;
	}
}
