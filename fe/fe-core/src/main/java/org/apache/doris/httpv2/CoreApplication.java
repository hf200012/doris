package org.apache.doris.httpv2;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
@Configuration
@EnableCaching
@EnableAutoConfiguration
@EnableScheduling
public class CoreApplication {
}
