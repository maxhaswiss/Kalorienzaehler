package main.java.kalorienzaehler.backend.service;

import main.java.kalorienzaehler.backend.entity.CaloricIntake;
import main.java.kalorienzaehler.backend.repository.CaloricIntakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaloricIntakeService {
    @Autowired
    private CaloricIntakeRepository caloricIntakeRepository;

    public CaloricIntake createCaloricIntake(CaloricIntake caloricIntake) {
        return caloricIntakeRepository.save(caloricIntake);
    }

    public List<CaloricIntake> getAllCaloricIntakes() {
        return caloricIntakeRepository.findAll();
    }

    public CaloricIntake getCaloricIntakeById(Long id) {
        return caloricIntakeRepository.findById(id).orElse(null);
    }

    public void deleteCaloricIntake(Long id) {
        caloricIntakeRepository.deleteById(id);
    }
}