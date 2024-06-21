package com.fishqq.fitter.kafka;

import com.fishqq.fitter.config.PropertyWriter;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class KafkaAdminConfig {
    private String clientId;
    private Duration closeTimeout;
    private Duration operationTimeout;
    private Boolean failFast;
    private Boolean modifyTopicConfigs;
    private Boolean autoCreate;
    private Map<String, String> properties = new HashMap<>();

    public static KafkaAdminConfig createDefault() {
        return new KafkaAdminConfig()
                .setCloseTimeout(Duration.ofSeconds(10))
                .setOperationTimeout(Duration.ofSeconds(30))
                .setFailFast(true)
                .setModifyTopicConfigs(false)
                .setAutoCreate(false);
    }

    public String getClientId() {
        return clientId;
    }

    public KafkaAdminConfig setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public Duration getCloseTimeout() {
        return closeTimeout;
    }

    public KafkaAdminConfig setCloseTimeout(Duration closeTimeout) {
        this.closeTimeout = closeTimeout;
        return this;
    }

    public Duration getOperationTimeout() {
        return operationTimeout;
    }

    public KafkaAdminConfig setOperationTimeout(Duration operationTimeout) {
        this.operationTimeout = operationTimeout;
        return this;
    }

    public Boolean getFailFast() {
        return failFast;
    }

    public KafkaAdminConfig setFailFast(Boolean failFast) {
        this.failFast = failFast;
        return this;
    }

    public Boolean getModifyTopicConfigs() {
        return modifyTopicConfigs;
    }

    public KafkaAdminConfig setModifyTopicConfigs(Boolean modifyTopicConfigs) {
        this.modifyTopicConfigs = modifyTopicConfigs;
        return this;
    }

    public Boolean getAutoCreate() {
        return autoCreate;
    }

    public KafkaAdminConfig setAutoCreate(Boolean autoCreate) {
        this.autoCreate = autoCreate;
        return this;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public KafkaAdminConfig setProperties(Map<String, String> properties) {
        this.properties = properties;
        return this;
    }

    public Properties buildProperties() {
        return writeInto(new PropertyWriter()).getProperties();
    }

    public PropertyWriter writeInto(PropertyWriter writer) {
        writer.tryWrite(ProducerConfig.CLIENT_ID_CONFIG, getClientId()).merge(properties);

        return writer;
    }

    public void merge(KafkaAdminConfig other) {
        if (other == null) {
            return;
        }

        if (other.getClientId() != null) {
            this.clientId = other.getClientId();
        }
        if (other.closeTimeout != null) {
            this.closeTimeout = other.getCloseTimeout();
        }
        if (other.getOperationTimeout() != null) {
            this.operationTimeout = other.getOperationTimeout();
        }
        if (other.getFailFast() != null) {
            this.failFast = other.getFailFast();
        }
        if (other.getModifyTopicConfigs() != null) {
            this.modifyTopicConfigs = other.getModifyTopicConfigs();
        }
        if (other.getAutoCreate() != null) {
            this.autoCreate = other.getAutoCreate();
        }

        if (other.getProperties() != null) {
            this.properties.putAll(other.getProperties());
        }
    }

    @Override
    public String toString() {
        return "KafkaAdminConfig{" +
                "clientId='" + clientId + '\'' +
                ", closeTimeout=" + closeTimeout +
                ", operationTimeout=" + operationTimeout +
                ", failFast=" + failFast +
                ", modifyTopicConfigs=" + modifyTopicConfigs +
                ", autoCreate=" + autoCreate +
                ", properties=" + properties +
                '}';
    }
}
