package com.example.usedCarSales.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usedCarSales.dto.CarDTO;
import com.example.usedCarSales.entity.Car;
import com.example.usedCarSales.entity.User;
import com.example.usedCarSales.entity.status.CarStatus;
import com.example.usedCarSales.mapper.CarMapper;
import com.example.usedCarSales.repository.CarRepository;
import com.example.usedCarSales.repository.UserRepository;
import com.example.usedCarSales.service.interfaces.CarService;

import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarMapper carMapper;

    @Override
    @Transactional 
    public CarDTO createCar(CarDTO carDTO) {
        if (carRepository.existsById(carDTO.carId())) {
            log.error("Car with ID {} already exists", carDTO.carId());
            throw new IllegalArgumentException("Car with this ID already exists");
        }
        try {
            // 1. Obtener el usuario desde el repositorio
            User user = userRepository.findById(carDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            // 2. Convertir el DTO a entidad
            Car newCar = carMapper.carDTOToCar(carDTO);
            newCar.setUser(user); // Asignar el usuario manualmente

            // 3. Guardar el carro y retornar el DTO
            Car savedCar = carRepository.save(newCar);
            log.info("Car with ID {} created successfully", savedCar.getIdCar()); //SLF4J:
            return carMapper.carToCarDTO(savedCar);
        } catch (Exception e) {
            log.error("Error creating car: {}", e.getMessage());
            throw e; // or handle it accordingly
        }
    }

    @Override
    @Transactional 
    public CarDTO updateCar(Long id, CarDTO carDTO) {
        try {
            Car existingCar = carRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Car not found with id: " + id));
            // Update fields
            existingCar.setBrand(carDTO.brand());
            existingCar.setModel(carDTO.model());
            existingCar.setYear(carDTO.year());
            existingCar.setPrice(carDTO.price());
            existingCar.setMileage(carDTO.mileage());
            existingCar.setImage(carDTO.image());
            existingCar.setStatus(CarStatus.valueOf(carDTO.status().toUpperCase()));
            if (carDTO.userId() != null) {
                User user = userRepository.findById(carDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found with ID " + carDTO.userId()));
                existingCar.setUser(user);
            }
            // Save updated car
            Car updatedCar = carRepository.save(existingCar);
            log.info("Car with ID {} updated successfully", updatedCar.getIdCar());
            return carMapper.carToCarDTO(updatedCar);
        } catch (Exception e) {
            log.error("Error updating car: {}", e.getMessage());
            throw e; // or handle it accordingly
        }
    }

    @Override
    @Transactional 
    public Boolean deleteCar(Long id) {
        try {
            if (carRepository.findById(id).isPresent()) {
                carRepository.deleteById(id);
                log.info("Car with ID {} deleted successfully", id);
                return true;
            } else {
                log.error("Car with ID {} NOT exists", id);
                throw new IllegalArgumentException("Car with this ID NOT exists");
            }
        } catch (Exception e) {
            log.error("Error deleting car: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public CarDTO getCarById(Long id) {
        try {
            Car car = carRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Car not found with id: " + id));
            log.info("Car with ID {} retrieved successfully", id);
            return carMapper.carToCarDTO(car);
        } catch (Exception e) {
            log.error("Error retrieving car by ID {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public List<CarDTO> getAllCars() {
        try {
            List<Car> cars = carRepository.findAll();
            log.info("Retrieved {} cars from the database", cars.size());
            List<CarDTO> carDTOs = cars.stream()
                    .map(carMapper::carToCarDTO)
                    .toList();
            return carDTOs;
        } catch (Exception e) {
            log.error("Error retrieving all cars: {}", e.getMessage());
            throw e;
        }
    }

}
