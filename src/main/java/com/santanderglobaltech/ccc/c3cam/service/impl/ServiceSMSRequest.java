package com.santanderglobaltech.ccc.c3cam.service.impl;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.santanderglobaltech.ccc.c3cam.beans.SAPHttpResponse;
import com.santanderglobaltech.ccc.c3cam.beans.SystemBrokers;
import com.santanderglobaltech.ccc.c3cam.configuration.ConfigBroker;
import com.santanderglobaltech.ccc.c3cam.configuration.ConfigConnector;
import com.santanderglobaltech.ccc.c3cam.configuration.ConfigConnectorList;
import com.santanderglobaltech.ccc.c3cam.configuration.HttpConnector;
import com.santanderglobaltech.ccc.c3cam.model.Broker;
import com.santanderglobaltech.ccc.c3cam.model.SMSRequest;
import com.santanderglobaltech.ccc.c3cam.repository.IRepoSMSRequest;
import com.santanderglobaltech.ccc.c3cam.service.IServiceSMSRequest;

import reactor.core.publisher.Flux;

@Service
public class ServiceSMSRequest implements IServiceSMSRequest {

	private static final Logger logSMSReq = LoggerFactory.getLogger(ServiceSMSRequest.class);
	
	private final String SERVICE_USERNAME_LIT  = "{service_username}";
	private final String MOBILE_NOTIFICATION_YES = "Yes";
	private final String ORDER_ID = "ORDERID";
	private final String ACKTYPE_MESSAGE = "Message";
	
	@Autowired
	private ConfigConnectorList cfgConnectors;
	
//	@Autowired
//	@Qualifier(value = "cfgConnectors")
//	private List<ConfigConnector> cfgConnectors2;
	
	@Override
	public Flux<String> testSendRequest() {
//		cfgConnectors.getConfigs().forEach(cfg -> {
//			logSMSReq.info(cfg.toString());
//		});
		
		String brkCode = sysBrokers.selectBroker();
		logSMSReq.info("Chosen Broker (lower): {}", brkCode);
		
		ConfigConnector cfgConnector = cfgConnectors.getConfigs().stream()
			.filter(cfg -> StringUtils.equalsIgnoreCase(cfg.getCode(), brkCode))
			.collect(Collectors.toList()).get(0);
		logSMSReq.info("Connector config: {}", cfgConnector.toString());
		
		Flux<String> sendFlux = WebClient.create(cfgConnector.getUrl())
			      .get()
			      .uri(cfgConnector.getResource())
			      .retrieve()
			      .bodyToFlux(String.class);
		
		return sendFlux;
	}
	
	
	
	@Override
	public List<SMSRequest> listRequests() {
		List<SMSRequest> lstRequests = repoRequest.findAll();
		return lstRequests;
	}
	
//	private	int total = 0;
	
