package orders.master.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import java.util.HashMap;
import java.util.Map;

import orders.master.model.Entity.Product;

@Configuration
public class KafkaConfig {
    @Bean
    public ConsumerFactory<String, Product> consumerFactory() {
      Map<String, Object> props = new HashMap<>();
      props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
      props.put(ConsumerConfig.GROUP_ID_CONFIG, "product_group");
      props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
      JsonDeserializer<Product> jsonDeserializer = new JsonDeserializer<>(Product.class, false);
      jsonDeserializer.addTrustedPackages("*");
      return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Product> kafkaListenerContainerFactory() {
      ConcurrentKafkaListenerContainerFactory<String, Product> factory =
          new ConcurrentKafkaListenerContainerFactory<>();
      factory.setConsumerFactory(consumerFactory());
      return factory;
    }
}
