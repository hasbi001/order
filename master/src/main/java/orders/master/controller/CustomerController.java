package orders.master.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import orders.master.Service.CacheService;
import orders.master.Service.CustomerService;
import orders.master.model.Entity.Customer;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CacheService cacheService;
    private final CustomerService customerService;

    public CustomerController(CacheService cacheService, CustomerService customerService) {
        this.cacheService = cacheService;
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAll() {
        List<Customer> data = customerService.getAll();
        if(data != null){
            for (Customer customer : data) {
                cacheService.saveCustomer(customer);
            }
            return data;
        }
        return null;
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable Long id){
        Customer data = customerService.getDataById(id);
        if (data != null) {
            cacheService.getCustomer(data);
            return data;
        }
        return null;
    }

    @PostMapping
    public Customer create(@RequestBody Customer request){
        Customer data = customerService.create(request);
        if (data != null) {
            cacheService.saveCustomer(data);
            return data;
        }
        return null;
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id, @RequestBody Customer customer){
        Customer data = customerService.update(id, customer);
        if (data != null) {
            cacheService.saveCustomer(data);
            return data;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    private String delete(@PathVariable Long id){
        customerService.delete(id);
        return "Sukses";
    }
}