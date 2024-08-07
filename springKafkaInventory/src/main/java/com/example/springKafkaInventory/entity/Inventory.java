package com.example.springKafkaInventory.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name="inventory")
public class Inventory {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long productId;

    public String productName;
    public int quantity;
}
