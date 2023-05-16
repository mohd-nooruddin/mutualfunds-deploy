package com.mutualfunds.mf.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mutualfunds.mf.entity.MutualFundsHistory;

@Repository
public interface MutualFundsHistoryRepository extends JpaRepository<MutualFundsHistory, Long>{
	
	@Modifying
	@Query (value = "Insert into mutual_funds_history (date_time, delta, price, mutual_funds_id) values (:date,:delta,:price,:id)",nativeQuery = true)
	public void insertHistory(@Param ("date") LocalDateTime dateTime, @Param ("delta") Double delta, @Param ("price") Double price, @Param ("id") Long id);
	
	@Query (value = "Select * from mutual_funds_history where mutual_funds_id = ? ", nativeQuery = true)
	public List<MutualFundsHistory> fetchHistory(Long id);
}
