package com.mutualfunds.mf.controller.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mutualfunds.mf.controller.MutualFundsController;
import com.mutualfunds.mf.entity.MutualFunds;
import com.mutualfunds.mf.service.MutualFundsService;

//@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class MutualFundsControllerTest {

    @InjectMocks
    private MutualFundsController mutualFundsController;

    @Mock
    private MutualFundsService mutualFundsService;

    @Test
    public void testTopMutualFundsDetails() {
        int limit = 5;
        List<MutualFunds> expectedMutualFundsList = new ArrayList<>();
        // Add expected mutual funds to the list

        when(mutualFundsService.topMutualFundsDetails(limit)).thenReturn(expectedMutualFundsList);

        ResponseEntity<List<MutualFunds>> responseEntity = mutualFundsController.topMutualFundsDetails(limit);
        List<MutualFunds> actualMutualFundsList = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedMutualFundsList, actualMutualFundsList);
        verify(mutualFundsService, times(1)).topMutualFundsDetails(limit);
    }

    @Test
    public void testBottomMutualFundsDetails() {
        int limit = 5;
        List<MutualFunds> expectedMutualFundsList = new ArrayList<>();
        // Add expected mutual funds to the list

        when(mutualFundsService.bottomMutualFundsDetails(limit)).thenReturn(expectedMutualFundsList);

        ResponseEntity<List<MutualFunds>> responseEntity = mutualFundsController.bottomMutualFundsDetails(limit);
        List<MutualFunds> actualMutualFundsList = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedMutualFundsList, actualMutualFundsList);
        verify(mutualFundsService, times(1)).bottomMutualFundsDetails(limit);
    }

    @Test
    public void testUpdatePriceandDelta() {
        String symbol = "SBI-ESG";
        Double delta = 5.0;

        when(mutualFundsService.updateDeltaPrice(delta, symbol)).thenReturn(true);

        Boolean result = mutualFundsController.updatePriceandDelta(delta, symbol);

        assertTrue(result);
        verify(mutualFundsService, times(1)).updateDeltaPrice(delta, symbol);
    }

    @Test
    public void testFetchHistoryBySymbol() {
        String symbol = "SBI-ESG";
        List<MutualFunds> expectedHistoryList = new ArrayList<>();
        // Add expected history entries to the list

        when(mutualFundsService.fetchHistoryBySymbol(symbol)).thenReturn(expectedHistoryList);

        ResponseEntity<List<MutualFunds>> responseEntity = mutualFundsController.fectHistoryBySymbol(symbol);
        List<MutualFunds> actualHistoryList = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedHistoryList, actualHistoryList);
        verify(mutualFundsService, times(1)).fetchHistoryBySymbol(symbol);
    }
    
    @Test
    public void testUpdatePriceandDelta_InvalidSymbol() {
        String symbol = "INVALID-SYMBOL";
        Double delta = 5.0;

        when(mutualFundsService.updateDeltaPrice(delta, symbol)).thenReturn(false);

        Boolean result = mutualFundsController.updatePriceandDelta(delta, symbol);

        assertFalse(result);
        verify(mutualFundsService, times(1)).updateDeltaPrice(delta, symbol);
    }

}

