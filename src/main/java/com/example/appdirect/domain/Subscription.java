package com.example.appdirect.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Subscription {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	public String companyName;
    public String editionCode;
    public String marketPlaceBaseUrl;
        
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getEditionCode() {
		return editionCode;
	}
	public void setEditionCode(String edition) {
		this.editionCode = edition;
	}
	public String getMarketPlaceBaseUrl() {
		return marketPlaceBaseUrl;
	}
	public void setMarketPlaceBaseUrl(String marketPlaceBaseUrl) {
		this.marketPlaceBaseUrl = marketPlaceBaseUrl;
	}
}
