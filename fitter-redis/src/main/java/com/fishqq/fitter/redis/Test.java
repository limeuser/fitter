package com.fishqq.fitter.redis;

import com.fishqq.fitter.redis.jedis.JedisClient;
import com.fishqq.fitter.redis.jedis.JedisClientFactory;

import java.util.concurrent.atomic.AtomicLong;

public class Test {
    public static void main(String[] args) throws Exception {
        RedisConfig config = new RedisConfig();
        config.setHost("172.29.188.32:7001, 172.29.188.32:7002, 172.29.188.32:7003, 172.29.188.32:7004, 172.29.188.32:7005, 172.29.188.32:7006");
        config.setClientName("test");
        config.setConnectionTimeoutMillis(10000);
        config.setSocketTimeoutMillis(10000);
        config.setBlockingSocketTimeoutMillis(10000);

        config.setMinIdle(1);
        config.setMaxIdle(3);
        config.setMaxTotal(10);

        JedisClient jedisClient = JedisClientFactory.create(config);

        AtomicLong i = new AtomicLong(0);
        while (true) {
            jedisClient.exec(commands -> {
                try {
                    commands.set("abc", "123");
                    System.out.println("ok: " + i.incrementAndGet());
                } catch (Exception e) {
                    System.out.println("error: " + i.incrementAndGet() + ": " + e.getMessage());
                }
            });
            Thread.sleep(1000);
        }
    }
}
