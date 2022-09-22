package org.apache.doris.httpv2.doris;

import org.apache.doris.httpv2.entity.ResponseEntityBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/cloud")
public class CloudTestController {

    @RequestMapping (path = "/test", method = RequestMethod.GET)
    public Object test(){
        ResponseEntity entity = ResponseEntityBuilder.ok("test");
        return entity;
    }
}
