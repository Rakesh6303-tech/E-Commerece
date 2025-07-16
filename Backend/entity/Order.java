@Entity
public class Order {
    @Id @GeneratedValue private Long id;

    @ManyToOne private User user;
    private Date orderDate;
    private double totalAmount;
}
