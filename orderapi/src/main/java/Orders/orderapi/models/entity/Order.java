package Orders.orderapi.models.entity;

import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class Order {
    private Long id;
    @NotBlank private String customerId;
    @NotBlank private String productId;
    @Min(1) private int qty;
    @Min(0) private double price;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
}
