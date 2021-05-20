package com.santanderglobaltech.ccc.c3cam.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.santanderglobaltech.ccc.c3cam.configuration.ConfigSwagger;
import com.santanderglobaltech.ccc.c3cam.model.SMSRequest;
import com.santanderglobaltech.ccc.c3cam.service.impl.ServiceSMSRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import reactor.core.publisher.Flux;

@RestController
@Api(tags = { ConfigSwagger.TAG_SMS_REQUESTS })
public class RestSMSRequest {

	private static final Logger logSMS = LoggerFactory.getLogger(RestSMSRequest.class);
	
	@GetMapping(value = "requests", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Retrieve all SMS Requests")
	public ResponseEntity<List<SMSRequest>> listRequests() {
		List<SMSRequest> lstRequests = svcRequest.listRequests();
		return new ResponseEntity<List<SMSRequest>>(lstRequests, HttpStatus.OK);
	}

	@PostMapping(value = "requests", consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Register SMS Request sending")
	public ResponseEntity<SMSRequest> addRequest(
			@RequestBody @ApiParam(value = "SMS Request definition", required = true) @Valid SMSRequest req) {
		SMSRequest reqAux = svcRequest.addRequest(req);
		return new ResponseEntity<SMSRequest>(reqAux, HttpStatus.CREATED);
	}
	
	@PostMapping(value = "requests/send", consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Send SMS Request")
	public ResponseEntity<SMSRequest> sendRequest(
			@RequestBody @ApiParam(value = "SMS Request definition", required = true) @Valid SMSRequest req) {
		svcRequest.sendRequest(req);
		return new ResponseEntity<SMSRequest>(HttpStatus.CREATED);
	}
	
	
	@GetMapping(value = "test")
	public Flux<String> test() {
		Flux<String> sendFlux = svcRequest.testSendRequest();
		return sendFlux;
//		return new ResponseEntity<String>("Hola", HttpStatus.OK);
	}
	
//	@GetMapping(value = "sap")
//	public Flux<String> sendSAP() {
//		Flux<String> sendFlux = WebClient.create(cfgConn.getSapUrl()) //"http://127.0.0.1:9201")
//			      .get()
//			      .uri(cfgConn.getSapResource())// "send")
//			      .retrieve()
//			      .bodyToFlux(String.class);
////			      .exchange()
////			      .flatMap(response -> response.bodyToFlux(String.class))
////			      .subscribe(person -> System.out.println("GET: " + person));
//
////			      .subscribe(pepe -> logSMS.info(pepe.toString()));
////		logSMS.info("Devuelve: {}", sendFlux.blockFirst());
//		 
////		sendFlux.subscribe(resp -> logSMS.info(resp.toString()));
//		
//		return sendFlux;
//	}
//	@GetMapping(value = "sinch")
//	public Flux<String> sendSinch() {
//		Flux<String> sendFlux = WebClient.create(cfgConn.getSinUrl()) //"http://127.0.0.1:9201")
//			      .get()
//			      .uri(cfgConn.getSinResource())// "send")
//			      .retrieve()
//			      .bodyToFlux(String.class);
//		
//		return sendFlux;
//	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleNotValidRequests(MethodArgumentNotValidException exNotValid) {
		Map<String, String> mapErrors = new HashMap<>();
		
		exNotValid.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errMessage = error.getDefaultMessage();
			
			mapErrors.put(fieldName, errMessage);
		});
		
		return mapErrors;
	}
	
	
	
	@Autowired
	private ServiceSMSRequest svcRequest;
	
//	@Autowired
//	private ConfigConnectors cfgConn;
	
	
}
