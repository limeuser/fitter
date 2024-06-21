package com.fishqq.fitter.redis.jedis;

import com.fishqq.fitter.redis.RedisConfig;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisClientConfig;

import java.util.HashSet;
import java.util.Set;

public class JedisConfigUtil {
    public static Set<HostAndPort> parseHostAndPorts(String host) {
        Set<HostAndPort> hostAndPorts = new HashSet<>();
        String[] words = host.split(",");
        for (String word : words) {
            hostAndPorts.add(parseHostAndPort(word));
        }
        return hostAndPorts;
    }

    public static HostAndPort parseHostAndPort(String value) {
        String[] ipPort = value.split(":");
        return new HostAndPort(ipPort[0].trim(), Integer.parseInt(ipPort[1]));
    }

    public static JedisClientConfig toJedisClientConfig(RedisConfig config) {
        DefaultJedisClientConfig.Builder builder = DefaultJedisClientConfig.builder();

        if (config.getClientName() != null) {
            builder.clientName(config.getClientName());
        }
        if (config.getUser() != null) {
            builder.user(config.getUser());
        }
        if (config.getPassword() != null) {
            builder.password(config.getPassword());
        }
        if (config.getSsl() != null) {
            builder.ssl(config.getSsl());
        }
        if (config.getBlockingSocketTimeoutMillis() != null) {
            builder.blockingSocketTimeoutMillis(config.getBlockingSocketTimeoutMillis());
        }
        if (config.getConnectionTimeoutMillis() != null) {
            builder.connectionTimeoutMillis(config.getConnectionTimeoutMillis());
        }
        if (config.getSocketTimeoutMillis() != null) {
            builder.socketTimeoutMillis(config.getSocketTimeoutMillis());
        }

        return builder.build();
    }

    public static <T> GenericObjectPoolConfig<T> toPoolConfig(RedisConfig config) {
        GenericObjectPoolConfig<T> poolConfig = new GenericObjectPoolConfig<>();

        if (config.getMinIdle() != null) {
            poolConfig.setMinIdle(config.getMinIdle());
        }
        if (config.getMaxIdle() != null) {
            poolConfig.setMaxIdle(config.getMaxIdle());
        }
        if (config.getMaxTotal() != null) {
            poolConfig.setMaxTotal(config.getMaxTotal());
        }

        return poolConfig;
    }
}
