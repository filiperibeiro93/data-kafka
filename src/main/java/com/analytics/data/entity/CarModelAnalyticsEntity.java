package com.analytics.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "car_model_analytics")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CarModelAnalyticsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String brand;

    private Long posts;
}
