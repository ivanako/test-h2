package com.santanderglobaltech.ccc.c3cam.configuration;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.AuthCache;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.santanderglobaltech.ccc.c3cam.beans.SAPHttpResponse;

//import lombok.extern.slf4j.Slf4j;

/**
 * Provide methods to perform HTTP operations
 */
public class HttpConnector {

	private static final Logger logHttp = LoggerFactory.getLogger(HttpConnector.class);

	public static SAPHttpResponse postProxy(String urlProxy, String urlEndpoint, String postAuth, String postBody) {
		
		SAPHttpResponse sapResp = null;

		// Generate an HttpHost representing a Proxy, out of its URL
		HttpHost proxy = null;
		
		URI uriProxy = buildUri(urlProxy);
		
		if (StringUtils.isBlank(uriProxy.getHost())) {
			String[] prxFeatures = StringUtils.split(urlProxy, ':');
			proxy = new HttpHost(prxFeatures[0], Integer.parseInt(prxFeatures[1]));
			logHttp.info("Proxy configuration - Host: {} # Port: {}", prxFeatures[0], Integer.parseInt(prxFeatures[1]));
		} else {
			proxy = new HttpHost(uriProxy.getHost(), uriProxy.getPort(), uriProxy.getScheme());
			logHttp.debug("Proxy configuration - Scheme: {} # Host: {} # Port: {}", uriProxy.getScheme(), uriProxy.getHost(), uriProxy.getPort());
		}
		
		// Generate Endpoint URI out of its URL
		URI uriEndpoint = buildUri(urlEndpoint);
//		logHttp.debug("Endpoint configuration - Scheme: {} # Host: {} # Port: {}", uriEndpoint.getScheme(), uriEndpoint.getHost(), uriEndpoint.getPort());
		
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
        	// Specify Proxy
            RequestConfig reqConfig = RequestConfig.custom().setProxy(proxy).build();
            
            String hdrAuth = String.format("Basic %1$s", postAuth);
            
            HttpPost reqPost = new HttpPost(uriEndpoint);
            reqPost.setHeader("Authorization", hdrAuth);
            reqPost.setConfig(reqConfig);
            reqPost.setEntity(new StringEntity(postBody));
            
            logHttp.debug("Executing request {} to {} via {}", reqPost.getRequestLine(), urlEndpoint, proxy);

            try (CloseableHttpResponse respPost = httpClient.execute(reqPost)) {
            	logHttp.debug("----------------------------------------");
            	logHttp.debug("POST result: {}", respPost.getStatusLine());
                
            	int respCode = respPost.getStatusLine().getStatusCode();
            	String postEntity = EntityUtils.toString(respPost.getEntity(), StandardCharsets.UTF_8);
                
            	sapResp = new SAPHttpResponse();
            	sapResp.setCode(respCode);
            	sapResp.setDesc(postEntity);
                
                logHttp.debug(postEntity);
            } 
        } catch (IOException exIO) {
        	logHttp.error("Error while closing HTTP object");
			exIO.printStackTrace();
		}
        
        return sapResp;
	}
	
	
	
	/**
	 * Perform a POST method through a Proxy
	 * 
	 * @param urlProxy Proxy URL
	 * @param urlEndpoint Endpoint URL
	 * @param postBody Request body
	 * 
	 * @return POST operation result
	 */
//	public static String postProxy(String urlProxy, String urlEndpoint, String postBody) {
//		
//		String postEntity = StringUtils.EMPTY;
//
//		// Generate an HttpHost representing a Proxy, out of its URL
//		HttpHost proxy = null;
//		
//		URI uriProxy = buildUri(urlProxy);
//		
//		if (StringUtils.isBlank(uriProxy.getHost())) {
//			String[] prxFeatures = StringUtils.split(urlProxy, ':');
//			proxy = new HttpHost(prxFeatures[0], Integer.parseInt(prxFeatures[1]));
//			logHttp.debug("Proxy configuration - Host: {} # Port: {}", prxFeatures[0], Integer.parseInt(prxFeatures[1]));
//		} else {
//			proxy = new HttpHost(uriProxy.getHost(), uriProxy.getPort(), uriProxy.getScheme());
//			logHttp.debug("Proxy configuration - Scheme: {} # Host: {} # Port: {}", uriProxy.getScheme(), uriProxy.getHost(), uriProxy.getPort());
//		}
//		
//		// Generate Endpoint URI out of its URL
//		URI uriEndpoint = buildUri(urlEndpoint);
////		logHttp.debug("Endpoint configuration - Scheme: {} # Host: {} # Port: {}", uriEndpoint.getScheme(), uriEndpoint.getHost(), uriEndpoint.getPort());
//		
//        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
//        	// Specify Proxy
//            RequestConfig reqConfig = RequestConfig.custom().setProxy(proxy).build();
//            
//            HttpPost reqPost = new HttpPost(uriEndpoint);
//            reqPost.setConfig(reqConfig);
//            reqPost.setEntity(new StringEntity(postBody));
//            
//            logHttp.debug("Executing request {} to {} via {}", reqPost.getRequestLine(), urlEndpoint, proxy);
//
//            try (CloseableHttpResponse respPost = httpClient.execute(reqPost)) {
//            	logHttp.debug("----------------------------------------");
//            	logHttp.debug("POST result: {}", respPost.getStatusLine());
//                
//                postEntity = EntityUtils.toString(respPost.getEntity(), StandardCharsets.UTF_8);
//                
//                logHttp.debug(postEntity);
//            } 
//        } catch (IOException exIO) {
//        	logHttp.error("Error while closing HTTP object");
//			exIO.printStackTrace();
//		}
//        
//        return postEntity;
//	}
	
	
	
	private static URI buildUri(String url) {
		return URI.create(url);
	}
	private static HttpClientContext buildContext(URI uri) {
		// Host definition
		HttpHost httpHost = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());

		// Generate BASIC scheme object and add it to the local auth cache
		BasicScheme basicAuth = new BasicScheme();

		// Create AuthCache instance
		AuthCache authCache = new BasicAuthCache();
		authCache.put(httpHost, basicAuth);

		HttpClientContext ctx = HttpClientContext.create();
		ctx.setAuthCache(authCache);

		return ctx;
	}
}
