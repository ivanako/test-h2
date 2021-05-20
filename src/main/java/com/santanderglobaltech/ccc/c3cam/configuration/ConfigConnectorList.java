package com.santanderglobaltech.ccc.c3cam.configuration;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "connectors")
@RefreshScope
public class ConfigConnectorList {

	private List<ConfigConnector> configs = new ArrayList<ConfigConnector>();

	public List<ConfigConnector> getConfigs() {
		return configs;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
