package orders.master.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import orders.master.Service.CacheService;
import orders.master.Service.ProductService;
import orders.master.model.Entity.Product;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final CacheService cacheService;
    private final ProductService productService;

    public ProductController(CacheService cacheService, ProductService productService) {
        this.cacheService = cacheService;
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAll() {
        List<Product> data = productService.getAll();
        if(data != null){
            for (Product product : data) {
                cacheService.saveProduct(product);
            }
            return data;
        }
        return null;
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id){
        Product data = productService.getDataById(id);
        if (data != null) {
            cacheService.getProduct(data);
            return data;
        }
        return null;
    }

    @PostMapping
    public Product create(@RequestBody Product request){
        Product data = productService.create(request);
        if (data != null) {
            cacheService.saveProduct(data);
            return data;
        }
        return null;
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product){
        Product data = productService.update(id, product);
        if (data != null) {
            cacheService.saveProduct(data);
            return data;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    private String delete(@PathVariable Long id){
        productService.delete(id);
        return "Sukses";
    }
}
