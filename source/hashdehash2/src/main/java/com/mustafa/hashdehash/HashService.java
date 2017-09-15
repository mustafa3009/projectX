package com.mustafa.hashdehash;

import java.util.HashMap;
import java.util.Map;


import org.springframework.stereotype.Service;

//The Hash Service is a singleton which implements a Hashmap to store the messages and associated digests. 
//The values are preserved across REST request invocations but not service restarts 
@Service
public class HashService {
	private Map <String, String> hm = new HashMap<String, String>();
	
	//This method adds/updates the message and its digest
	public void setMessage (String digest, String message) {
		hm.put(digest, message);
	}
	
	//This method retrieves the stored message for the supplied digest 
	public String getMessage (String digest) {
		return hm.get(digest);
	}
	
}
