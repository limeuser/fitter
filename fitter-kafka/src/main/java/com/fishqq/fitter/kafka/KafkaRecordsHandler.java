package com.fishqq.fitter.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecords;

public interface KafkaRecordsHandler<K, V> {
    CommitMode handle(ConsumerRecords<K, V> records);
}
