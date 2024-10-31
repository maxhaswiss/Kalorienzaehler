package kalorienzaehler.backend.controller;


import kalorienzaehler.backend.entity.Product;
import kalorienzaehler.backend.entity.CaloricIntake;
import kalorienzaehler.backend.entity.Meal;

import kalorienzaehler.backend.service.MealService;
import kalorienzaehler.backend.service.CaloricIntakeService;


import kalorienzaehler.backend.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Kalorienzähler", description = "API zur Verwaltung von Mahlzeiten, Produkten und Kalorienaufnahme")
public class ApiController {

    @Autowired
    private MealService mealService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CaloricIntakeService caloricIntakeService;

    // CRUD-Methoden für Meal
    @Operation(summary = "Erstellt eine neue Mahlzeit")
    @PostMapping("/meals")
    public ResponseEntity<Meal> createMeal(@RequestBody Meal meal) {
        return ResponseEntity.ok(mealService.createMeal(meal));
    }

    @Operation(summary = "Gibt alle Mahlzeiten zurück")
    @GetMapping("/meals")
    public ResponseEntity<List<Meal>> getAllMeals() {
        return ResponseEntity.ok(mealService.getAllMeals());
    }

    @Operation(summary = "Gibt eine spezifische Mahlzeit nach ID zurück")
    @GetMapping("/meals/{id}")
    public ResponseEntity<Meal> getMealById(@PathVariable Long id) {
        return ResponseEntity.ok(mealService.getMealById(id));
    }


    @Operation(summary = "Löscht eine Mahlzeit nach ID")
    @DeleteMapping("/meals/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long id) {
        mealService.deleteMeal(id);
        return ResponseEntity.noContent().build();
    }

    // CRUD-Methoden für Product
    @Operation(summary = "Erstellt ein neues Produkt")
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @Operation(summary = "Gibt alle Produkte zurück")
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @Operation(summary = "Gibt ein spezifisches Produkt nach ID zurück")
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }



    @Operation(summary = "Löscht ein Produkt nach ID")
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // CRUD-Methoden für CaloricIntake
    @Operation(summary = "Erstellt eine neue Kalorienaufnahme")
    @PostMapping("/caloric-intake")
    public ResponseEntity<CaloricIntake> createCaloricIntake(@RequestBody CaloricIntake caloricIntake) {
        return ResponseEntity.ok(caloricIntakeService.createCaloricIntake(caloricIntake));
    }

    @Operation(summary = "Gibt alle Kalorienaufnahmen zurück")
    @GetMapping("/caloric-intake")
    public ResponseEntity<List<CaloricIntake>> getAllCaloricIntakes() {
        return ResponseEntity.ok(caloricIntakeService.getAllCaloricIntakes());
    }

    @Operation(summary = "Gibt eine spezifische Kalorienaufnahme nach ID zurück")
    @GetMapping("/caloric-intake/{id}")
    public ResponseEntity<CaloricIntake> getCaloricIntakeById(@PathVariable Long id) {
        return ResponseEntity.ok(caloricIntakeService.getCaloricIntakeById(id));
    }

 

    @Operation(summary = "Löscht eine Kalorienaufnahme nach ID")
    @DeleteMapping("/caloric-intake/{id}")
    public ResponseEntity<Void> deleteCaloricIntake(@PathVariable Long id) {
        caloricIntakeService.deleteCaloricIntake(id);
        return ResponseEntity.noContent().build();
    }
}
