package Orders.processor.Repository;

import Orders.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    
    @Query(value = "SELECT customer_name, DATE(created_at) AS order_date, SUM(price * qty) AS total_sales " +
          "FROM orders GROUP BY customer_name, DATE(created_at) ORDER BY total_sales DESC", nativeQuery = true)
    List<Object[]> findDailySalesReport();
}
