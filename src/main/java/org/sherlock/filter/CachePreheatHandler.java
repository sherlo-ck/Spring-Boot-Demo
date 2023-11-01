package org.sherlock.filter;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sherlock.config.RedisAbstractCache;
import org.springframework.context.ApplicationContext;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class CachePreheatHandler implements SmartLifecycle {

    @NonNull
    private ApplicationContext applicationContext;

    private Boolean isRunning = false;

    public RedisAbstractCache getAbstractCache() {
        Map<String, RedisAbstractCache> beansOfType = applicationContext.getBeansOfType(RedisAbstractCache.class);
        for (Map.Entry<String, RedisAbstractCache> entry : beansOfType.entrySet()) {
            return applicationContext.getBean(entry.getValue().getClass());
        }
        return null;
    }

    @Override
    public void start() {
        log.info("开始缓存预热！");
        isRunning = true;
        RedisAbstractCache abstractCache = getAbstractCache();
        abstractCache.init();
    }

    @Override
    public void stop() {
        log.info("清除缓存预热！");
        isRunning = false;
        RedisAbstractCache abstractCache = getAbstractCache();
        abstractCache.clear();
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }
}
