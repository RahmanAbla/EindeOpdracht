package com.example.eindeopdrachtvanrahman.sequence_diagram.Services;

import com.example.eindeopdrachtvanrahman.models.Car;
import com.example.eindeopdrachtvanrahman.dto.RecordNotFoundException;
import com.example.eindeopdrachtvanrahman.dto.CarDTO;
import com.example.eindeopdrachtvanrahman.dto.CarInputDto;
import com.example.eindeopdrachtvanrahman.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
   public List<CarDTO>getAllCars(){
     List<Car>carList=carRepository.findAll();
     List<CarDTO>carDTOList=new ArrayList<>();
     for (Car car: carList){
         CarDTO dto=transferToDTO(car);
         carDTOList.add(dto);
     }
     return carDTOList;
    }

    public CarDTO getCarsById(Long id) throws Exception {
        Optional<Car>carOptional = carRepository.findById(id);
        if (carOptional.isPresent()){

            Car car1 = carOptional.get();
            return transferToDTO(car1);
        } else {
            throw new Exception("no car found");
        }
    }


    public Car transferToCar(CarInputDto dto){
        var car=new Car();

        car.setId(dto.getId());
        car.setBrand(dto.getBrand());
        car.setManufacturingyear(dto.getManufacturingyear());
        car.setModel(dto.getModel());
        return car;
}
public CarDTO transferToDTO(Car car){
        CarDTO dto=new CarDTO();
        dto.setBrand(car.getBrand());
        dto.setId(car.getId());
        dto.setManufacturingyear(car.getManufacturingyear());
        dto.setModel(car.getModel());
        return dto;
}
    public CarDTO addCar(CarInputDto dto) {

        Car car = transferToCar(dto);
        carRepository.save(car);

        return transferToDTO(car);
    }

    public CarDTO updateCar(Long id, CarInputDto inputDto) throws RecordNotFoundException {

        if (carRepository.findById(id).isPresent()){

            Car car = carRepository.findById(id).get();

            Car car1 = transferToCar(inputDto);
            car1.setId(car.getId());

            carRepository.save(car1);

            return transferToDTO(car1);

        } else {

            throw new  RecordNotFoundException();

        }

    }
    public void deleteCar(@RequestBody Long id) {

        carRepository.deleteById(id);

    }

}
