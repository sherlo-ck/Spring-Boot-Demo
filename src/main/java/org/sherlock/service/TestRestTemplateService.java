package org.sherlock.service;

import org.sherlock.utils.ReturnMsg;

import java.util.Map;

public interface TestRestTemplateService {
    ReturnMsg getRequest(Map<String, Object> entityParam);
}
