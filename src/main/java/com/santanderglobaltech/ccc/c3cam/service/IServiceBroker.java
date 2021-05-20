package com.santanderglobaltech.ccc.c3cam.service;

import java.util.List;

import com.santanderglobaltech.ccc.c3cam.model.Broker;

public interface IServiceBroker {

	public List<Broker> listBrokers();
	
	public Broker findBroker(String brkCode);
	
	public Broker addBroker(Broker brk);
	
	public Broker modifyBroker(Broker brk);
	
	public Broker deactivateBroker(String brkCode);
	
}
