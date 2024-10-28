package main.java.kalorienzaehler.backend.service;

import main.java.kalorienzaehler.backend.NutritionalInfo;
import main.java.kalorienzaehler.backend.NutritionalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NutritionalInfoService {
    @Autowired
    private NutritionalInfoRepository nutritionalInfoRepository;

    public NutritionalInfo createNutritionalInfo(NutritionalInfo nutritionalInfo) {
        return nutritionalInfoRepository.save(nutritionalInfo);
    }

    public List<NutritionalInfo> getAllNutritionalInfos() {
        return nutritionalInfoRepository.findAll();
    }

    public NutritionalInfo getNutritionalInfoById(Long id) {
        return nutritionalInfoRepository.findById(id).orElse(null);
    }

    public void deleteNutritionalInfo(Long id) {
        nutritionalInfoRepository.deleteById(id);
    }    
}
