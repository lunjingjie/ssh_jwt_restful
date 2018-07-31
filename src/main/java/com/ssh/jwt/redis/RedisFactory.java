package com.ssh.jwt.redis;

import redis.clients.jedis.Jedis;

/**
 * 缓存token到redis数据库
 * @author Administrator
 */
public class RedisFactory {

    private static Jedis jedis = null;

    private RedisFactory() {
    }

    public static Jedis getInstance() {
        if (jedis == null) {
            jedis = new Jedis("localhost");
        }
        return jedis;
    }

    public void putInRedis(String key, String value) {
        jedis.set(key, value);
    }

    public String getTokenFromRedis(String key) {
        return jedis.get(key);
    }
}
