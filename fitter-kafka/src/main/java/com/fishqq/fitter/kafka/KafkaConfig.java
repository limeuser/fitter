package com.fishqq.fitter.kafka;

import com.fishqq.fitter.config.PropertyWriter;
import org.apache.kafka.clients.CommonClientConfigs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class KafkaConfig {
    public static final String DEFAULT_SERVER = "localhost:9092";
    public static final String STRING_DESERIALIZER = "org.apache.kafka.common.serialization.StringDeserializer";

    private String clientId;
    private List<String> bootstrapServers;

    private KafkaConsumerConfig consumer = new KafkaConsumerConfig();
    private KafkaProducerConfig producer = new KafkaProducerConfig();
    private KafkaAdminConfig admin = new KafkaAdminConfig();
    private final Map<String, String> properties = new HashMap<>();

    public static KafkaConfig createDefault() {
        return new KafkaConfig()
                .setBootstrapServers(Collections.singletonList(DEFAULT_SERVER))
                .setConsumer(KafkaConsumerConfig.createDefault())
                .setProducer(KafkaProducerConfig.createDefault())
                .setAdmin(KafkaAdminConfig.createDefault());
    }

    public List<String> getBootstrapServers() {
        return bootstrapServers;
    }

    public KafkaConfig setBootstrapServers(List<String> bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
        return this;
    }

    public String getClientId() {
        return clientId;
    }

    public KafkaConfig setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public KafkaConfig setConsumer(KafkaConsumerConfig consumer) {
        this.consumer = consumer;
        return this;
    }

    public KafkaConfig setProducer(KafkaProducerConfig producer) {
        this.producer = producer;
        return this;
    }

    public KafkaConfig setAdmin(KafkaAdminConfig admin) {
        this.admin = admin;
        return this;
    }

    public Map<String, String> getProperties() {
        return this.properties;
    }

    public KafkaConsumerConfig getConsumer() {
        if (consumer.getBootstrapServers() == null) {
            consumer.setBootstrapServers(new ArrayList<>(Collections.singletonList(DEFAULT_SERVER)));
        } else if (consumer.getBootstrapServers().isEmpty()) {
            consumer.getBootstrapServers().add(DEFAULT_SERVER);
        }

        return this.consumer;
    }

    public KafkaProducerConfig getProducer() {
        if (producer.getBootstrapServers() == null) {
            producer.setBootstrapServers(new ArrayList<>(Collections.singletonList(DEFAULT_SERVER)));
        } else if (producer.getBootstrapServers().isEmpty()) {
            producer.getBootstrapServers().add(DEFAULT_SERVER);
        }
        return this.producer;
    }

    public KafkaAdminConfig getAdmin() {
        return this.admin;
    }

    private PropertyWriter writeInto(PropertyWriter writer) {
        writer.tryWrite(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers)
                .tryWrite(CommonClientConfigs.CLIENT_ID_CONFIG, this.clientId)
                .merge(properties);

        return writer;
    }

    public Properties buildConsumerProperties() {
        return consumer.writeInto(this.writeInto(new PropertyWriter())).getProperties();
    }

    public Properties buildProducerProperties() {
        return producer.writeInto(this.writeInto(new PropertyWriter())).getProperties();
    }

    public Properties buildAdminProperties() {
        return admin.writeInto(this.writeInto(new PropertyWriter())).getProperties();
    }

    public void merge(KafkaConfig other) {
        if (other == null) {
            return;
        }

        if (other.getBootstrapServers() != null) {
            this.bootstrapServers = other.getBootstrapServers();
        }
        if (other.getClientId() != null) {
            this.clientId = other.getClientId();
        }
        if (other.getConsumer() != null) {
            this.consumer.merge(other.getConsumer());
        }
        if (other.getProducer() != null) {
            this.producer.merge(other.getProducer());
        }
        if (other.getAdmin() != null) {
            this.admin.merge(other.getAdmin());
        }
    }

    @Override
    public String toString() {
        return "KafkaConfig{" +
                "bootstrapServers=" + bootstrapServers +
                ", clientId='" + clientId + '\'' +
                ", consumer=" + consumer +
                ", producer=" + producer +
                ", admin=" + admin +
                ", properties=" + properties +
                '}';
    }
}