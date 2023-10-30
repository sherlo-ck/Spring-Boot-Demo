package org.sherlock.config;


import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class RestTemplateConfig {
    @Bean("myRestTemplate")
    public RestTemplate restTemplate() {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        // 最大连接数
        poolingHttpClientConnectionManager.setMaxTotal(500);
        // 同路由并发数（每个主机的并发）
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(100);
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // 设置http连接管理器
        httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager);
        // 设置重试次数
//        httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(3, true));
        // 设置默认请求头(具体可根据自己业务场景进行设置)
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Content-Type", "application/json"));
        httpClientBuilder.setDefaultHeaders(headers);
        CloseableHttpClient httpClient = httpClientBuilder.build();
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        // httpClient创建器
        clientHttpRequestFactory.setHttpClient(httpClient);
        return new RestTemplate(clientHttpRequestFactory);
    }
}
