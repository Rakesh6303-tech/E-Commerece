@Entity
public class Product {
    @Id @GeneratedValue private Long id;
    private String name;
    private String category;
    private double price;
}
