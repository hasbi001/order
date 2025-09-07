package Orders.orderapi.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import Orders.orderapi.models.entity.Order;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderProducer {
    private final KafkaTemplate<String, Order> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(String topic, Order order) {
        log.info("log dari order api");
        kafkaTemplate.send(topic, order);
    }
}
