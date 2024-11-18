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

/**
 * Diese API bietet die Möglichkeit, Mahlzeiten, Produkte und Kalorienaufnahmen zu verwalten.
 * 
 * Hauptfunktionen:
 * - Erstellen, Abrufen und Löschen von Mahlzeiten
 * - Verwalten von Produkten, die in Mahlzeiten enthalten sein können
 * - Nachverfolgen der Kalorienaufnahme basierend auf Mahlzeiten und Produkten
 * 
 * Jede Funktionalität ist über spezifische Endpunkte zugänglich.
 */
@RestController
@RequestMapping("/api")
@Tag(name = "Kalorienzähler", description = "API zur Verwaltung von Mahlzeiten, Produkten und Kalorienaufnahme")
public class ApiController {

    @Autowired
    private MealService mealService; // Service zur Verwaltung von Mahlzeiten.

    @Autowired
    private ProductService productService; // Service zur Verwaltung von Produkten.

    @Autowired
    private CaloricIntakeService caloricIntakeService; // Service zur Nachverfolgung von Kalorienaufnahmen.

    /**
     * Erstellt eine neue Mahlzeit.
     * 
     * Beispiel: Eine Mahlzeit mit Namen "Frühstück", die Produkte wie "Brötchen" und "Butter" enthält.
     * 
     * @param meal Die zu erstellende Mahlzeit (als JSON-Objekt).
     * @return Die erstellte Mahlzeit inklusive ID und weiteren Details.
     */
    @Operation(summary = "Erstellt eine neue Mahlzeit")
    @PostMapping("/meals")
    public ResponseEntity<Meal> createMeal(@RequestBody Meal meal) {
        return ResponseEntity.ok(mealService.createMeal(meal));
    }

    /**
     * Gibt eine Liste aller gespeicherten Mahlzeiten zurück.
     * 
     * Beispiel: Eine Liste, die Frühstück, Mittagessen und Abendessen enthält.
     * 
     * @return Liste aller Mahlzeiten.
     */
    @Operation(summary = "Gibt alle Mahlzeiten zurück")
    @GetMapping("/meals")
    public ResponseEntity<List<Meal>> getAllMeals() {
        return ResponseEntity.ok(mealService.getAllMeals());
    }

    /**
     * Ruft eine spezifische Mahlzeit basierend auf ihrer ID ab.
     * 
     * Beispiel: Abrufen der Mahlzeit mit ID 1.
     * 
     * @param id Die ID der gewünschten Mahlzeit.
     * @return Die angeforderte Mahlzeit, falls vorhanden.
     */
    @Operation(summary = "Gibt eine spezifische Mahlzeit nach ID zurück")
    @GetMapping("/meals/{id}")
    public ResponseEntity<Meal> getMealById(@PathVariable Long id) {
        return ResponseEntity.ok(mealService.getMealById(id));
    }

    /**
     * Löscht eine Mahlzeit basierend auf ihrer ID.
     * 
     * Hinweis: Diese Aktion ist nicht rückgängig zu machen.
     * 
     * @param id Die ID der zu löschenden Mahlzeit.
     */
    @Operation(summary = "Löscht eine Mahlzeit nach ID")
    @DeleteMapping("/meals/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long id) {
        mealService.deleteMeal(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Erstellt ein neues Produkt, das in Mahlzeiten verwendet werden kann.
     * 
     * Beispiel: Ein Produkt "Apfel" mit 52 Kalorien pro 100g.
     * 
     * @param product Das zu erstellende Produkt.
     * @return Das erstellte Produkt inklusive ID.
     */
    @Operation(summary = "Erstellt ein neues Produkt")
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    /**
     * Gibt eine Liste aller gespeicherten Produkte zurück.
     * 
     * Beispiel: Liste mit Produkten wie "Apfel", "Banane" und "Milch".
     * 
     * @return Liste aller Produkte.
     */
    @Operation(summary = "Gibt alle Produkte zurück")
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    /**
     * Ruft ein spezifisches Produkt basierend auf seiner ID ab.
     * 
     * Beispiel: Abrufen des Produkts mit ID 5.
     * 
     * @param id Die ID des gewünschten Produkts.
     * @return Das angeforderte Produkt, falls vorhanden.
     */
    @Operation(summary = "Gibt ein spezifisches Produkt nach ID zurück")
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    /**
     * Löscht ein Produkt basierend auf seiner ID.
     * 
     * Hinweis: Diese Aktion entfernt das Produkt dauerhaft.
     * 
     * @param id Die ID des zu löschenden Produkts.
     */
    @Operation(summary = "Löscht ein Produkt nach ID")
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Erstellt eine neue Kalorienaufnahme.
     * 
     * Beispiel: Eine Aufnahme mit 500 Kalorien, basierend auf der Mahlzeit "Frühstück".
     * 
     * @param caloricIntake Die zu speichernde Kalorienaufnahme.
     * @return Die erstellte Kalorienaufnahme.
     */
    @Operation(summary = "Erstellt eine neue Kalorienaufnahme")
    @PostMapping("/caloric-intake")
    public ResponseEntity<CaloricIntake> createCaloricIntake(@RequestBody CaloricIntake caloricIntake) {
        return ResponseEntity.ok(caloricIntakeService.createCaloricIntake(caloricIntake));
    }

    /**
     * Gibt eine Liste aller gespeicherten Kalorienaufnahmen zurück.
     * 
     * Beispiel: Liste mit täglichen Aufnahmen.
     * 
     * @return Liste aller Kalorienaufnahmen.
     */
    @Operation(summary = "Gibt alle Kalorienaufnahmen zurück")
    @GetMapping("/caloric-intake")
    public ResponseEntity<List<CaloricIntake>> getAllCaloricIntakes() {
        return ResponseEntity.ok(caloricIntakeService.getAllCaloricIntakes());
    }

    /**
     * Löscht alle gespeicherten Mahlzeiten.
     * 
     * Hinweis: Diese Aktion entfernt alle gespeicherten Mahlzeiten.
     */
    @DeleteMapping("/meals")
    public ResponseEntity<Void> deleteAllMeals() {
        mealService.deleteAllMeals();
        return ResponseEntity.noContent().build();
    }
}
