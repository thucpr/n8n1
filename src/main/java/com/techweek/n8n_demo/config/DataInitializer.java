package com.techweek.n8n_demo.config;

import com.techweek.n8n_demo.entity.Food;
import com.techweek.n8n_demo.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private FoodRepository foodRepository;
    
    @Override
    public void run(String... args) {
        if (foodRepository.count() == 0) {
            // Appetizers
            foodRepository.save(Food.builder()
                    .name("Caesar Salad")
                    .description("Fresh romaine lettuce with caesar dressing, croutons, and parmesan cheese")
                    .price(8.99)
                    .category("Appetizers")
                    .imageUrl("https://images.unsplash.com/photo-1546793665-c74683f339c1?w=300")
                    .build());

            foodRepository.save(Food.builder()
                    .name("Buffalo Wings")
                    .description("Spicy chicken wings served with ranch dressing")
                    .price(12.99)
                    .category("Appetizers")
                    .imageUrl("https://images.unsplash.com/photo-1608039755401-742074f0548d?w=300")
                    .build());

            foodRepository.save(Food.builder()
                    .name("Mozzarella Sticks")
                    .description("Crispy breaded mozzarella sticks with marinara sauce")
                    .price(7.99)
                    .category("Appetizers")
                    .imageUrl("https://images.unsplash.com/photo-1571091718767-18b5b1457add?w=300")
                    .build());

            // Main Courses
            foodRepository.save(Food.builder()
                    .name("Grilled Salmon")
                    .description("Fresh Atlantic salmon grilled to perfection with lemon herb butter")
                    .price(18.99)
                    .category("Main Courses")
                    .imageUrl("https://images.unsplash.com/photo-1467003909585-2f8a72700288?w=300")
                    .build());

            foodRepository.save(Food.builder()
                    .name("Beef Burger")
                    .description("Juicy beef patty with lettuce, tomato, onion, and special sauce")
                    .price(14.99)
                    .category("Main Courses")
                    .imageUrl("https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=300")
                    .build());

            foodRepository.save(Food.builder()
                    .name("Chicken Parmesan")
                    .description("Breaded chicken breast with marinara sauce and melted mozzarella")
                    .price(16.99)
                    .category("Main Courses")
                    .imageUrl("https://images.unsplash.com/photo-1562967914-608f82629710?w=300")
                    .build());

            foodRepository.save(Food.builder()
                    .name("Pasta Carbonara")
                    .description("Creamy pasta with bacon, eggs, and parmesan cheese")
                    .price(15.99)
                    .category("Main Courses")
                    .imageUrl("https://images.unsplash.com/photo-1546793665-c74683f339c1?w=300")
                    .build());

            foodRepository.save(Food.builder()
                    .name("BBQ Ribs")
                    .description("Slow-cooked pork ribs with our signature BBQ sauce")
                    .price(19.99)
                    .category("Main Courses")
                    .imageUrl("https://images.unsplash.com/photo-1544025162-d76694265947?w=300")
                    .build());

            // Desserts
            foodRepository.save(Food.builder()
                    .name("Chocolate Cake")
                    .description("Rich chocolate cake with chocolate ganache")
                    .price(6.99)
                    .category("Desserts")
                    .imageUrl("https://images.unsplash.com/photo-1578985545062-69928b1d9587?w=300")
                    .build());

            foodRepository.save(Food.builder()
                    .name("Tiramisu")
                    .description("Classic Italian dessert with coffee and mascarpone")
                    .price(7.99)
                    .category("Desserts")
                    .imageUrl("https://images.unsplash.com/photo-1571877227200-a0d98ea607e9?w=300")
                    .build());

            foodRepository.save(Food.builder()
                    .name("Ice Cream Sundae")
                    .description("Vanilla ice cream with chocolate sauce, nuts, and cherry")
                    .price(5.99)
                    .category("Desserts")
                    .imageUrl("https://images.unsplash.com/photo-1563805042-7684c019e1cb?w=300")
                    .build());

            // Beverages
            foodRepository.save(Food.builder()
                    .name("Fresh Orange Juice")
                    .description("Freshly squeezed orange juice")
                    .price(3.99)
                    .category("Beverages")
                    .imageUrl("https://images.unsplash.com/photo-1621506289937-a8e4df240d0b?w=300")
                    .build());

            foodRepository.save(Food.builder()
                    .name("Iced Coffee")
                    .description("Cold brew coffee served over ice")
                    .price(4.99)
                    .category("Beverages")
                    .imageUrl("https://images.unsplash.com/photo-1461023058943-07fcbe16d735?w=300")
                    .build());

            foodRepository.save(Food.builder()
                    .name("Coca Cola")
                    .description("Classic Coca Cola")
                    .price(2.99)
                    .category("Beverages")
                    .imageUrl("https://images.unsplash.com/photo-1581636625402-29b2a704ef13?w=300")
                    .build());
        }
    }
}
