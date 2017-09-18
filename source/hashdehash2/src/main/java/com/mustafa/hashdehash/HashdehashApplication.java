package com.mustafa.hashdehash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import com.mustafa.hashdehash.repository.MessageDigestRepository;
import com.mustafa.hashdehash.repository.KeyspaceRepository;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.UUIDs;

@EnableDiscoveryClient
@SpringBootApplication
public class HashdehashApplication {

	public static void main(String[] args) {
		SpringApplication.run(HashdehashApplication.class, args);
	}
}


