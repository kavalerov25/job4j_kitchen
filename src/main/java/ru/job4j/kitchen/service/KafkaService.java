package ru.job4j.kitchen.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface KafkaService<K, V, T> {
    public void sendMessage(String topic, K key, T type);

    public T receive(ConsumerRecord<K, V> record);
}
