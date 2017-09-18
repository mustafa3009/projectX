package com.mustafa.hashdehash.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.datastax.driver.core.Session;
import com.mustafa.hashdehash.domain.MessageDigest;
import com.mustafa.hashdehash.repository.KeyspaceRepository;
import com.mustafa.hashdehash.repository.MessageDigestRepository;

//The Hash Service is a singleton which implements a Hashmap to store the messages and associated digests. 
//The values are preserved across REST request invocations but not service restarts 
@Service
public class HashService {
	// private Map <String, String> hm = new HashMap<String, String>();
	
	private MessageDigestRepository mdr;
	
	@Value("${cassandra.host}")
	private String cassandraHost="localhost";
	
	@PostConstruct
	public void init() {
		CassandraConnector connector = new CassandraConnector();
        System.out.println("Connecting to cassandra." + " Host:" + cassandraHost);
        connector.connect(cassandraHost, null);
       Session session = connector.getSession();

        KeyspaceRepository sr = new KeyspaceRepository(session);
        sr.createKeyspace("paxos", "SimpleStrategy", 1);
        sr.useKeyspace("paxos");

        mdr = new MessageDigestRepository(session);
        mdr.createTable();
	}
	
	//This method adds/updates the message and its digest
	public void setMessage (String digest, String message) {
		//hm.put(digest, message);
		
		MessageDigest md = new MessageDigest (message, digest);
		mdr.insertmsgDigest(md);
	}

	
	//This method retrieves the stored message for the supplied digest 
	public String getMessage (String digest) {
		try {
			
			return (mdr.getMessage(digest).getMessage());
		}
		catch (Exception E) {
			return null;
		}
		
		//return hm.get(digest);
	}
}
