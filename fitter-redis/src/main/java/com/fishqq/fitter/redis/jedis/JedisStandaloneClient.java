package com.fishqq.fitter.redis.jedis;

import com.fishqq.fitter.redis.RedisConfig;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.commands.JedisCommands;

import java.util.function.Consumer;
import java.util.function.Function;

public class JedisStandaloneClient implements JedisClient {
    private final JedisPool jedisPool;

    public JedisStandaloneClient(RedisConfig config) {
        HostAndPort hostAndPort = JedisConfigUtil.parseHostAndPort(config.getHost());
        JedisClientConfig jedisClientConfig = JedisConfigUtil.toJedisClientConfig(config);
        GenericObjectPoolConfig<Jedis> poolConfig = JedisConfigUtil.toPoolConfig(config);
        this.jedisPool = new JedisPool(poolConfig, hostAndPort, jedisClientConfig);
    }

    @Override
    public void exec(Consumer<JedisCommands> handler) {
        try (Jedis jedis = jedisPool.getResource()) {
            handler.accept(jedis);
        }
    }

    @Override
    public <T> T query(Function<JedisCommands, T> handler) {
        try (Jedis jedis = jedisPool.getResource()) {
            return handler.apply(jedis);
        }
    }
}
