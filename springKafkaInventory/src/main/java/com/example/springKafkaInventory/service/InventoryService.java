package com.example.springKafkaInventory.service;

import com.example.springKafkaInventory.dao.InventoryDao;
import com.example.springKafkaInventory.dto.InventoryDTO;
import com.example.springKafkaInventory.entity.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    private InventoryDao inventoryDao;


    public Inventory save(Inventory inventory){
        return inventoryDao.save(inventory);
    }

    public List<InventoryDTO> getAllInventories() {

        List<InventoryDTO> allInventory = new ArrayList<InventoryDTO>();
        allInventory = inventoryDao.getAllInventories()
                .stream()
                .map(this ::convertToDTO)
                .collect(Collectors.toList());
        return allInventory;
    }



    @KafkaListener(topics = "order_topic", groupId = "inventory-group")
    public void listenToOrderTopic(String message) {
        System.out.println("Received message: " + message);
        // Handle the message and update inventory accordingly
        // For example, parse the message to get order details and update inventory
    }

    public InventoryDTO convertToDTO (Inventory inventory){
        return new InventoryDTO(inventory.getProductId(),inventory.getProductName(),inventory.getQuantity());
    }
}
