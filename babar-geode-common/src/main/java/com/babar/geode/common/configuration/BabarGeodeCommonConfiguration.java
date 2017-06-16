package com.babar.geode.common.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("client-cache.xml")
public class BabarGeodeCommonConfiguration {

}