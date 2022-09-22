package org.apache.doris.httpv2;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan ("org.apache.doris.httpv2.cloud")
@EnableCaching
@EnableAutoConfiguration
@EnableScheduling
public class CloudWebApplication {

}
