package orders.master.model.Entity;

@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String address;
    
    @Column(nullable = false)
    private int phoneNumber;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
