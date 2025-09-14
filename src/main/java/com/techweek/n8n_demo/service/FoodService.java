package com.techweek.n8n_demo.service;

import com.techweek.n8n_demo.entity.Food;
import com.techweek.n8n_demo.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FoodService {
    
    @Autowired
    private FoodRepository foodRepository;
    
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }
    
    public List<Food> getFoodsByCategory(String category) {
        return foodRepository.findByCategory(category);
    }
    
    public List<Food> searchFoods(String name) {
        return foodRepository.findByNameContainingIgnoreCase(name);
    }
    
    public Optional<Food> getFoodById(Long id) {
        return foodRepository.findById(id);
    }
    
    public List<String> getAllCategories() {
        return foodRepository.findAll().stream()
                .map(Food::getCategory)
                .distinct()
                .sorted()
                .toList();
    }
}
