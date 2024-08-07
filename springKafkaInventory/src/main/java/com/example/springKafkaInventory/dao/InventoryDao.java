package com.example.springKafkaInventory.dao;

import com.example.springKafkaInventory.dto.InventoryDTO;
import com.example.springKafkaInventory.entity.Inventory;

import java.util.List;

public interface InventoryDao {


    Inventory   save(Inventory inventory);
    List<Inventory> getAllInventories();
}
