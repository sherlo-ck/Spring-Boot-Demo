package org.sherlock.filter;

import com.alibaba.fastjson.JSON;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sherlock.config.RedisAbstractCache;
import org.sherlock.model.User;
import org.sherlock.service.SpringBootDemoService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class SpringBootDemoCache extends RedisAbstractCache {

    private static final String REDIS_KEY = "springBootDemo";

    @NonNull
    @Qualifier("redisBean")
    private RedisTemplate<String, Object> redisTemplate;

    @NonNull
    private SpringBootDemoService springBootDemoService;
    /**
     * 初始化缓存
     * 加载mysql表所有缓存待redis中
     */
    @Override
    public void init() {
        // 查询需要缓存的数据
        List<User> userCache = springBootDemoService.getString();
        log.info("查询mysql的数据为: {}", JSON.toJSONString(userCache));
        // 存入redis
        Map<String, String> collect = userCache.stream().collect(Collectors.toMap(User::StringValue, User::toString));
        redisTemplate.opsForHash().putAll(REDIS_KEY, collect);
        log.info("key:{}, HashKey:{}, value:{}", REDIS_KEY, redisTemplate.opsForHash().keys(REDIS_KEY), redisTemplate.opsForHash().multiGet(REDIS_KEY, Arrays.asList(collect.keySet().toArray())));
    }

    @Override
    public <T> T get() {
        return null;
    }

    @Override
    public void clear() {
        redisTemplate.opsForHash().delete(REDIS_KEY, redisTemplate.opsForHash().keys(REDIS_KEY).toArray());
    }

    @Override
    public void reload() {

    }
}
