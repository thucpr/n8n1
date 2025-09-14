package com.techweek.n8n_demo.service;

import com.techweek.n8n_demo.entity.Food;
import com.techweek.n8n_demo.entity.Order;
import com.techweek.n8n_demo.entity.OrderItem;
import com.techweek.n8n_demo.repository.FoodRepository;
import com.techweek.n8n_demo.repository.OrderRepository;
import com.techweek.n8n_demo.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    @Autowired
    private FoodRepository foodRepository;
    
    public Order createOrder(String customerName, String customerPhone, String customerAddress, 
                           Map<Long, Integer> cartItems) {
        Order order = new Order();
        order.setCustomerName(customerName);
        order.setCustomerPhone(customerPhone);
        order.setCustomerAddress(customerAddress);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Order.OrderStatus.PENDING);
        
        double totalAmount = 0.0;
        
        for (Map.Entry<Long, Integer> entry : cartItems.entrySet()) {
            Long foodId = entry.getKey();
            Integer quantity = entry.getValue();
            
            Optional<Food> foodOpt = foodRepository.findById(foodId);
            if (foodOpt.isPresent()) {
                Food food = foodOpt.get();
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setFood(food);
                orderItem.setQuantity(quantity);
                orderItem.setUnitPrice(food.getPrice());
                orderItem.setTotalPrice(food.getPrice() * quantity);
                
                totalAmount += orderItem.getTotalPrice();
                order.getOrderItems().add(orderItem);
            }
        }
        
        order.setTotalAmount(totalAmount);
        return orderRepository.save(order);
    }
    
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
    
    public Order updateOrderStatus(Long orderId, Order.OrderStatus status) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            order.setStatus(status);
            return orderRepository.save(order);
        }
        throw new RuntimeException("Order not found with id: " + orderId);
    }
}
