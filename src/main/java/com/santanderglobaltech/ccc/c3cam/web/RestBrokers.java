package com.santanderglobaltech.ccc.c3cam.web;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.santanderglobaltech.ccc.c3cam.configuration.ConfigSwagger;
import com.santanderglobaltech.ccc.c3cam.model.Broker;
import com.santanderglobaltech.ccc.c3cam.model.BrokerConfig;
import com.santanderglobaltech.ccc.c3cam.service.impl.ServiceBroker;
import com.santanderglobaltech.ccc.c3cam.service.impl.ServiceBrokerConfig;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { ConfigSwagger.TAG_BROKERS })
public class RestBrokers {

	@GetMapping(value = "brokers")
	@ApiOperation(value = "Retrieve all Brokers")
	public ResponseEntity<List<Broker>> listBrokers() {
		List<Broker> lstBrokers = svcBroker.listBrokers();
		return new ResponseEntity<List<Broker>>(lstBrokers, HttpStatus.OK);
	}
	@GetMapping(value = "brokers/{code}")
	@ApiOperation(value = "Look up a Broker")
	public ResponseEntity<Broker> findBroker(String brkCode) {
		Broker brk = svcBroker.findBroker(brkCode);
		return new ResponseEntity<Broker>(brk, HttpStatus.OK);
	}
	@PostMapping(value = "brokers")
	@ApiOperation(value = "Register Broker")
	public ResponseEntity<Broker> addBroker(@RequestBody Broker brk) {
		Broker brkAux = svcBroker.addBroker(brk);
		return new ResponseEntity<Broker>(brkAux, HttpStatus.OK);
	}
	@PutMapping(value = "brokers")
	@ApiOperation(value = "Modify Broker")
	public ResponseEntity<Broker> modifyBroker(@RequestBody Broker brk) {
		Broker brkAux = svcBroker.modifyBroker(brk);
		return new ResponseEntity<Broker>(brkAux, HttpStatus.OK);
	}
	@DeleteMapping(value = "brokers")
	@ApiOperation(value = "Deactivate Broker", hidden = true)
	public ResponseEntity<Broker> deactivateBroker(@RequestBody String brkCode) {
		Broker brkAux = svcBroker.deactivateBroker(StringUtils.upperCase(brkCode));
		return new ResponseEntity<Broker>(brkAux, HttpStatus.OK);
	}
	
//	@PostMapping(value = "broker-configs")
//	@ApiOperation(value = "Add Broker configuration", hidden = true)
//	public ResponseEntity<BrokerConfig> addBrokerConfig(@RequestBody BrokerConfig brkConfig) {
//		BrokerConfig brkConfigAux = svcBrokerConfig.addBrokerConfig(brkConfig);
//		return new ResponseEntity<BrokerConfig>(brkConfigAux, HttpStatus.OK);
//	}
	
	
	
	@Autowired
	private ServiceBroker svcBroker;
//	@Autowired
//	private ServiceBrokerConfig svcBrokerConfig;
}
