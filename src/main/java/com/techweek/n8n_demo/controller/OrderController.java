package com.techweek.n8n_demo.controller;

import com.techweek.n8n_demo.entity.Order;
import com.techweek.n8n_demo.service.FoodService;
import com.techweek.n8n_demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("cartItems", new HashMap<Long, Integer>());
        return "cart";
    }
    
    @PostMapping("/add-to-cart")
    @ResponseBody
    public String addToCart(@RequestParam Long foodId, @RequestParam Integer quantity) {
        // In a real application, you would store cart in session or database
        // For simplicity, we'll just return success
        return "success";
    }
    
    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("order", new Order());
        return "checkout";
    }
    
    @PostMapping("/place-order")
    public String placeOrder(@ModelAttribute Order order, 
                           @RequestParam Map<String, String> cartItems) {
        Map<Long, Integer> cart = new HashMap<>();
        
        for (Map.Entry<String, String> entry : cartItems.entrySet()) {
            if (entry.getKey().startsWith("food_")) {
                Long foodId = Long.parseLong(entry.getKey().substring(5));
                Integer quantity = Integer.parseInt(entry.getValue());
                if (quantity > 0) {
                    cart.put(foodId, quantity);
                }
            }
        }
        
        if (!cart.isEmpty()) {
            Order savedOrder = orderService.createOrder(
                order.getCustomerName(),
                order.getCustomerPhone(),
                order.getCustomerAddress(),
                cart
            );
            return "redirect:/order-confirmation/" + savedOrder.getId();
        }
        
        return "redirect:/checkout?error=empty";
    }
    
    @GetMapping("/order-confirmation/{orderId}")
    public String orderConfirmation(@PathVariable Long orderId, Model model) {
        Optional<Order> order = orderService.getOrderById(orderId);
        if (order.isPresent()) {
            model.addAttribute("order", order.get());
            return "order-confirmation";
        }
        return "redirect:/menu";
    }
    
    @GetMapping("/orders")
    public String orders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "orders";
    }
}
