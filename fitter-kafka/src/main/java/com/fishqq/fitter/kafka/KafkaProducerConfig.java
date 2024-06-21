package com.fishqq.fitter.kafka;

import com.fishqq.fitter.config.PropertyWriter;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class KafkaProducerConfig {
    private String acks;
    private Integer batchSize;
    private List<String> bootstrapServers;
    private Integer bufferMemory;
    private String clientId;
    private String compressionType;
    private String keySerializer;
    private String valueSerializer;
    private Integer retries;
    private String transactionIdPrefix;
    private final Map<String, String> properties = new HashMap<>();

    public static KafkaProducerConfig createDefault() {
        return new KafkaProducerConfig()
                .setBootstrapServers(Collections.singletonList(KafkaConfig.DEFAULT_SERVER))
                .setKeySerializer(KafkaConfig.STRING_DESERIALIZER)
                .setValueSerializer(KafkaConfig.STRING_DESERIALIZER);
    }

    public String getAcks() {
        return acks;
    }

    public KafkaProducerConfig setAcks(String acks) {
        this.acks = acks;
        return this;
    }

    public Integer getBatchSize() {
        return batchSize;
    }

    public KafkaProducerConfig setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
        return this;
    }

    public List<String> getBootstrapServers() {
        return bootstrapServers;
    }

    public KafkaProducerConfig setBootstrapServers(List<String> bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
        return this;
    }

    public Integer getBufferMemory() {
        return bufferMemory;
    }

    public KafkaProducerConfig setBufferMemory(Integer bufferMemory) {
        this.bufferMemory = bufferMemory;
        return this;
    }

    public String getClientId() {
        return clientId;
    }

    public KafkaProducerConfig setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getCompressionType() {
        return compressionType;
    }

    public KafkaProducerConfig setCompressionType(String compressionType) {
        this.compressionType = compressionType;
        return this;
    }

    public String getKeySerializer() {
        return keySerializer;
    }

    public KafkaProducerConfig setKeySerializer(String keySerializer) {
        this.keySerializer = keySerializer;
        return this;
    }

    public String getValueSerializer() {
        return valueSerializer;
    }

    public KafkaProducerConfig setValueSerializer(String valueSerializer) {
        this.valueSerializer = valueSerializer;
        return this;
    }

    public Integer getRetries() {
        return retries;
    }

    public KafkaProducerConfig setRetries(Integer retries) {
        this.retries = retries;
        return this;
    }

    public String getTransactionIdPrefix() {
        return transactionIdPrefix;
    }

    public KafkaProducerConfig setTransactionIdPrefix(String transactionIdPrefix) {
        this.transactionIdPrefix = transactionIdPrefix;
        return this;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public Properties buildProperties() {
        return writeInto(new PropertyWriter()).getProperties();
    }

    public Properties toProperties() {
        return writeInto(new PropertyWriter()).getProperties();
    }

    public PropertyWriter writeInto(PropertyWriter writer) {
        writer
                .tryWrite(ProducerConfig.ACKS_CONFIG, getAcks())
                .tryWrite(ProducerConfig.BATCH_SIZE_CONFIG, getBatchSize())
                .tryWrite(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, getBootstrapServers())
                .tryWrite(ProducerConfig.BUFFER_MEMORY_CONFIG, getBufferMemory())
                .tryWrite(ProducerConfig.CLIENT_ID_CONFIG, getClientId())
                .tryWrite(ProducerConfig.COMPRESSION_TYPE_CONFIG, getCompressionType())
                .tryWrite(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, getKeySerializer())
                .tryWrite(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, getValueSerializer())
                .tryWrite(ProducerConfig.RETRIES_CONFIG, getRetries())
                .merge(properties);

        return writer;
    }

    public void merge(KafkaProducerConfig other) {
        if (other == null) {
            return;
        }

        if (other.getAcks() != null) {
            this.acks = other.getAcks();
        }
        if (other.getBatchSize() != null) {
            this.batchSize = other.getBatchSize();
        }
        if (other.getBootstrapServers() != null) {
            this.bootstrapServers = other.getBootstrapServers();
        }
        if (other.getBufferMemory() != null) {
            this.bufferMemory = other.getBufferMemory();
        }
        if (other.getClientId() != null) {
            this.clientId = other.getClientId();
        }
        if (other.getCompressionType() != null) {
            this.compressionType = other.getCompressionType();
        }
        if (other.getKeySerializer() != null) {
            this.keySerializer = other.getKeySerializer();
        }
        if (other.getValueSerializer() != null) {
            this.valueSerializer = other.getValueSerializer();
        }
        if (other.getRetries() != null) {
            this.retries = other.getRetries();
        }
        if (other.getTransactionIdPrefix() != null) {
            this.transactionIdPrefix = other.getTransactionIdPrefix();
        }

        if (other.getProperties() != null) {
            this.properties.putAll(other.getProperties());
        }
    }

    @Override
    public String toString() {
        return "KafkaProducerConfig{" +
                "acks='" + acks + '\'' +
                ", batchSize=" + batchSize +
                ", bootstrapServers=" + bootstrapServers +
                ", bufferMemory=" + bufferMemory +
                ", clientId='" + clientId + '\'' +
                ", compressionType='" + compressionType + '\'' +
                ", keySerializer='" + keySerializer + '\'' +
                ", valueSerializer='" + valueSerializer + '\'' +
                ", retries=" + retries +
                ", transactionIdPrefix='" + transactionIdPrefix + '\'' +
                ", properties=" + properties +
                '}';
    }
}
