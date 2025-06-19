package com.example.usedCarSales.dto;

import java.math.BigDecimal;

public record CarDTO(
     Long carId,
     String brand,
     String model,
     int year,
     BigDecimal price,
     int mileage,
     String image,
     Long userId,
     String status
) {   
}
