package Orders.processor.Service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import Orders.processor.model.entity.Order;

@Service
public class CacheService {
  private final RedisTemplate<String, Order> redisTemplate;  

  public CacheService(RedisTemplate<String, Order> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public void saveOrder(Order order) {
    redisTemplate.opsForValue().set("order:" + order.getId(), order);
  }

  public Order getOrder(Long id) {
    return redisTemplate.opsForValue().get("order:" + id);
  }
}
