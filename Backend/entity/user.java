@Entity
public class User {
    @Id @GeneratedValue private Long id;
    private String name;
    private String email;
    private String password;
    private String role; // "ADMIN" or "CUSTOMER"
}
