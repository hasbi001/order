package orders.master.model.Dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String productName;
    private int stock;
    private double price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
