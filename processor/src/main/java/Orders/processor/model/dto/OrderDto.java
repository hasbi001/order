package Orders.processor.model.dto;


import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderDto {
    private Long id;
    private Long customerId;
    private Long productId;
    private int qty;
    private double price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
