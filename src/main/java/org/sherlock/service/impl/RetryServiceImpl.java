package org.sherlock.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.NoHttpResponseException;
import org.sherlock.service.RetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetrySynchronizationManager;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class RetryServiceImpl implements RetryService {

    @Autowired
    @Qualifier("myRestTemplate")
    private RestTemplate restTemplate;
    /**
     * 测试 SpringBoot Retry重试机制
     */
    @Override
    @Retryable(value = {NoHttpResponseException.class}, maxAttempts = 5, backoff = @Backoff(delay = 1000L, multiplier = 1.5))
    public void callPost() throws NoHttpResponseException {
        log.info("抛出异常");
        log.info("已经重试：{}次了", RetrySynchronizationManager.getContext().getRetryCount() + 1);
        throw new NoHttpResponseException("获取连接失败");
    }

//    @Recover
//    public void recover(NoHttpResponseException e) {
//        log.info("重试失败后进入回调方法");
//    }
}
