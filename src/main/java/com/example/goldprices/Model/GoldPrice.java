package com.example.goldprices.Model;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "prices")

public class GoldPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private double price;

    public GoldPrice(){}
    public GoldPrice(Long id, String date, double price) {
        this.id=id;
        this.date = date;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

