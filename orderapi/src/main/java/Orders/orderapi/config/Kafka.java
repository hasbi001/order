package Orders.orderapi.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import Orders.orderapi.models.entity.Order;

@Configuration
public class Kafka {
  @Bean
  public NewTopic ordersTopic() {
    return TopicBuilder.name("orders").partitions(1).replicas(1).build();
  }
}