	@Override
	public void sendRequest(SMSRequest req) {
//		List<Broker> lstBrokers = svcBroker.listBrokers();
		
//		private final NavigableMap<Double, E> map = new TreeMap<Double, E>();
//		NavigableMap<Integer, String> mapBrokers = new TreeMap<Integer, String>();
//		
//		sysBrokers.getCachedBrokers().forEach((brk) -> {
//			total += brk.getWeight();
//            mapBrokers.put(total, brk.getCode());
//        });
		
//		total = 0;
		
//		mapBrokers.put((byte)90, "SAP");
//		mapBrokers.put((byte)10, "SIN");
//		mapBrokers.put((byte)39, "VDF");
//		mapBrokers.put((byte)19, "MOV");
		

		
		for (int i=1; i<=100;i++) {
//			int brkSelect = new Random().nextInt(101);
//			logSMSReq.info("Numero: {}", brkSelect);
//			
//			Entry<Integer, String> ret = sysBrokers.getWeightedBrokers().higherEntry(brkSelect);
//			logSMSReq.info("Chosen Broker (higher): {}", ret.getValue());
			
//			Entry<Integer, String> ret2 = mapBrokers.lowerEntry(brkSelect);
//			logSMSReq.info("Chosen Broker (lower): {}", ret2.getValue());
			
			String brkCode = sysBrokers.selectBroker();
			logSMSReq.info("Chosen Broker (lower): {}", brkCode);
			
			ConfigConnector cfgConnector = cfgConnectors.getConfigs().stream()
				.filter(cfg -> StringUtils.equalsIgnoreCase(cfg.getName(), brkCode))
				.collect(Collectors.toList()).get(0);
			
			Flux<String> sendFlux = WebClient.create(cfgConnector.getUrl()) //"http://127.0.0.1:9201")
				      .get()
				      .uri(cfgConnector.getResource())// "send")
				      .retrieve()
				      .bodyToFlux(String.class);
			
//			(cfg -> {
//				logSMSReq.info(cfg.toString());
//			});
		}
		
		
//		mapBrokers.put(key, value)
		
//		Broker brk = svcBroker.findBroker("SAP");
		
//		String sapToken = brk.getToken();
//		String sapEndpoint = brk.getReqEndpoint();
//		
//		String credentials = new String(Base64.getDecoder().decode(sapToken));
//		String username = StringUtils.split(credentials, ':')[0];
//		sapEndpoint = StringUtils.replace(sapEndpoint, "[username]", username);
//		
//		String sapProxy = StringUtils.join(cfgConnector.getProxyHost(), ":", cfgConnector.getProxyPort());
//		
//		
//		String sapMessage = reqToString(req, brk);
//		
//		SAPHttpResponse httpResp = HttpConnector.postProxy(sapProxy, sapEndpoint, sapToken, sapMessage);
	}
	
	@Override
	public SMSRequest addRequest(SMSRequest req) {
		req.setReqDate(LocalDateTime.now());
		
		
		
		SMSRequest reqAux = repoRequest.save(req);
		return reqAux;
	}
	
	
	private String reqToString(SMSRequest req, Broker brk) {
		
		String reqMessage = req.getMessage();
		
		// To keep standard with all operators
		reqMessage = reqMessage.replaceAll("¡", "i")
				.replaceAll("€", "E")
				.replaceAll("ç", "Ç")
				.replaceAll("á", "a")
				.replaceAll("é", "e")
				.replaceAll("í", "i")
				.replaceAll("ó", "o")
				.replaceAll("ú", "u")
				.replaceAll("Ï", "I")
				.replaceAll("ï", "i")
				.replaceAll("\r\n", "<LF>")
				.replaceAll("\n\r", "<LF>");
		
		// Set an expiration time in which if SMS has not been delivered, discard it
		String reqTtl = "2d";
		if (req.getTtl() != -1) {
			if (req.getTtl() < 300) {
				// The smallest TTL allowed by SAP is 5 minutes
				reqTtl = "5m";
			} else {
				reqTtl = String.format("%1$sm", String.valueOf(req.getTtl() / 60));
			}
		}
		
		StringBuilder sbReq = new StringBuilder(System.lineSeparator());
		sbReq.append("List=").append(req.getTo()).append(System.lineSeparator());
		sbReq.append("OriginatingAddress=").append(req.getFrom()).append(System.lineSeparator());
		sbReq.append("Text=").append(reqMessage).append(System.lineSeparator());
		sbReq.append("MobileNotification=").append(MOBILE_NOTIFICATION_YES).append(System.lineSeparator());
		sbReq.append("AckType=").append(ACKTYPE_MESSAGE).append(System.lineSeparator());
		sbReq.append("AckReplyAddress=").append(brk.getNtfEndpoint()).append(System.lineSeparator());
		sbReq.append("ValidityPeriod=").append(reqTtl);
		
		logSMSReq.debug("SMS Request: {}", sbReq.toString());
		
		return sbReq.toString();
	}
	
//	@Autowired
	private ConfigBroker cfgConnector;
	
	@Autowired
	private IRepoSMSRequest repoRequest;
	
	@Autowired
	private ServiceBroker svcBroker; 
	@Autowired
	private SystemBrokers sysBrokers;



}
