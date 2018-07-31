package com.ssh.jwt.redis;

import redis.clients.jedis.Jedis;

public class RedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        jedis.set("name", "lunjingjie");
        System.out.println(jedis.get("name"));
    }
}
