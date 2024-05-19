package com.analytics.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "brand_analytics")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BrandAnalyticsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String brand;

    private Long posts;
}
