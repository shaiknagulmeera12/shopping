package com.example.inventory.service;

import com.example.inventory.model.Order;
import com.example.inventory.model.Stock;
import com.example.inventory.repository.OrderRepository;
import com.example.inventory.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockRepository stockRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order addOrder(Order order) {
        Optional<Stock> stockOptional = stockRepository.findByItemName(order.getItemName());
        if (stockOptional.isPresent()) {
            Stock stock = stockOptional.get();
            if (stock.getQuantity() >= order.getQuantity()) {
                stock.setQuantity(stock.getQuantity() - order.getQuantity());
                stockRepository.save(stock);
                order.setStatus("Confirmed");
                order.setOrderDate(new java.util.Date());
                return orderRepository.save(order);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient stock");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        }
    }

    public Order updateOrder(Long id, Order order) {
        return orderRepository.findById(id).map(existingOrder -> {
            if (order.getCustomerName() != null) {
                existingOrder.setCustomerName(order.getCustomerName());
            }
            if (order.getItemName() != null) {
                existingOrder.setItemName(order.getItemName());
            }
            if (order.getQuantity() != 0) {
                existingOrder.setQuantity(order.getQuantity());
            }
            if (order.getStatus() != null) {
                existingOrder.setStatus(order.getStatus());
            }
            if (order.getOrderDate() != null) {
                existingOrder.setOrderDate(order.getOrderDate());
            }
            return orderRepository.save(existingOrder);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }

    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
    }
}
