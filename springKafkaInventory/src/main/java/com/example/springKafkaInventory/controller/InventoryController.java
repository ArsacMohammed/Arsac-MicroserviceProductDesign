package com.example.springKafkaInventory.controller;
import com.example.springKafkaInventory.dto.InventoryDTO;
import com.example.springKafkaInventory.entity.Inventory;
import com.example.springKafkaInventory.service.InventoryService;
import org.apache.kafka.clients.admin.FinalizedVersionRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService){
        this.inventoryService= inventoryService;
    }


    @PostMapping("/addToInventory")
    public Inventory save(@RequestBody Inventory inventory){
        return inventoryService.save(inventory);
    }


    @GetMapping("/getAllInventories")
    public String getAllInventories(){

        try{
            List<InventoryDTO> allInventories = inventoryService.getAllInventories();
            if (allInventories.isEmpty()){
                return "Inventory Empty ....";
            }else{
                for (InventoryDTO items : allInventories){
                    System.out.println(items.toString());
                }
            }
        }catch (Exception e){
            return "Something gone wrong while executing getting all the inventories :: ";

        }
        return "Inventory returned successfully";
    }


}
