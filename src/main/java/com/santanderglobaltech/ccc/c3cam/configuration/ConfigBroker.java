package com.santanderglobaltech.ccc.c3cam.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class ConfigBroker {

//	@Value(value = "#{'${proxy.host}'}")
	private String proxyHost;
	
//	@Value(value = "proxy.port")
//	@Value(value = "#{new Integer('${proxy.port}')}")
	private int proxyPort;

	public String getProxyHost() {
		return proxyHost;
	}

	public int getProxyPort() {
		return proxyPort;
	}
	
	
}
