package kalorienzaehler.backend.entity;


import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String name;

    public Product() {
    }

    public Product(String name, double calories, double quantity) {
        this.name = name;
        this.calories = calories;
        this.quantity = quantity;
    }

    // Getter und Setter
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
