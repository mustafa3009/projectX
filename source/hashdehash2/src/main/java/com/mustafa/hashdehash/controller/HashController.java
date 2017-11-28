package com.mustafa.hashdehash.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.hash.Hashing;
import com.mustafa.hashdehash.MessageNotFoundException;
import com.mustafa.hashdehash.domain.Digest;
import com.mustafa.hashdehash.domain.Message;
import com.mustafa.hashdehash.service.HashService;



@RefreshScope
@RestController
public class HashController {
	
	
	@Autowired
    private DiscoveryClient discoveryClient;

	@Value("${spring.application.name}")
	private String appName;
	
	@Value("${server.port}")
	private String appPort;
	
	
	@RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }

	@Autowired
	private HashService hashService;
	
	//Request handler to register new messages. It calculates the Digest in hex format and stores it in a local cache along with the message.
	@RequestMapping (value="/messages", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Digest setHashFromMsg (@RequestBody Message msg) {
		System.out.println("Received POST request on" + appName + ":" + appPort + " MsgRequest: " + msg.getMessage());
		//Use guava for hashing hash
		String digest = Hashing.sha256()
				  .hashString(msg.getMessage(), StandardCharsets.UTF_8)
				  .toString();
		
		System.out.println("Set message digest:" + digest);
		//Add to hashmap
		hashService.setMessage(digest, msg.getMessage());
		
		//Return digest in object form for automatic JSON conversion
		return new Digest(digest);
	}
	
	//Request handler to retrieve existing messages for supplied digest by searching the local cache. If there is no message found then it returns 404.
	@RequestMapping (value="/messages/{hash}", method=RequestMethod.GET)
	public Message getMsgFromHash (@PathVariable("hash") Digest hash) {
		System.out.println("Received GET request on" + appName + ":" + appPort + " HashRequest:" + hash.getDigest());
		String msg = hashService.getMessage(hash.getDigest());
		
		if ( null != msg) {		
			return new Message(msg);
		}
		else {
			throw new MessageNotFoundException();
		}
	}
	
}
