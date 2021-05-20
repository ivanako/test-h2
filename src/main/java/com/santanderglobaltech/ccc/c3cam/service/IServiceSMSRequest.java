package com.santanderglobaltech.ccc.c3cam.service;

import java.util.List;

import com.santanderglobaltech.ccc.c3cam.model.SMSRequest;

import reactor.core.publisher.Flux;

public interface IServiceSMSRequest {

	public List<SMSRequest> listRequests();
	
	public SMSRequest addRequest(SMSRequest req);
	
	public void sendRequest(SMSRequest req);
	
	public Flux<String> testSendRequest();
	
}
