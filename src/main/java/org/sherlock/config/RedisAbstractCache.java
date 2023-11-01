package org.sherlock.config;

public abstract class RedisAbstractCache {

    /**
     * redis缓存初始化
     */
    public abstract void init();

    /**
     * 获取redis缓存
     * @return redis缓存结果
     * @param <T> Class
     */
    public abstract <T> T get();

    /**
     * 删除redis缓存
     */
    public abstract void clear();

    /**
     * 重新加载缓存
     */
    public abstract void reload();
}
