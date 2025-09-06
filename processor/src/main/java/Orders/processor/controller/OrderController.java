package Orders.processor.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Orders.processor.model.entity.Order;
import Orders.processor.Service.CacheService;
import Orders.processor.Repository.OrderRepository;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final CacheService cacheService;
    private final OrderRepository repository;

    public OrderController(CacheService cacheService, OrderRepository repository) {
        this.cacheService = cacheService;
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public OrderEntity getById(@PathVariable Long id) {
        OrderEntity cached = cacheService.getOrder(id);
        if (cached != null) return cached;
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/report/daily")
    public List<Object[]> dailyReport() {
        return repository.findDailySalesReport();
    }

    @GetMapping("/search")
    public Map<String, Object> searchByCustomer(@RequestParam String customer) {
        // Proxy to Elasticsearch simple query
        Map<String, Object> res = new HashMap<>();
        res.put("hint", "Use Elasticsearch endpoint directly: http://localhost:9200/orders/_search?q=customerName:" + customer);
        return res;
    }
}
