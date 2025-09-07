package orders.master.Service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import orders.master.model.Entity.Product;
import orders.master.model.Entity.Customer;

@Service
public class CacheService {
    private final RedisTemplate<String, Product> redisProduct;  
    private final RedisTemplate<String, Customer> redisCustomer;  

  public CacheService(RedisTemplate<String, Product> redisProduct,RedisTemplate<String, Product> redisCustomer) {
    this.redisProduct = redisProduct;
    this.redisCustomer = redisCustomer;
  }

  public void saveProduct(Product product) {
    redisProduct.opsForValue().set("product:" + product.getId(), product);
  }

  public Product getProduct(Long id) {
    return redisProduct.opsForValue().get("product:" + id);
  }
  
  public void saveCustomer(Customer customer ) {
    redisCustomer.opsForValue().set("customer :" + customer.getId(), customer );
  }

  public Customer getCuss(Long id) {
    return redisCustomer.opsForValue().get("customer :" + id);
  }
}
