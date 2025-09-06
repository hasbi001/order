package Orders.processor.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import Orders.processor.model.entity.Order;

@Service
public class SearchService {
    private final WebClient client;
  public SearchService(WebClient.Builder builder) {
    this.client = builder.baseUrl("http://elasticsearch:9200/orders").build();
  }

  public void index(Order order) {
    client.post()
          .uri("/_doc/" + order.getId())
          .bodyValue(order)
          .retrieve()
          .bodyToMono(String.class)
          .onErrorResume(ex -> Mono.empty())
          .block();
  }
}
