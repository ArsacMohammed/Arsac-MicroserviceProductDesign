package com.example.springKafkaInventory.controller;
import com.example.springKafkaInventory.dto.InventoryDTO;
import com.example.springKafkaInventory.entity.Inventory;
import com.example.springKafkaInventory.service.InventoryService;
import org.apache.kafka.clients.admin.FinalizedVersionRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory/")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;



    @Value("${springKafkaInventory.service.url}")
    private String springKafkaInventoryServiceUrl;


    @PostMapping("/addToInventory")
    public Inventory save(@RequestBody Inventory inventory){
        return inventoryService.save(inventory);
    }


    @GetMapping("/getAllInventories")
    public ResponseEntity<List<InventoryDTO>> getAllInventories(){

        try{
            List<InventoryDTO> allInventories = inventoryService.getAllInventories();

            if (allInventories.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                for (InventoryDTO items : allInventories){
                    System.out.println(items.toString());
                }
                return new ResponseEntity<>(allInventories,HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }





    @GetMapping("/config")
    public String getConfig() {
        return "Order Service URL: " + springKafkaInventoryServiceUrl;
    }

}
