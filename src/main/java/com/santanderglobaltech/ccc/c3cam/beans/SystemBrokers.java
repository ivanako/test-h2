package com.santanderglobaltech.ccc.c3cam.beans;

import java.util.List;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.santanderglobaltech.ccc.c3cam.model.Broker;
import com.santanderglobaltech.ccc.c3cam.service.impl.ServiceBroker;

//@Component
@Configuration
public class SystemBrokers {

	private static final Logger logSysBrokers = LoggerFactory.getLogger(SystemBrokers.class);
	
	private int weightAux;

	private final Random seed;
	
	private List<Broker> cachedBrokers;
	
	private NavigableMap<Integer, String> weightedBrokers;
	
	
	public SystemBrokers() {
		this.weightAux = 0;
		this.seed = new Random();
		this.weightedBrokers = new TreeMap<Integer, String>();
	}
	
	@PostConstruct
	private void initialise() {
		setCachedBrokers(svcBroker.listBrokers());
		populateWeightedBrokers();
	}
	
	@Bean(name = "cachedBrokers")
	public List<Broker> getCachedBrokers() {
		return cachedBrokers;
	}
	public void setCachedBrokers(List<Broker> cachedBrokers) {
		this.cachedBrokers = cachedBrokers;
	}

	public NavigableMap<Integer, String> getWeightedBrokers() {
		return weightedBrokers;
	}


	private void populateWeightedBrokers() {
		this.cachedBrokers.forEach(brk -> {
			weightAux += brk.getWeight();
			weightedBrokers.put(weightAux, brk.getCode());
        });
	}

	public String selectBroker() {
		int brkSelect = seed.nextInt(100);
		logSysBrokers.info("Seed random: {}", brkSelect);
		
		Entry<Integer, String> brk = this.weightedBrokers.higherEntry(brkSelect);
		
		return brk.getValue();
	}
	
	@Autowired
	private ServiceBroker svcBroker; 
}
