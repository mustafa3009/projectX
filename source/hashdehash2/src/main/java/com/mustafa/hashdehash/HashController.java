package com.mustafa.hashdehash;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.hash.Hashing;




@RestController
public class HashController {
	

	@Autowired
	private HashService hashService;
	
	//Request handler to register new messages. It calculates the Digest in hex format and stores it in a local cache along with the message.
	@RequestMapping (value="/messages", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Digest setHashFromMsg (@RequestBody Message msg) {
		//Use guava for hashing hash
		String digest = Hashing.sha256()
				  .hashString(msg.getMessage(), StandardCharsets.UTF_8)
				  .toString();
		
		//Add to hashmap
		hashService.setMessage(digest, msg.getMessage());
		
		//Return digest in object form for automatic JSON conversion
		return new Digest(digest);
	}
	
	//Request handler to retrieve existing messages for supplied digest by searching the local cache. If there is no message found then it returns 404.
	@RequestMapping (value="/messages/{hash}", method=RequestMethod.GET)
	public Message getMsgFromHash (@PathVariable("hash") Digest hash) {
		String msg = hashService.getMessage(hash.getDigest());
		
		if ( null != msg) {		
			return new Message(msg);
		}
		else {
			throw new MessageNotFoundException();
		}
	}
	
}
