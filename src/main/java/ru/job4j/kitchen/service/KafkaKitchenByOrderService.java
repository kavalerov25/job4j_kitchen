package ru.job4j.kitchen.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.job4j.kitchen.model.OrderDTO;

@Service
@AllArgsConstructor
@Slf4j
public class KafkaKitchenByOrderService implements KafkaService<Integer, String, OrderDTO> {
    private static final String TOPIC_ORDERS = "job4j_orders";
    private final ObjectMapper objectMapper;

    @Override
    public void sendMessage(String topic, Integer key, OrderDTO type) {
        log.error("The method 'sendMessage', is not overridden");
        throw new RuntimeException("The method 'sendMessage', is not overridden");
    }

    @KafkaListener(topics = TOPIC_ORDERS)
    @Override
    public OrderDTO receive(ConsumerRecord<Integer, String> record) {
        log.debug("Partition: {}", record.partition());
        log.debug("Key: {}", record.key());
        log.debug("Value: {}", record.value());
        OrderDTO orderDTO;
        try {
            orderDTO = objectMapper.readValue(record.value(), OrderDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return orderDTO;
    }
}
