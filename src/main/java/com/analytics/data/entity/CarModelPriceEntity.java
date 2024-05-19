package com.analytics.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "car_model_price")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CarModelPriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String model;

    private Double price;
}
