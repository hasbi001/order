package Orders.orderapi.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import Orders.orderapi.models.entity.Order;

@Service
public class OrderProducer {
    private final KafkaTemplate<String, Order> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(String topic, Order order) {
        kafkaTemplate.send(topic, order);
    }
}
