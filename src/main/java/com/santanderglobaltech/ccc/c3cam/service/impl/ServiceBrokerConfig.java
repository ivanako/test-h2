package com.santanderglobaltech.ccc.c3cam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santanderglobaltech.ccc.c3cam.model.BrokerConfig;
import com.santanderglobaltech.ccc.c3cam.repository.IRepoBrokerConfig;
import com.santanderglobaltech.ccc.c3cam.service.IServiceBrokerConfig;

//@Service
public class ServiceBrokerConfig implements IServiceBrokerConfig {

	@Override
	public BrokerConfig addBrokerConfig(BrokerConfig brkConfig) {
		BrokerConfig brkConfigAux = repoBrokerConfig.save(brkConfig);
		return brkConfigAux;
	}

	@Autowired
	private IRepoBrokerConfig repoBrokerConfig;
}
