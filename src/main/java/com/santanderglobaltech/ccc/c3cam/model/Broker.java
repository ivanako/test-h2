package com.santanderglobaltech.ccc.c3cam.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "BROKERS", schema = "PUBLIC")
public class Broker {

	@Id
	@Column(name = "BRK_CODE")
	private String code;
	
	@Column(name = "BRK_NAME")
	private String name;
	
	@Column(name = "BRK_ACTIVE")
	private boolean active;

	@Column(name = "BRK_WEIGHT")
	private byte weight;
	
	@Column(name = "BRK_TOKEN", nullable = true)
	private String token;
	
	@Column(name = "BRK_REQUEST_ENDPOINT")
	private String reqEndpoint;
	
	@Column(name = "CCC_NOTIFICATION_ENDPOINT", nullable = true)
	private String ntfEndpoint;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "CFG_ID", referencedColumnName = "CFG_ID")
//	@JsonProperty(value = "configuration")
//	private BrokerConfig config;
	
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public byte getWeight() {
		return weight;
	}
	public void setWeight(byte weight) {
		this.weight = weight;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getReqEndpoint() {
		return reqEndpoint;
	}
	public void setReqEndpoint(String reqEndpoint) {
		this.reqEndpoint = reqEndpoint;
	}
	
	public String getNtfEndpoint() {
		return ntfEndpoint;
	}
	public void setNtfEndpoint(String ntfEndpoint) {
		this.ntfEndpoint = ntfEndpoint;
	}
	
//	public BrokerConfig getConfig() {
//		return config;
//	}
	
}