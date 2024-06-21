package com.fishqq.fitter.kafka;

import com.fishqq.fitter.config.PropertyWriter;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class KafkaConsumerConfig {
    private String groupId;
    private String clientId;
    private List<String> bootstrapServers;
    private Integer autoCommitInterval;

    private String autoOffsetReset;
    private Boolean enableAutoCommit;
    private Integer fetchMaxWait;
    private Integer fetchMinSize;

    private Integer heartbeatInterval;
    private String isolationLevel;
    private String keyDeserializer;
    private String valueDeserializer;
    private Integer maxPollRecords;

    private KafkaClientConfig client = new KafkaClientConfig();
    private Map<String, String> properties = new HashMap<>();

    public static KafkaConsumerConfig createDefault() {
        return new KafkaConsumerConfig()
                .setBootstrapServers(Collections.singletonList(KafkaConfig.DEFAULT_SERVER))
                .setEnableAutoCommit(true)
                .setKeyDeserializer(KafkaConfig.STRING_DESERIALIZER)
                .setValueDeserializer(KafkaConfig.STRING_DESERIALIZER)
                .setClient(KafkaClientConfig.createDefault());
    }

    public String getGroupId() {
        return groupId;
    }

    public KafkaConsumerConfig setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getClientId() {
        return clientId;
    }

    public KafkaConsumerConfig setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public List<String> getBootstrapServers() {
        return bootstrapServers;
    }

    public KafkaConsumerConfig setBootstrapServers(List<String> bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
        return this;
    }

    public Integer getAutoCommitInterval() {
        return autoCommitInterval;
    }

    public KafkaConsumerConfig setAutoCommitInterval(Integer autoCommitInterval) {
        this.autoCommitInterval = autoCommitInterval;
        return this;
    }

    public String getAutoOffsetReset() {
        return autoOffsetReset;
    }

    public KafkaConsumerConfig setAutoOffsetReset(String autoOffsetReset) {
        this.autoOffsetReset = autoOffsetReset;
        return this;
    }

    public Boolean getEnableAutoCommit() {
        return enableAutoCommit;
    }

    public KafkaConsumerConfig setEnableAutoCommit(Boolean enableAutoCommit) {
        this.enableAutoCommit = enableAutoCommit;
        return this;
    }

    public Integer getFetchMaxWait() {
        return fetchMaxWait;
    }

    public KafkaConsumerConfig setFetchMaxWait(Integer fetchMaxWait) {
        this.fetchMaxWait = fetchMaxWait;
        return this;
    }

    public Integer getFetchMinSize() {
        return fetchMinSize;
    }

    public KafkaConsumerConfig setFetchMinSize(Integer fetchMinSize) {
        this.fetchMinSize = fetchMinSize;
        return this;
    }

    public Integer getHeartbeatInterval() {
        return heartbeatInterval;
    }

    public KafkaConsumerConfig setHeartbeatInterval(Integer heartbeatInterval) {
        this.heartbeatInterval = heartbeatInterval;
        return this;
    }

    public String getIsolationLevel() {
        return isolationLevel;
    }

    public KafkaConsumerConfig setIsolationLevel(String isolationLevel) {
        this.isolationLevel = isolationLevel;
        return this;
    }

    public String getKeyDeserializer() {
        return keyDeserializer;
    }

    public KafkaConsumerConfig setKeyDeserializer(String keyDeserializer) {
        this.keyDeserializer = keyDeserializer;
        return this;
    }

    public String getValueDeserializer() {
        return valueDeserializer;
    }

    public KafkaConsumerConfig setValueDeserializer(String valueDeserializer) {
        this.valueDeserializer = valueDeserializer;
        return this;
    }

    public Integer getMaxPollRecords() {
        return maxPollRecords;
    }

    public KafkaConsumerConfig setMaxPollRecords(Integer maxPollRecords) {
        this.maxPollRecords = maxPollRecords;
        return this;
    }

    public KafkaClientConfig getClient() {
        return client;
    }

    public KafkaConsumerConfig setClient(KafkaClientConfig client) {
        this.client = client;
        return this;
    }

    public KafkaConsumerConfig setProperties(Map<String, String> properties) {
        this.properties = properties;
        return this;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public Properties buildProperties() {
        return writeInto(new PropertyWriter()).getProperties();
    }

    public PropertyWriter writeInto(PropertyWriter writer) {
        writer.tryWrite(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, getAutoCommitInterval())
                .tryWrite(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, getAutoOffsetReset())
                .tryWrite(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, getBootstrapServers())
                .tryWrite(ConsumerConfig.CLIENT_ID_CONFIG, getClientId())
                .tryWrite(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, getEnableAutoCommit())
                .tryWrite(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, getFetchMaxWait())
                .tryWrite(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, getFetchMinSize())
                .tryWrite(ConsumerConfig.GROUP_ID_CONFIG, getGroupId())
                .tryWrite(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, getHeartbeatInterval())
                .tryWrite(ConsumerConfig.ISOLATION_LEVEL_CONFIG, getIsolationLevel())
                .tryWrite(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, getKeyDeserializer())
                .tryWrite(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, getValueDeserializer())
                .tryWrite(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, getMaxPollRecords())
                .merge(properties);

        return writer;
    }

    public void merge(KafkaConsumerConfig other) {
        if (other == null) {
            return;
        }

        if (other.getGroupId() != null) {
            this.groupId = other.getGroupId();
        }
        if (other.getClientId() != null) {
            this.clientId = other.getClientId();
        }
        if (other.getBootstrapServers() != null) {
            this.bootstrapServers = other.getBootstrapServers();
        }
        if (other.getAutoCommitInterval() != null) {
            this.autoCommitInterval = other.getAutoCommitInterval();
        }

        if (other.getAutoOffsetReset() != null) {
            this.autoOffsetReset = other.getAutoOffsetReset();
        }
        if (other.getEnableAutoCommit() != null) {
            this.enableAutoCommit = other.getEnableAutoCommit();
        }
        if (other.getFetchMaxWait() != null) {
            this.fetchMaxWait = other.getFetchMaxWait();
        }
        if (other.getFetchMinSize() != null) {
            this.fetchMinSize = other.getFetchMinSize();
        }

        if (other.getHeartbeatInterval() != null) {
            this.heartbeatInterval = other.getHeartbeatInterval();
        }
        if (other.getIsolationLevel() != null) {
            this.isolationLevel = other.getIsolationLevel();
        }
        if (other.getKeyDeserializer() != null) {
            this.keyDeserializer = other.getKeyDeserializer();
        }
        if (other.getValueDeserializer() != null) {
            this.valueDeserializer = other.getValueDeserializer();
        }
        if (other.getMaxPollRecords() != null) {
            this.maxPollRecords = other.getMaxPollRecords();
        }

        if (other.getClient() != null) {
            this.client.merge(other.getClient());
        }

        if (other.getProperties() != null) {
            this.properties.putAll(other.getProperties());
        }
    }

    @Override
    public String toString() {
        return "KafkaConsumerConfig{" +
                "groupId='" + groupId + '\'' +
                ", clientId='" + clientId + '\'' +
                ", bootstrapServers=" + bootstrapServers +
                ", autoCommitInterval=" + autoCommitInterval +
                ", autoOffsetReset='" + autoOffsetReset + '\'' +
                ", enableAutoCommit=" + enableAutoCommit +
                ", fetchMaxWait=" + fetchMaxWait +
                ", fetchMinSize=" + fetchMinSize +
                ", heartbeatInterval=" + heartbeatInterval +
                ", isolationLevel='" + isolationLevel + '\'' +
                ", keyDeserializer='" + keyDeserializer + '\'' +
                ", valueDeserializer='" + valueDeserializer + '\'' +
                ", maxPollRecords=" + maxPollRecords +
                ", client=" + client +
                ", properties=" + properties +
                '}';
    }
}
