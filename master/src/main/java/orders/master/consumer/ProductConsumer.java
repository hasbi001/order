package orders.master.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import orders.master.model.Entity.Product;
import orders.master.model.Dto.ProductDto;
import orders.master.Respository.ProductRepository;
import orders.master.Service.CacheService;
import orders.master.Service.SearchService;



@Service
public class ProductConsumer {
    private final ProductRepository repository;
    private final CacheService cacheService;
    private final SearchService searchService;

    public ProductConsumer(ProductRepository repository, CacheService cacheService, SearchService searchService) {
        this.repository = repository;
        this.cacheService = cacheService;
        this.searchService = searchService;
    }

    @KafkaListener(topics = "orders", groupId = "order_group", containerFactory = "kafkaListenerContainerFactory")
    public void onMessage(ProductDto dto) {
        Product entity = new Product();
        entity.setId(dto.getId());
        entity.setProductName(dto.getProductName());
        entity.setStock(dto.getStock());
        entity.setPrice(dto.getPrice());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());

        Product saved = repository.save(entity);
        cacheService.saveProduct(saved);
        searchService.index(saved);
    }

}
