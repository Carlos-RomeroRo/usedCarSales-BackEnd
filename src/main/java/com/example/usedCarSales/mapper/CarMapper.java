package com.example.usedCarSales.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import com.example.usedCarSales.dto.CarDTO;
import com.example.usedCarSales.entity.Car;


@Mapper(componentModel = "spring")
public interface CarMapper {


    @Mapping(source = "idCar", target = "carId")
    @Mapping(source = "user.idUser", target = "userId")
    @Mapping(source = "status", target = "status")
    CarDTO carToCarDTO(Car car);

    // @Mapping(source = "entity.idReferencer", target = "idDTO") 
    // Convert Car entity to CarDTO (backend -> frontend)

    // Convert CarDTO to Car entity (frontend -> backend)
    
    
    @Mapping(source = "carId", target = "idCar")
    @Mapping(target = "user", ignore = true)
    @Mapping(source = "status", target = "status")
    Car carDTOToCar(CarDTO carDTO);
}
