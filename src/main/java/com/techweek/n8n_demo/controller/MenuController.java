package com.techweek.n8n_demo.controller;

import com.techweek.n8n_demo.entity.Food;
import com.techweek.n8n_demo.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MenuController {
    
    @Autowired
    private FoodService foodService;
    
    @GetMapping("/")
    public String home() {
        return "redirect:/menu";
    }
    
    @GetMapping("/menu")
    public String menu(@RequestParam(required = false) String category, 
                      @RequestParam(required = false) String search, 
                      Model model) {
        List<Food> foods;
        
        if (category != null && !category.isEmpty()) {
            foods = foodService.getFoodsByCategory(category);
        } else if (search != null && !search.isEmpty()) {
            foods = foodService.searchFoods(search);
        } else {
            foods = foodService.getAllFoods();
        }
        
        List<String> categories = foodService.getAllCategories();
        
        model.addAttribute("foods", foods);
        model.addAttribute("categories", categories);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("searchTerm", search);
        System.out.println("hihi");
        System.out.println("haha");
        
        return "menu";
    }
}
