package org.sherlock.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.sherlock.service.TestRestTemplateService;
import org.sherlock.utils.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Slf4j
public class TestRestTemplateServiceImpl implements TestRestTemplateService {
    @Value("${test.url}")
    private String url;
    @Autowired
    @Qualifier("myRestTemplate")
    private RestTemplate restTemplate;
    @Override
    public ReturnMsg getRequest(Map<String, Object> entityParam) {
        ResponseEntity<ReturnMsg> returnEntity = restTemplate.getForEntity(url, ReturnMsg.class, entityParam);
        log.info("head{}",returnEntity.getHeaders());
        if (returnEntity.getStatusCodeValue() == 200) {
            return returnEntity.getBody();
        } else {
            throw new RuntimeException("接口调用异常");
        }
    }
}
