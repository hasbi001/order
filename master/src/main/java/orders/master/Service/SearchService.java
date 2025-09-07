package orders.master.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import orders.master.model.Entity.Product;
import orders.master.model.Entity.Customer;

@Service
public class SearchService {
    public SearchService(WebClient.Builder builder) {
      this.client = builder.baseUrl("http://elasticsearch:9200/orders").build();
    }

    public void index(Product product) {
      client.post()
            .uri("/_doc/" + product.getId())
            .bodyValue(product)
            .retrieve()
            .bodyToMono(String.class)
            .onErrorResume(ex -> Mono.empty())
            .block();
    }
}
