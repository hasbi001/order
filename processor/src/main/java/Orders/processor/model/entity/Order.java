package Orders.processor.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "order")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String customerId;

  @Column(nullable = false)
  private String productId;

  private int qty;
  private double price;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
