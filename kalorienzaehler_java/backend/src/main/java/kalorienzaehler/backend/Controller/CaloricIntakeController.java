package kalorienzaehler.backend.controller;

import kalorienzaehler.backend.entity.CaloricIntake;
import kalorienzaehler.backend.service.CaloricIntakeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/caloric-intake")
public class CaloricIntakeController {
    @Autowired
    private CaloricIntakeService caloricIntakeService;

    @PostMapping
    public ResponseEntity<CaloricIntake> createCaloricIntake(@RequestBody CaloricIntake caloricIntake) {
        return ResponseEntity.ok(caloricIntakeService.createCaloricIntake(caloricIntake));
    }

    @GetMapping
    public ResponseEntity<List<CaloricIntake>> getAllCaloricIntakes() {
        return ResponseEntity.ok(caloricIntakeService.getAllCaloricIntakes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaloricIntake> getCaloricIntakeById(@PathVariable Long id) {
        return ResponseEntity.ok(caloricIntakeService.getCaloricIntakeById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCaloricIntake(@PathVariable Long id) {
        caloricIntakeService.deleteCaloricIntake(id);
        return ResponseEntity.noContent().build();
    }
}