package orders.master.Service;

import org.springframework.stereotype.Service;

import orders.master.Respository.CustomerRepository;
import orders.master.model.Entity.Customer;

import java.util.List;
import java.time.LocalDateTime;

@Service
public class CustomerService {
    private final CustomerRepository customRepository;

    public CustomerService(CustomerRepository customRepository) {
        this.customRepository = customRepository;
    }

    public List<Customer> getAll() {
        return customRepository.findAll();
    }

    public Customer getDataById(Long id) {
        return customRepository.findById(id).orElse(null);
    }

    public Customer create(Customer data) {
        LocalDateTime curDate = LocalDateTime.now();
        data.setCreatedAt(curDate);
        return customRepository.save(data);
    }

    public Customer update(Long id, Customer detail) {
        LocalDateTime curDate = LocalDateTime.now();
        return customRepository.findById(id)
                .map(customer -> {
                    customer.setCustomerName(detail.getCustomerName());
                    customer.setEmail(detail.getEmail());
                    customer.setAddress(detail.getAddress());
                    customer.setPhoneNumber(detail.getPhoneNumber());
                    customer.setUpdatedAt(curDate);
                    return customRepository.save(customer);
                })
                .orElse(null);
    }

    public void delete(Long id) {
        customRepository.deleteById(id);
    }
}
