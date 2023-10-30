package org.sherlock.controller.resttemplate;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NoHttpResponseException;
import org.sherlock.service.RetryService;
import org.sherlock.utils.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/retry")
@Slf4j
@Api(value = "retry", tags = {"retry"})
public class RetryAbleController {

    @Autowired
    private RetryService retryService;

    @GetMapping("/callPost")
    public void CallPostGetExceptionRetry() throws NoHttpResponseException {
            retryService.callPost();
    }
}
