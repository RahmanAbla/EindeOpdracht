package com.example.eindeopdrachtvanrahman.unittests;

import com.example.eindeopdrachtvanrahman.Services.CarService;
import com.example.eindeopdrachtvanrahman.dto.CarDTO;
import com.example.eindeopdrachtvanrahman.dto.CarInputDto;
import com.example.eindeopdrachtvanrahman.models.Car;
import com.example.eindeopdrachtvanrahman.repository.CarRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.event.CaretListener;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CarControllerTest {

    @Mock
    CarRepository carRepository;

    @InjectMocks
    CarService service;

    @Test
    void shouldRetrieveCorrectCarById() throws Exception {
        Car car = new Car("TOYOTA", "Yaris", 2018, 20L);
        Mockito.when(carRepository.findById(20L)).thenReturn(Optional.of(car));

        CarDTO carDTO = service.getCarsById(20L);

        assertEquals(20L, carDTO.getId());
    }

    @Test
    void shouldRetrieveCorrectCars() throws Exception {
        Car car = new Car("TOYOTA", "Yaris", 2018, 20L);
        Car car1 = new Car("Audi", "A3", 2015, 15L);
        Car car2 = new Car("Fiat", "Panda", 2016, 14L);
        Car car3 = new Car("Volvo", "V40", 2017, 17L);

        List<Car> carList = new ArrayList<>();
        carList.add(car);
        carList.add(car1);
        carList.add(car2);
        carList.add(car3);

        Mockito.when(carRepository.findAll()).thenReturn(carList);
        List<CarDTO> carDTOList = service.getAllCars();
        assertEquals(20L, carDTOList.get(0).getId());
        assertEquals("Audi", carDTOList.get(1).getBrand());
        assertEquals("Panda", carDTOList.get(2).getModel());
        assertEquals(2017, carDTOList.get(3).getManufacturingyear());

    }

    @Test
    void shouldAddCarsCorrectly() throws Exception {

        CarInputDto carInputDto = new CarInputDto();
        carInputDto.setBrand("Audi");
        carInputDto.setModel("A3");
        carInputDto.setManufacturingyear(2017);
        carInputDto.setId(21L);

        CarDTO carDTO = service.addCar(carInputDto);
        assertEquals("Audi", carDTO.getBrand());
        assertEquals("A3", carDTO.getModel());
        assertEquals(2017, carDTO.getManufacturingyear());
        assertEquals(21L, carDTO.getId());


    }

    @Test
    void shouldUpdateCarsCorrectly() throws Exception {
        Car car = new Car("TOYOTA", "Yaris", 2018, 20L);

        Mockito.when(carRepository.findById(20L)).thenReturn(Optional.of(car));
        //"Audi", "A3", 2015
        CarInputDto inputDto = new CarInputDto();
        inputDto.setBrand("Audi");
        inputDto.setModel("A3");
        inputDto.setManufacturingyear(2015);

        CarDTO carDTO = service.updateCar(20L, inputDto);

        assertEquals("Audi", carDTO.getBrand());


    }

    @Test
    void shouldDeletedCorrectCarById() throws Exception {
        Car car=new Car();
        car.setId(10L);
        service.deleteCar(10L);
        verify(carRepository).deleteById(10L);
    }

}


