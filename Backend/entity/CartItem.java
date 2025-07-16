@Entity
public class CartItem {
    @Id @GeneratedValue private Long id;

    @ManyToOne private User user;
    @ManyToOne private Product product;

    private int quantity;
}
