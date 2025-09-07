package Orders.processor.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import Orders.orderapi.models.entity.Order;
import Orders.processor.model.dto.OrderDto;
import Orders.processor.Repository.OrderRepository;
import Orders.processor.Service.CacheService;
import Orders.processor.Service.SearchService;



@Service
public class OrderConsumer {
    private final OrderRepository repository;
    private final CacheService cacheService;
    private final SearchService searchService;

    public OrderConsumer(OrderRepository repository, CacheService cacheService, SearchService searchService) {
        this.repository = repository;
        this.cacheService = cacheService;
        this.searchService = searchService;
    }

    @KafkaListener(topics = "orders", groupId = "order_group", containerFactory = "kafkaListenerContainerFactory")
    public void onMessage(OrderDto dto) {
        Order entity = new Order();
        entity.setCustomerName(dto.getCustomerName());
        entity.setProduct(dto.getProduct());
        entity.setQty(dto.getQty());
        entity.setPrice(dto.getPrice());
        entity.setCreatedAt(dto.getCreatedAt());

        Order saved = repository.save(entity);
        cacheService.saveOrder(saved);
        searchService.index(saved);
    }

}
