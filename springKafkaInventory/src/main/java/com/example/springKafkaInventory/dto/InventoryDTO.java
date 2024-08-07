package com.example.springKafkaInventory.dto;

import lombok.Data;

@Data
public class InventoryDTO {



    public Long productId;
    public String productName;
    public int quantity;


    public InventoryDTO(){};

    public InventoryDTO(Long productId, String productName,int quantity){
        this.productId=productId;
        this.productName=productName;
        this.quantity=quantity;
    }
}
