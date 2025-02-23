package com.example.eindeopdrachtvanrahman.Services;

import com.example.eindeopdrachtvanrahman.dto.RecordNotFoundException;
import com.example.eindeopdrachtvanrahman.dto.CarMechanicDTO;
import com.example.eindeopdrachtvanrahman.models.CarMechanic;
import com.example.eindeopdrachtvanrahman.repository.CarMechanicRepository;
import com.example.eindeopdrachtvanrahman.repository.GarageReseptionistRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarMechanicService {
    private final CarMechanicRepository carMechanicRepository;
    private GarageReseptionistRepository garageReseptionistRepository;
    private  GarageReceptionistService garageReceptionistService;

    public CarMechanicService(CarMechanicRepository carMechanicRepository, GarageReseptionistRepository garageReseptionistRepository,
                              GarageReceptionistService garageReceptionistService) {
        this.carMechanicRepository = carMechanicRepository;
        this.garageReseptionistRepository = garageReseptionistRepository;
        this.garageReceptionistService = garageReceptionistService;
    }

    public List<CarMechanicDTO>getAllCarMechanics(){
        List<CarMechanic>carMechanicList=carMechanicRepository.findAll();
        List<CarMechanicDTO>carMechanicDTOList=new ArrayList<>();
        for (CarMechanic carMechanic:carMechanicList){
            CarMechanicDTO dto=transferToDTO(carMechanic);
            carMechanicDTOList.add(dto);
        }
return carMechanicDTOList;
}
    public CarMechanicDTO getCarMechanicById(Long id) throws Exception {
        Optional<CarMechanic>carMechanicOptional = carMechanicRepository.findById(id);
        if (carMechanicOptional.isPresent()){
            CarMechanic carMechanic1  =carMechanicOptional.get();
            CarMechanicDTO dto=transferToDTO(carMechanic1);
            if(carMechanic1.getGarageReceptionist()!=null){
                dto.setGarageReceptionistDTO(garageReceptionistService.transferToDTO(carMechanic1.getGarageReceptionist()));
            }
            return transferToDTO(carMechanic1);
        } else {
            throw new Exception("no carMechanic found");
        }
    }
    public CarMechanic transferToCarMechanic(CarMechanicDTO dto){
        var carMechanic=new CarMechanic();
        carMechanic.setId(dto.getId());
        carMechanic.setAge(dto.getAge());
        carMechanic.setAddress(dto.getAddress());
        carMechanic.setName(dto.getName());
        carMechanic.setEmail(dto.getEmail());

return carMechanic;
    }

    public CarMechanicDTO transferToDTO(CarMechanic carMechanic){
        CarMechanicDTO dto=new CarMechanicDTO();
        dto.setId(carMechanic.getId());
        dto.setAge(carMechanic.getAge());
        dto.setAddress(carMechanic.getAddress());
        dto.setName(carMechanic.getName());
        dto.setEmail(carMechanic.getEmail());
        return dto;
    }
    public CarMechanicDTO addCarMechanic(CarMechanicDTO dto) {
        CarMechanic carMechanic=transferToCarMechanic(dto);
        carMechanicRepository.save(carMechanic);
        return  transferToDTO(carMechanic);
    }
    public CarMechanicDTO updateCarMechanic(Long id, CarMechanicDTO carMechanicDTO) throws RecordNotFoundException {

        if (carMechanicRepository.findById(id).isPresent()) {

            CarMechanic carMechanic =carMechanicRepository.findById(id).get();

            CarMechanic carMechanic1 = transferToCarMechanic(carMechanicDTO);
            carMechanic1.setId(carMechanic.getId());

            carMechanicRepository.save(carMechanic1);

            return transferToDTO(carMechanic1);

        } else {

            throw new RecordNotFoundException();

        }

    }
    public void deleteCarMechanic(@RequestBody Long id) {

        carMechanicRepository.deleteById(id);

    }
    public void assignGarageReseptionistToCarMechanic(Long id, Long garageReseptionistId) throws RecordNotFoundException {
        var optionalCarMechanic = carMechanicRepository.findById(id);
        var optionalGarageReseptionist = garageReseptionistRepository.findById(garageReseptionistId);

        if(optionalCarMechanic.isPresent() && optionalGarageReseptionist.isPresent()) {
            var carMechanic= optionalCarMechanic.get();
            var garageReseptionist = optionalGarageReseptionist.get();

            carMechanic.setGarageReceptionist(garageReseptionist);
            carMechanicRepository.save(carMechanic);
        } else {
            throw new RecordNotFoundException();
        }
    }
}