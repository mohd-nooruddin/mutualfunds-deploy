package com.mutualfunds.mf.service.test;


import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mutualfunds.mf.entity.MutualFunds;
import com.mutualfunds.mf.repository.MutualFundsHistoryRepository;
import com.mutualfunds.mf.repository.MutualFundsRepository;
import com.mutualfunds.mf.service.MutualFundsHistoryService;
import com.mutualfunds.mf.service.MutualFundsService;


public class MutualFundsServiceTest {

    @Mock
    private MutualFundsRepository mutualFundsRepository;
    
    @Mock
    private MutualFundsHistoryRepository mutualFundsHistoryRepository;
    
    @InjectMocks
    private MutualFundsHistoryService mutualFundsHistoryService;

    @InjectMocks
    private MutualFundsService mutualFundsService;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testTopMutualFundsDetails() {
        int limit = 5;
        List<MutualFunds> expectedMutualFundsList = new ArrayList<>();
        // Add expected mutual funds to the list

        when(mutualFundsRepository.fetchTopMutualFunds(limit)).thenReturn(expectedMutualFundsList);

        List<MutualFunds> actualMutualFundsList = mutualFundsService.topMutualFundsDetails(limit);

        assertEquals(expectedMutualFundsList, actualMutualFundsList);
        verify(mutualFundsRepository, times(1)).fetchTopMutualFunds(limit);
    }

    @Test
    public void testBottomMutualFundsDetails() {
        int limit = 5;
        List<MutualFunds> expectedMutualFundsList = new ArrayList<>();
        // Add expected mutual funds to the list

        when(mutualFundsRepository.bottomMutualFundsDetails(limit)).thenReturn(expectedMutualFundsList);

        List<MutualFunds> actualMutualFundsList = mutualFundsService.bottomMutualFundsDetails(limit);

        assertEquals(expectedMutualFundsList, actualMutualFundsList);
        verify(mutualFundsRepository, times(1)).bottomMutualFundsDetails(limit);
    }

    @Test
    public void testCheckSymbolExists() {
        String symbol = "ABC";
        MutualFunds mutualFunds = new MutualFunds();
        when(mutualFundsRepository.checkSymbol(symbol)).thenReturn(mutualFunds);

        assertTrue(mutualFundsService.checkSymbol(symbol));
        verify(mutualFundsRepository, times(1)).checkSymbol(symbol);
    }

    @Test
    public void testCheckSymbolNotExists() {
        String symbol = "XYZ";
        when(mutualFundsRepository.checkSymbol(symbol)).thenReturn(null);

        assertFalse(mutualFundsService.checkSymbol(symbol));
        verify(mutualFundsRepository, times(1)).checkSymbol(symbol);
    }

    @Test
    @Disabled
    public void testUpdateDeltaPriceSymbolExists() {
        String symbol = "ABC";
        Double delta = 1.5;
        MutualFunds mutualFunds = new MutualFunds();
        mutualFunds.setCurrentPrice(10.0);

        when(mutualFundsRepository.checkSymbol(symbol)).thenReturn(mutualFunds);

        Boolean result = mutualFundsService.updateDeltaPrice(delta, symbol);

        assertTrue(result);
        verify(mutualFundsRepository, times(1)).checkSymbol(symbol);
        verify(mutualFundsRepository, times(1)).updateData(10.15, 1.5, symbol);
    }

    @Test
    public void testUpdateDeltaPriceSymbolNotExists() {
        String symbol = "XYZ";
        Double delta = 1.5;

        when(mutualFundsRepository.checkSymbol(symbol)).thenReturn(null);

        Boolean result = mutualFundsService.updateDeltaPrice(delta, symbol);

        assertFalse(result);
        verify(mutualFundsRepository, times(1)).checkSymbol(symbol);
        verify(mutualFundsRepository, never()).updateData(anyDouble(), anyDouble(), anyString());
    }

    @Test
    @Disabled
    public void testFetchHistoryBySymbolExists() {
        String symbol = "ABC";
        List<MutualFunds> expectedHistoryList = new ArrayList<>();
        // Add expected history entries to the list

        when(mutualFundsRepository.fetchHistory(symbol)).thenReturn(expectedHistoryList);
        when(mutualFundsService.checkSymbol(symbol)).thenReturn(true);

        List<MutualFunds> actualHistoryList = mutualFundsService.fetchHistoryBySymbol(symbol);

        assertEquals(expectedHistoryList, actualHistoryList);
        verify(mutualFundsRepository, times(1)).fetchHistory(symbol);
        verify(mutualFundsService, times(1)).checkSymbol(symbol);
    }

    @Test
    @Disabled
    public void testFetchHistoryBySymbolNotExists() {
        String symbol = "XYZ";

        when(mutualFundsService.checkSymbol(symbol)).thenReturn(false);

        List<MutualFunds> actualHistoryList = mutualFundsService.fetchHistoryBySymbol(symbol);

        assertNull(actualHistoryList);
        verify(mutualFundsService, times(1)).checkSymbol(symbol);
        verify(mutualFundsRepository, never()).fetchHistory(symbol);
    }


}
