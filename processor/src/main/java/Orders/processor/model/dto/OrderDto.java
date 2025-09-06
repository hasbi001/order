package Orders.processor.model.dto;


import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderDto {
    private Long id;
    private String customerName;
    private String product;
    private int qty;
    private double price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
