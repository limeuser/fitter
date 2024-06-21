package com.fishqq.fitter.kafka;

public class KafkaClientConfig {
    private Long poolTimeoutMs;

    public static KafkaClientConfig createDefault() {
        return new KafkaClientConfig()
                .setPoolTimeoutMs(30 * 1000L);
    }

    public Long getPoolTimeoutMs() {
        return poolTimeoutMs;
    }

    public KafkaClientConfig setPoolTimeoutMs(Long poolTimeoutMs) {
        this.poolTimeoutMs = poolTimeoutMs;
        return this;
    }

    public void merge(KafkaClientConfig other) {
        if (other == null) {
            return;
        }

        if (other.getPoolTimeoutMs() != null) {
            this.poolTimeoutMs = other.getPoolTimeoutMs();
        }
    }

    @Override
    public String toString() {
        return "KafkaClientConfig{" +
                "poolTimeoutMs=" + poolTimeoutMs +
                '}';
    }
}
