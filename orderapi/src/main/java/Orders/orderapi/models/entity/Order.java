package Orders.orderapi.models.entity;

import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class Order {
    private Long id;
    private int customerId;
    private int productId;
    private int qty;
    private double price;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
}
