package com.mutualfunds.mf.repository.test;
//package com.mutualfunds.mf.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.mutualfunds.mf.entity.MutualFunds;
import com.mutualfunds.mf.repository.MutualFundsRepository;

//@ExtendWith(SpringExtension.class)
//@DataJpaTest
@SpringBootTest
public class MutualFundsRepositoryTest {

    @Autowired
    private MutualFundsRepository mutualFundsRepository;

    private List<MutualFunds> sampleData;

    @BeforeEach
    public void setup() {
        sampleData = new ArrayList<>();
        sampleData.add(new MutualFunds("FundHouse1", "Symbol1", "SchemaName1", "Category1", "Description1", 100.0, 10.0, new ArrayList<>()));
        sampleData.add(new MutualFunds( "FundHouse2", "Symbol2", "SchemaName2", "Category2", "Description2", 200.0, 20.0, new ArrayList<>()));
        sampleData.add(new MutualFunds( "FundHouse3", "Symbol3", "SchemaName3", "Category3", "Description3", 300.0, 30.0, new ArrayList<>()));

        mutualFundsRepository.saveAll(sampleData);
    }


    @AfterEach
    public void cleanup() {
    	System.out.println("+++++++++++++++++++++ Cleaning Testing Data ++++++++++++++++++++");
        List<String> symbolsToDelete = new ArrayList<>();
        symbolsToDelete.add("Symbol1");
        symbolsToDelete.add("Symbol2");
        symbolsToDelete.add("Symbol3");

        for (String symbol : symbolsToDelete) {
            MutualFunds mutualFunds = mutualFundsRepository.checkSymbol(symbol);
            if (mutualFunds != null) {
                mutualFundsRepository.delete(mutualFunds);
            }
        }
    }



    @Test
    public void testFetchTopMutualFunds() {
    	System.out.println("+++++++++++++++++++++ Testing Top MutualFunds ++++++++++++++++++++");
        int limit = 2;
        List<MutualFunds> topMutualFunds = mutualFundsRepository.fetchTopMutualFunds(limit);

        assertNotNull(topMutualFunds);
        assertEquals(limit, topMutualFunds.size());
    }
    
    @Test
    public void testBottomMutualFundsDetails() {
    	System.out.println("++++++++++++++++ Testing Bottom MutualFunds ++++++++++++++");
        int limit = 2;
        List<MutualFunds> bottomMutualFunds = mutualFundsRepository.bottomMutualFundsDetails(limit);

        assertNotNull(bottomMutualFunds);
        assertEquals(limit, bottomMutualFunds.size());
    }

    @Test
    @Transactional
    @Disabled
    public void testUpdateData() {
    	System.out.println("+++++++++++++++++++++ Testing Update Price and delta ++++++++++++++++++++");
        String symbol = "HDFC-EQ";
        Double newPrice = 150.0;
        Double newDelta = 15.0;

        try {
        	mutualFundsRepository.updateData(newPrice, newDelta, symbol);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        mutualFundsRepository.flush();
        
        MutualFunds updatedMutualFunds =  mutualFundsRepository.checkSymbol(symbol);
        assertNotNull(updatedMutualFunds);
        System.out.println(updatedMutualFunds.getSymbol()+"    "+updatedMutualFunds.getCurrentPrice());
        
        assertEquals(newPrice, updatedMutualFunds.getCurrentPrice());
        assertEquals(newDelta, updatedMutualFunds.getDelta());
    }

    @Test
    public void testCheckSymbol() {
    	System.out.println("+++++++++++++++++++++ Testing Mutuals Funds By Symbol ++++++++++++++++++++");
        String symbol = "Symbol1";
        MutualFunds mutualFunds = mutualFundsRepository.checkSymbol(symbol);

        assertNotNull(mutualFunds);
        assertEquals(symbol, mutualFunds.getSymbol());
    }

    @Test
    public void testFetchHistory() {
    	System.out.println("+++++++++++++++++++++ Testing History ++++++++++++++++++++");
        String symbol = "Symbol1";
        List<MutualFunds> history = mutualFundsRepository.fetchHistory(symbol);

        assertNotNull(history);
        assertEquals(1, history.size());
        assertEquals(symbol, history.get(0).getSymbol());
    }

}
