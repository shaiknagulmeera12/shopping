package com.example.inventory.controller;

import com.example.inventory.model.Order;
import com.example.inventory.model.Stock;
import com.example.inventory.model.Supplier;
import com.example.inventory.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/stocks")
    public List<Stock> getStockReport() {
        return reportService.getStockReport();
    }

    @GetMapping("/orders")
    public List<Order> getOrderReport() {
        return reportService.getOrderReport();
    }

    @GetMapping("/suppliers")
    public List<Supplier> getSupplierReport() {
        return reportService.getSupplierReport();
    }
}
