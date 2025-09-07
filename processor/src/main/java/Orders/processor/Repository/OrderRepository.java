package Orders.processor.Repository;

// Update the import below to match the actual package of your Order entity
import Orders.processor.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {
    
    @Query(value = "SELECT c.customer_name, DATE(o.created_at) AS order_date, SUM(o.price * qty) AS total_sales " +
          "FROM orders o INNER JOIN customer c ON o.customer_id=c.id GROUP BY customer_name, DATE(created_at) ORDER BY total_sales DESC", nativeQuery = true)
    List<Object[]> findDailySalesReport();
}
