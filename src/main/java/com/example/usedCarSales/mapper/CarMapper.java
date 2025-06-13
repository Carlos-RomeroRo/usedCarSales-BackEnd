package com.example.usedCarSales.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.usedCarSales.dto.CarDTO;
import com.example.usedCarSales.entity.Car;
import com.example.usedCarSales.repository.UserRepository;
import com.example.usedCarSales.entity.User;
@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mapping(source = "idCar", target = "carId")
    @Mapping(source = "user.idUser", target = "userId")
    CarDTO carToCarDTO(Car car);

    // @Mapping(source = "entity.idReferencer", target = "idDTO") 
    // Convert Car entity to CarDTO (backend -> frontend)

    // Convert CarDTO to Car entity (frontend -> backend)
    default Car carDTOToCar(CarDTO carDTO, UserRepository userRepository) {
        if (carDTO == null) {
            return null;
        }
        Car car = new Car();
        car.setIdCar(carDTO.carId());
        car.setBrand(carDTO.brand());
        car.setModel(carDTO.model());
        car.setYear(carDTO.year());
        car.setPrice(carDTO.price());
        car.setMileage(carDTO.mileage());
        car.setImage(carDTO.image());
        if(carDTO.userId() != null) {
            User user = userRepository.findById(carDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + carDTO.userId()));
            car.setUser(user);
        }
        // User entity should be set separately, as it requires a User object
        return car;
    }

}
