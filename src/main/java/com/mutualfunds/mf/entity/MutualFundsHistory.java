package com.mutualfunds.mf.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mutual_funds_history")
public class MutualFundsHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "date_time")
	private LocalDateTime dateTime;
	private Double price;
	private Double delta;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mutual_funds_id")
	private MutualFunds mutualFunds;
	
	
	public MutualFundsHistory(Long id, LocalDateTime dateTime, Double price, Double delta, MutualFunds mutualFunds) {
		super();
		this.id = id;
		this.dateTime = dateTime;
		this.price = price;
		this.delta = delta;
		this.mutualFunds = mutualFunds;
	}


	public MutualFundsHistory() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDateTime getDateTime() {
		return dateTime;
	}


	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public Double getDelta() {
		return delta;
	}


	public void setDelta(Double delta) {
		this.delta = delta;
	}


	public MutualFunds getMutualFunds() {
		return mutualFunds;
	}


	public void setMutualFunds(MutualFunds mutualFunds) {
		this.mutualFunds = mutualFunds;
	}

//
//	@Override
//	public String toString() {
//		return "MutualFundsHistory [id=" + id + ", dateTime=" + dateTime + ", price=" + price + ", delta=" + delta
//				+ ", mutualFunds=" + mutualFunds + "]";
//	}	
	
}
