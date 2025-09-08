package orders.master.Service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDateTime;

import orders.master.Respository.ProductRepository;
import orders.master.model.Entity.Product;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getDataById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product create(Product data) {
        return productRepository.save(data);
    }

    public Product update(Long id, Product detail) {
        LocalDateTime curDate = LocalDateTime.now();
        return productRepository.findById(id)
                .map(product -> {
                    product.setProductName(detail.getProductName());
                    product.setPrice(detail.getPrice());
                    product.setStock(detail.getStock());
                    product.setUpdatedAt(curDate);
                    return productRepository.save(product);
                })
                .orElse(null);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
