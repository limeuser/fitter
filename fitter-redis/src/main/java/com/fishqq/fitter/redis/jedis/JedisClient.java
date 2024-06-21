package com.fishqq.fitter.redis.jedis;

import redis.clients.jedis.commands.JedisCommands;

import java.util.function.Consumer;
import java.util.function.Function;

public interface JedisClient {
    void exec(Consumer<JedisCommands> handler);

    <T> T query(Function<JedisCommands, T> handler);
}
