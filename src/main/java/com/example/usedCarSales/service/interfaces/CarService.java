package com.example.usedCarSales.service.interfaces;
import com.example.usedCarSales.dto.CarDTO;
import java.util.List;

public interface CarService {
     CarDTO createCar(CarDTO carDTO);
     CarDTO updateCar(Long id, CarDTO carDTO);
     Boolean deleteCar(Long id);
     CarDTO getCarById(Long id);
     List<CarDTO> getAllCars();
}
