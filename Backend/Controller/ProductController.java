@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired private ProductService productService;

    @GetMapping public List<Product> getAll() {
        return productService.getAllProducts();
    }

    @PostMapping @PreAuthorize("hasAuthority('ADMIN')")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/{id}") @PreAuthorize("hasAuthority('ADMIN')")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}") @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
