package org.sherlock.controller.requesttest;

import lombok.extern.slf4j.Slf4j;
import org.sherlock.service.TestRestTemplateService;
import org.sherlock.utils.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/request")
@Slf4j
public class RequestController {

    @Autowired
    private TestRestTemplateService service;

    @PostMapping("/get")
    public ReturnMsg getRequest(@RequestBody Map<String, Object> entityParam) {
        return service.getRequest(entityParam);
    }

}
