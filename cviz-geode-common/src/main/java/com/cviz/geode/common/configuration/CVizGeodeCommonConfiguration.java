package com.cviz.geode.common.configuration;

import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.cviz.geode.common", "com.cviz.geode.cache"})
public class CVizGeodeCommonConfiguration {

	@Bean(destroyMethod = "close")
	public ClientCache cvizClientCache() {
		ClientCache clientCache = new ClientCacheFactory()
				.set("name", "CVizClientCache")
				.set("cache-xml-file", "cache-client.xml")
				.create();
		return clientCache;
	}
}