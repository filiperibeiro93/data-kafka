package com.analytics.data.service;

import com.analytics.data.dto.CarPostDto;
import com.analytics.data.entity.BrandAnalyticsEntity;
import com.analytics.data.entity.CarModelAnalyticsEntity;
import com.analytics.data.entity.CarModelPriceEntity;
import com.analytics.data.repository.BrandAnalyticsRepository;
import com.analytics.data.repository.CarModelAnalyticsRepository;
import com.analytics.data.repository.CarPriceAnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostAnalyticsServiceImpl implements PostAnalyticsService {

    private final BrandAnalyticsRepository brandAnalyticsRepository;
    private final CarModelAnalyticsRepository carModelAnalyticsRepository;
    private final CarPriceAnalyticsRepository carPriceAnalyticsRepository;

    @Autowired
    public PostAnalyticsServiceImpl(BrandAnalyticsRepository brandAnalyticsRepository,
                                    CarModelAnalyticsRepository carModelAnalyticsRepository,
                                    CarPriceAnalyticsRepository carPriceAnalyticsRepository) {
        this.brandAnalyticsRepository = brandAnalyticsRepository;
        this.carModelAnalyticsRepository = carModelAnalyticsRepository;
        this.carPriceAnalyticsRepository = carPriceAnalyticsRepository;
    }

    @Override
    public void saveDataAnalytics(CarPostDto dto) {
        saveBrandAnalytics(dto.getBrand());
        saveCarModelAnalytics(dto.getModel());
        saveCarModelPriceAnalytics(dto.getModel(), dto.getPrice());
    }

    private void saveBrandAnalytics(String brand) {
        var entity = new BrandAnalyticsEntity();

        brandAnalyticsRepository.findByBrand(brand).ifPresentOrElse(item -> {
            item.setPosts(item.getPosts() + 1);
            brandAnalyticsRepository.save(item);
        }, () -> {
            entity.setBrand(brand);
            entity.setPosts(1L);
            brandAnalyticsRepository.save(entity);
        });
    }

    private void saveCarModelAnalytics(String model) {
        var entity = new CarModelAnalyticsEntity();

        carModelAnalyticsRepository.findByModel(model).ifPresentOrElse(item -> {
            item.setPosts(item.getPosts() + 1);
            carModelAnalyticsRepository.save(item);
        }, () -> {
            entity.setModel(model);
            entity.setPosts(1L);
            carModelAnalyticsRepository.save(entity);
        });
    }

    private void saveCarModelPriceAnalytics(String model, Double price) {
        var entity = new CarModelPriceEntity();

        entity.setModel(model);
        entity.setPrice(price);
        carPriceAnalyticsRepository.save(entity);
    }
}
