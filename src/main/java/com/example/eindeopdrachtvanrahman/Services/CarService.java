package com.example.eindeopdrachtvanrahman.Services;

import com.example.eindeopdrachtvanrahman.dto.CarDTO;
import com.example.eindeopdrachtvanrahman.exeptions.RecordNotFoundException;
import com.example.eindeopdrachtvanrahman.inputDTO.CarInputDto;
import com.example.eindeopdrachtvanrahman.models.Car;
import com.example.eindeopdrachtvanrahman.repository.CarRepository;
import com.example.eindeopdrachtvanrahman.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;

    public CarService(CarRepository carRepository, ClientRepository clientRepository) {
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
    }

    public List<CarDTO> getAllCars() {
        List<Car> carList = carRepository.findAll();
        List<CarDTO> carDTOList = new ArrayList<>();
        for (Car car : carList) {
            CarDTO dto = transferToDTO(car);
            carDTOList.add(dto);
        }
        return carDTOList;
    }

    public CarDTO getCarsById(Long id) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) {

            Car car1 = carOptional.get();
            return transferToDTO(car1);
        } else {
            throw new RecordNotFoundException("no car found");
        }
    }


    public Car transferToCar(CarInputDto dto) {
        var car = new Car();

        car.setId(dto.getId());
        car.setBrand(dto.getBrand());
        car.setManufacturingyear(dto.getManufacturingyear());
        car.setModel(dto.getModel());
        car.setClient(dto.getClient());

        return car;
    }

    public CarDTO transferToDTO(Car car) {
        CarDTO dto = new CarDTO();
        dto.setBrand(car.getBrand());
        dto.setId(car.getId());
        dto.setManufacturingyear(car.getManufacturingyear());
        dto.setModel(car.getModel());
        if (!(car.getClient() == null)) {
            dto.setClient(car.getClient());
        }
        return dto;
    }

    public CarDTO addCar(CarInputDto dto) {

        Car car = transferToCar(dto);
        carRepository.save(car);

        return transferToDTO(car);
    }

    public CarDTO updateCar(Long id, CarInputDto inputDto) {

        if (carRepository.findById(id).isPresent()) {

            Car car = carRepository.findById(id).get();

            Car car1 = transferToCar(inputDto);

            car1.setId(car.getId());

            carRepository.save(car1);

            return transferToDTO(car1);

        } else {

            throw new RecordNotFoundException();

        }

    }

    public void deleteCar(@RequestBody Long id) {

        carRepository.deleteById(id);

    }

    public void assignClientToCar(Long id, Long clientId) {
        var optionalCar = carRepository.findById(id);
        var optionaClient = clientRepository.findById(clientId);

        if (optionalCar.isPresent() && optionaClient.isPresent()) {
            var car = optionalCar.get();
            var client = optionaClient.get();

            car.setClient(client);
            carRepository.save(car);
        } else {
            throw new RecordNotFoundException();
        }
    }


}
