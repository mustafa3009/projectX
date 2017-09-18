package com.mustafa.hashdehash.component;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:cassandra.properties")
@ConfigurationProperties(prefix="cassandra")
public class ConfigProperties {
 
    private String host;
    private int Port;
    
}


