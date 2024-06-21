package com.fishqq.fitter.example;

import com.fishqq.fitter.config.DataSourceConfig;
import com.fishqq.fitter.config.DataSourcePoolConfig;
import com.fishqq.fitter.kafka.KafkaConfig;
import com.fishqq.fitter.mybatis.MybatisConfig;
import com.fishqq.fitter.redis.RedisConfig;

public class ExampleAppConfig {
    private ServiceConfig service;
    private KafkaConfig kafka;
    private RedisConfig redis;
    private MybatisConfig mybatis;
    private DataSourceConfig dataSource;
    private DataSourcePoolConfig dataSourcePool;

    public static ExampleAppConfig createDefault() {
        return new ExampleAppConfig()
                .setService(ServiceConfig.createDefault())
                .setKafka(KafkaConfig.createDefault())
                .setRedis(RedisConfig.createDefault())
                .setMybatis(MybatisConfig.createDefault())
                .setDataSource(DataSourceConfig.createDefault())
                .setDataSourcePool(DataSourcePoolConfig.createDefault());
    }

    public ServiceConfig getService() {
        return service;
    }

    public ExampleAppConfig setService(ServiceConfig service) {
        this.service = service;
        return this;
    }

    public KafkaConfig getKafka() {
        return kafka;
    }

    public ExampleAppConfig setKafka(KafkaConfig kafka) {
        this.kafka = kafka;
        return this;
    }

    public RedisConfig getRedis() {
        return redis;
    }

    public ExampleAppConfig setRedis(RedisConfig redis) {
        this.redis = redis;
        return this;
    }

    public MybatisConfig getMybatis() {
        return mybatis;
    }

    public ExampleAppConfig setMybatis(MybatisConfig mybatis) {
        this.mybatis = mybatis;
        return this;
    }

    public DataSourceConfig getDataSource() {
        return dataSource;
    }

    public ExampleAppConfig setDataSource(DataSourceConfig dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public DataSourcePoolConfig getDataSourcePool() {
        return dataSourcePool;
    }

    public ExampleAppConfig setDataSourcePool(DataSourcePoolConfig dataSourcePool) {
        this.dataSourcePool = dataSourcePool;
        return this;
    }

    public static class ServiceConfig {
        private String name;

        public static ServiceConfig createDefault() {
            return new ServiceConfig().setName("example");
        }

        public void merge(ServiceConfig other) {
            if (other == null) {
                return;
            }

            if (other.getName() != null) {
                this.name = other.getName();
            }
        }

        public String getName() {
            return name;
        }

        public ServiceConfig setName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public String toString() {
            return "ServiceConfig{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public void merge(ExampleAppConfig other) {
        if (other == null) {
            return;
        }

        if (other.getService() != null) {
            this.service.merge(other.getService());
        }
        if (other.getKafka() != null) {
            this.kafka.merge(other.getKafka());
        }
        if (other.getRedis() != null) {
            this.redis.merge(other.getRedis());
        }
        if (other.getMybatis() != null) {
            this.mybatis.merge(other.getMybatis());
        }
        if (other.getDataSource() != null) {
            this.dataSource.merge(other.getDataSource());
        }
        if (other.getDataSourcePool() != null) {
            this.dataSourcePool.merge(other.getDataSourcePool());
        }
    }

    @Override
    public String toString() {
        return "ExampleAppConfig{" +
                "service=" + service +
                ", kafka=" + kafka +
                ", redis=" + redis +
                ", mybatis=" + mybatis +
                ", dataSource=" + dataSource +
                ", dataSourcePool=" + dataSourcePool +
                '}';
    }
}
