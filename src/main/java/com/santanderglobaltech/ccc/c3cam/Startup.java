package com.santanderglobaltech.ccc.c3cam;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = { "com.santanderglobaltech.ccc.c3cam.model" })
@EnableJpaRepositories(basePackages = { "com.santanderglobaltech.ccc.c3cam.repository" })
public class Startup {

	private static final Logger log = LoggerFactory.getLogger(Startup.class);
	
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = new SpringApplication(Startup.class).run(args);
		
		Environment env = ctx.getEnvironment();
		
		int appPort = 8080;
		
		try {
        	appPort = env.getProperty("server.port", Integer.class);
        }
        catch (Exception e) {
        	log.error("Property server.port not found");
        };
        
        String extAddress = StringUtils.EMPTY;
        
		try {
			extAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException exUnknown) {
			log.error("Unable to get external host address", exUnknown);
		}
		
		log.info("Access URLs:\n----------------------------------------------------------\n\t"
                + "Local: \t\thttp://{}:{}\n\t"
                + "External: \thttp://{}:{}\n----------------------------------------------------------",
		"127.0.0.1", appPort, extAddress, appPort);
		
	}
}
