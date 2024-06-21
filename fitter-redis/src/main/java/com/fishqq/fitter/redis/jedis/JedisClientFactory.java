package com.fishqq.fitter.redis.jedis;

import com.fishqq.fitter.redis.RedisConfig;

public class JedisClientFactory {
    public static JedisClient create(RedisConfig config) {
        if (config.getHost().contains(",")) {
            return new JedisClusterClient(config);
        } else {
            return new JedisStandaloneClient(config);
        }
    }
}
