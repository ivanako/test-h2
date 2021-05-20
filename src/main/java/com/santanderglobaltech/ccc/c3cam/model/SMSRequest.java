package com.santanderglobaltech.ccc.c3cam.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "SMS_REQUESTS", schema = "PUBLIC")
public class SMSRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REQ_ID")
	private int id;
	
	@Column(name = "REQ_FROM")
	@NotBlank(message = "This field must not be empty")
	private String from;
	
	@Column(name = "REQ_TO")
	@NotBlank(message = "This field must not be empty")
	private String to;
	
	@Column(name = "REQ_TTL")
	private short ttl;

	@Transient
	@NotBlank(message = "This field must not be empty")
	private String message;
	
	@Column(name = "REQ_DATE_REQUEST")
	private LocalDateTime reqDate;
	
	@Column(name = "REQ_DATE_ACKNOWLEDGE")
	private LocalDateTime ackDate;
	

	public int getId() {
		return id;
	}
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}

	public short getTtl() {
		return ttl;
	}
	public void setTtl(short ttl) {
		this.ttl = ttl;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}

	public LocalDateTime getReqDate() {
		return reqDate;
	}
	public void setReqDate(LocalDateTime reqDate) {
		this.reqDate = reqDate;
	}

	public LocalDateTime getAckDate() {
		return ackDate;
	}
}
