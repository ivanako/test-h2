package com.santanderglobaltech.ccc.c3cam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
//@Table(name = "BROKERS_CONFIG", schema = "PUBLIC")
public class BrokerConfig {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CFG_ID")
	private String id;
	
	@Column(name = "CFG_TOKEN", nullable = true)
	private String token;
	
	@Column(name = "CFG_REQUEST_ENDPOINT")
	private String reqEndpoint;
	
	@Column(name = "CFG_NOTIFICATION_ENDPOINT", nullable = true)
	private String ntfEndpoint;
	
	
//	@OneToOne(mappedBy = "config")
//	private Broker brk;


	public String getId() {
		return id;
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
	
}
