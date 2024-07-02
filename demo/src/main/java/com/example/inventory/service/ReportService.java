package com.example.inventory.service;

import com.example.inventory.model.Order;
import com.example.inventory.model.Stock;
import com.example.inventory.model.Supplier;
import com.example.inventory.repository.OrderRepository;
import com.example.inventory.repository.StockRepository;
import com.example.inventory.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Stock> getStockReport() {
        return stockRepository.findAll();
    }

    public List<Order> getOrderReport() {
        return orderRepository.findAll();
    }

    public List<Supplier> getSupplierReport() {
        return supplierRepository.findAll();
    }
}
