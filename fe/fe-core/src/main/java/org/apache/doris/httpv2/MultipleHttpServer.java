package org.apache.doris.httpv2;


import org.apache.doris.PaloFe;
import org.apache.doris.common.Config;
import org.apache.doris.common.FeConstants;
import org.apache.doris.httpv2.config.SpringLog4j2Config;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableConfigurationProperties
@ServletComponentScan
public class MultipleHttpServer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure( SpringApplicationBuilder application) {
        return application.sources(HttpServer.class);
    }

    public void start() {
        Map<String, Object> properties = new HashMap<> ();
        properties.put("server.port", 8030);
        properties.put("server.servlet.context-path", "/");
        properties.put("spring.resources.static-locations", "classpath:/static");
        properties.put("spring.http.encoding.charset", "UTF-8");
        properties.put("spring.http.encoding.enabled", true);
        properties.put("spring.http.encoding.force", true);

        System.setProperty("spring.devtools.restart.enabled", "false");
        // Value of `DORIS_HOME_DIR` is null in unit test.
        if ( PaloFe.DORIS_HOME_DIR != null) {
            System.setProperty("spring.http.multipart.location", PaloFe.DORIS_HOME_DIR);
        }
        System.setProperty("spring.banner.image.location", "doris-logo.png");
        if ( FeConstants.runningUnitTest) {
            // this is currently only used for unit test
            properties.put("logging.config", getClass().getClassLoader().getResource("log4j2.xml").getPath());
        } else {
            properties.put("logging.config", Config.custom_config_dir + "/" + SpringLog4j2Config.SPRING_LOG_XML_FILE);
        }

        Map<String, Object> doris = new HashMap<> ();
        doris.put("server.port", 8040);
        doris.put("server.servlet.context-path", "/");
        doris.put("spring.resources.static-locations", "classpath:/static");
        doris.put("spring.http.encoding.charset", "UTF-8");
        doris.put("spring.http.encoding.enabled", true);
        doris.put("spring.http.encoding.force", true);

        System.setProperty("spring.devtools.restart.enabled", "false");
        // Value of `DORIS_HOME_DIR` is null in unit test.
        if ( PaloFe.DORIS_HOME_DIR != null) {
            System.setProperty("spring.http.multipart.location", PaloFe.DORIS_HOME_DIR);
        }
        System.setProperty("spring.banner.image.location", "doris-logo.png");
        if ( FeConstants.runningUnitTest) {
            // this is currently only used for unit test
            doris.put("logging.config", getClass().getClassLoader().getResource("log4j2.xml").getPath());
        } else {
            doris.put("logging.config", Config.custom_config_dir + "/" + SpringLog4j2Config.SPRING_LOG_XML_FILE);
        }

        new SpringApplicationBuilder().parent(CoreApplication.class).web(WebApplicationType.NONE)
            .child(CloudWebApplication.class).web(WebApplicationType.SERVLET)
            .properties(properties)
            .sibling(DorisApplication.class).web(WebApplicationType.SERVLET)
            .properties(doris)
            .run(new String[]{});

    }

}
