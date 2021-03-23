package com.redditpost.step;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.batch.item.ItemWriter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Writer implements ItemWriter<String> {

	private Scanner sc;
	private String title;
	private String link;
	
	private Map<String,String> postContent=new LinkedHashMap<String, String>();
	@Override
	public void write(List<? extends String> messages) throws Exception {
		sc=new Scanner(messages.get(0));
		sc.useDelimiter("!");
		while(sc.hasNext()) {
			title=sc.next();
			link=sc.next();
			String authToken=getAuthToken();
			postToSubreddit(authToken,title,link);
		}
		
		
	}

	private void postToSubreddit(String authToken, String title, String link) {
		RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Authorization", "Bearer " + authToken);
	    headers.put("User-Agent",
	    Collections.singletonList("tomcat:com.redditpost (by /u/ShadabKazi)"));
	    headers.add("Content-Type", "application/x-www-form-urlencoded");
	    String url = "https://oauth.reddit.com/api/submit";
	  
	    
	    MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
	    map.add("kind", "link");
	    map.add("sr", "Technology_Geeks");
	    map.add("title", title);
	    map.add("url", link);
	    
	    HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
	    String response
	            = restTemplate.postForObject(url, entity, String.class);
	}

	private String getAuthToken(){
	    RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    
	    
	    String plainCreds = "yourCreds";
	    byte[] plainCredsBytes = plainCreds.getBytes();
	    byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
	    String base64Creds = new String(base64CredsBytes);
	    
	    headers.add("Authorization", "Basic " + base64Creds);
	    
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	    headers.put("User-Agent",
	            Collections.singletonList("tomcat:com.redditpost (by /u/ShadabKazi)"));
	    String body = "grant_type=password&username=Shadabkazi&password=tempPassword";
	    HttpEntity<String> request
	            = new HttpEntity<>(body, headers);
	    String authUrl = "https://www.reddit.com/api/v1/access_token";
	    System.out.println(request);
	    ResponseEntity<String> response = restTemplate.postForEntity(
	            authUrl, request, String.class);
	    ObjectMapper mapper = new ObjectMapper();
	    Map<String, Object> map = new HashMap<>();
	    try {
	        map.putAll(mapper
	                .readValue(response.getBody(), new TypeReference<Map<String,Object>>(){}));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    System.out.println(response.getBody());
	    return String.valueOf(map.get("access_token"));
	}
	
}
