package com.fishqq.fitter.redis.jedis;

import com.fishqq.fitter.redis.RedisConfig;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Connection;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.commands.JedisCommands;

import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

public class JedisClusterClient implements JedisClient {
    private final JedisCluster jedisCluster;

    public JedisClusterClient(RedisConfig config) {
        Set<HostAndPort> hostAndPorts = JedisConfigUtil.parseHostAndPorts(config.getHost());
        JedisClientConfig jedisClientConfig = JedisConfigUtil.toJedisClientConfig(config);
        GenericObjectPoolConfig<Connection> poolConfig = JedisConfigUtil.toPoolConfig(config);
        this.jedisCluster = new JedisCluster(hostAndPorts, jedisClientConfig, poolConfig);
    }

    @Override
    public void exec(Consumer<JedisCommands> handler) {
        handler.accept(jedisCluster);
    }

    public <T> T query(Function<JedisCommands, T> handler) {
        return handler.apply(jedisCluster);
    }
}
