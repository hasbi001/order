package orders.master.Respository;

import orders.master.model.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>{

    
}