package com.example.goldprices.Model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "prices")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GoldPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String data;
    private double cena;

}

