package com.fishqq.fitter.example;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fishqq.fitter.config.LogbackConfigLoader;
import com.fishqq.fitter.config.Profile;
import com.fishqq.fitter.kafka.CommitMode;
import com.fishqq.fitter.kafka.KafkaConsumerClient;
import com.zaxxer.hikari.HikariConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Optional;

public class ExampleApplication {
    public static void main(String[] args) throws Exception {
        String activeProfile = Profile.activeOrDefault();
        System.out.println("use active profile: " + activeProfile);

        System.out.println("start load logback config");
        LogbackConfigLoader.loadActiveOrDefault();
        System.out.println("finish load logback config");

        Logger logger = LoggerFactory.getLogger(ExampleApplication.class);

        logger.info("start load application config");
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.findAndRegisterModules();

        ExampleConfigLoader configLoader = new ExampleConfigLoader();
        ExampleAppConfig config = ExampleAppConfig.createDefault();

        Optional<String> defaultConfigText = configLoader.loadDefault();
        if (defaultConfigText.isPresent()) {
            mergeFileConfig(config, defaultConfigText.get(), objectMapper);
        }

        Optional<String> profileConfigText = configLoader.loadActive();
        if (profileConfigText.isPresent()) {
            mergeFileConfig(config, profileConfigText.get(), objectMapper);
        }

        if (!defaultConfigText.isPresent() && !profileConfigText.isPresent()) {
            throw new RuntimeException("can't find application config file");
        }

        objectMapper.enable(JsonParser.Feature.ALLOW_YAML_COMMENTS);
        logger.info("finish load application config:\n{}", objectMapper.writeValueAsString(config));

        // start kafka
        KafkaConsumerClient<String, String> client = new KafkaConsumerClient<>(
                Collections.singletonList("t1"),
                config.getKafka().getConsumer(),
                records -> CommitMode.SYNC);

        client.start();

        // start redis
//        JedisClient jedisClient = JedisClientFactory.create(config.getRedis());
//        jedisClient.query()

        // exec mapper
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setMinimumIdle(config.getDataSourcePool().getMinIdle());
        hikariConfig.setMaximumPoolSize(config.getDataSourcePool().getMaxTotal());
        hikariConfig.setJdbcUrl(config.getDataSource().getUrl());
        hikariConfig.setUsername(config.getDataSource().getUsername());
        hikariConfig.setPassword(config.getDataSource().getPassword());

//        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
//        Configuration configuration = MybatisConfigUtil.create(dataSource, config.getMybatis());
//        MapperExecutor mapperExecutor = new MapperExecutor(new SqlSessionFactoryBuilder().build(configuration));
    }

    private static void mergeFileConfig(ExampleAppConfig config,
                                        String configText,
                                        ObjectMapper objectMapper) throws JsonProcessingException {
        ExampleAppConfig fileConfig = objectMapper.readValue(
                configText,
                new TypeReference<ExampleAppConfig>() {
                });
        config.merge(fileConfig);
    }
}