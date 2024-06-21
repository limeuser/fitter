package com.fishqq.fitter.kafka;

import com.fishqq.fitter.limiter.Limiter;
import com.fishqq.fitter.limiter.TimeBasedRateLimiter;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.internals.NoOpConsumerRebalanceListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class KafkaConsumerClient<K, V> {
    private final List<String> topics;
    private final KafkaConsumerConfig config;
    private final Consumer<K, V> consumer;
    private final KafkaRecordsHandler<K, V> handler;
    private final ConsumerRebalanceListener rebalanceListener;

    private final Thread thread;

    public KafkaConsumerClient(List<String> topics,
                               KafkaConsumerConfig config,
                               KafkaRecordsHandler<K, V> handler,
                               ConsumerRebalanceListener listener) {
        this.topics = topics;
        this.config = config;
        this.consumer = new KafkaConsumer<>(config.buildProperties());
        this.handler = handler;
        this.rebalanceListener = listener;
        this.thread = new Thread(this::run, "kafka-consumer-" + this.topics);
    }

    public KafkaConsumerClient(List<String> topics,
                               KafkaConsumerConfig config,
                               KafkaRecordsHandler<K, V> handler) {
        this(topics, config, handler, new NoOpConsumerRebalanceListener());
    }

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerClient.class);

    public void start() {
        consumer.subscribe(topics, this.rebalanceListener);
        this.thread.start();
    }

    private void run() {
        logger.info("start consumer topic: {}", topics);

        Limiter errorLimiter = new TimeBasedRateLimiter(3, TimeUnit.MINUTES.toMillis(1));

        while (true) {
            try {
                ConsumerRecords<K, V> records = consumer.poll(Duration.ofMillis(config.getClient().getPoolTimeoutMs()));
                if (records.isEmpty()) {
                    continue;
                }

                switch (handler.handle(records)) {
                    case ASYNC:
                        this.consumer.commitAsync();
                        break;
                    case SYNC:
                        this.consumer.commitSync();
                        break;
                    case NO:
                    default:
                        break;
                }
            } catch (Throwable e) {
                errorLimiter.limit(() -> logger.error("poll kafka message error: " + this.topics, e));
            }
        }
    }
}
