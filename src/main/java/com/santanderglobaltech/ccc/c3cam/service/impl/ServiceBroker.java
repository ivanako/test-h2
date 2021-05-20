package com.santanderglobaltech.ccc.c3cam.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santanderglobaltech.ccc.c3cam.model.Broker;
import com.santanderglobaltech.ccc.c3cam.repository.IRepoBroker;
import com.santanderglobaltech.ccc.c3cam.service.IServiceBroker;

@Service
public class ServiceBroker implements IServiceBroker {

	@Override
	public List<Broker> listBrokers() {
		List<Broker> lstBrokers = repoBroker.findAll();
		return lstBrokers;
	}
	@Override
	public Broker findBroker(String brkCode) {
		Optional<Broker> brk = repoBroker.findById(StringUtils.upperCase(brkCode));
		return brk.get();
	}
	
	@Override
	public Broker addBroker(Broker brk) {
		Broker brkAux = repoBroker.save(brk);
		return brkAux;
	}
	@Override
	public Broker modifyBroker(Broker brk) {
		Broker brkAux = repoBroker.save(brk);
		return brkAux;
	}

	@Override
	public Broker deactivateBroker(String brkCode) {
		int brkcount = repoBroker.deactivateBroker(brkCode);
		Optional<Broker> brk = repoBroker.findById(brkCode);
		return brk.get();
	}
	
	@Autowired
	private IRepoBroker repoBroker;

}
