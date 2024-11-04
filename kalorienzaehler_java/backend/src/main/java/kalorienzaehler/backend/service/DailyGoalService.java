package main.java.kalorienzaehler.backend.service;

import main.java.kalorienzaehler.backend.entity.DailyGoal;
import main.java.kalorienzaehler.backend.repository.DailyGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyGoalService {
    @Autowired
    private DailyGoalRepository dailyGoalRepository;

    public DailyGoal createDailyGoal(DailyGoal dailyGoal) {
        return dailyGoalRepository.save(dailyGoal);
    }

    public List<DailyGoal> getAllDailyGoals() {
        return dailyGoalRepository.findAll();
    }

    public DailyGoal getDailyGoalById(Long id) {
        return dailyGoalRepository.findById(id).orElse(null);
    }

    public void deleteDailyGoal(Long id) {
        dailyGoalRepository.deleteById(id);
    }
}