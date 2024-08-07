package com.example.springKafkaInventory.impl;

import java.util.List;
import com.example.springKafkaInventory.dao.InventoryDao;
import com.example.springKafkaInventory.entity.Inventory;
import com.example.springKafkaInventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class InventoryDaoImpl implements InventoryDao {


    @Autowired
    private InventoryRepository inventoryRepository;

    public InventoryDaoImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }


    @Override
    public Inventory save(Inventory inventory){
        return inventoryRepository.save(inventory);
    }

    @Override
    public List<Inventory> getAllInventories(){
        return inventoryRepository.findAll();
    }

}
