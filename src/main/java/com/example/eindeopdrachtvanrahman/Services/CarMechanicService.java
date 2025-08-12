package com.example.eindeopdrachtvanrahman.Services;

import com.example.eindeopdrachtvanrahman.dto.CarMechanicDTO;
import com.example.eindeopdrachtvanrahman.exeptions.RecordNotFoundException;
import com.example.eindeopdrachtvanrahman.models.CarMechanic;
import com.example.eindeopdrachtvanrahman.models.ImageData;
import com.example.eindeopdrachtvanrahman.models.User;
import com.example.eindeopdrachtvanrahman.repository.CarMechanicRepository;
import com.example.eindeopdrachtvanrahman.repository.ImageRepository;
import com.example.eindeopdrachtvanrahman.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarMechanicService {
    private final CarMechanicRepository carMechanicRepository;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private CarMechanic carMechanic;

    public CarMechanicService(CarMechanicRepository carMechanicRepository, ImageRepository imageRepository, UserRepository userRepository) {
        this.carMechanicRepository = carMechanicRepository;
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
    }


    public List<CarMechanicDTO> getAllCarMechanics() {
        List<CarMechanic> carMechanicList = carMechanicRepository.findAll();
        List<CarMechanicDTO> carMechanicDTOList = new ArrayList<>();
        for (CarMechanic carMechanic : carMechanicList) {
            CarMechanicDTO dto = transferToDTO(carMechanic);
            carMechanicDTOList.add(dto);
        }
        return carMechanicDTOList;
    }

    public CarMechanicDTO getCarMechanicById(Long id) {
        Optional<CarMechanic> carMechanicOptional = carMechanicRepository.findById(id);
        if (carMechanicOptional.isPresent()) {
            CarMechanic carMechanic1 = carMechanicOptional.get();
            return transferToDTO(carMechanic1);
        } else {
            throw new RecordNotFoundException("no carMechanic found");
        }
    }


    public CarMechanic transferToCarMechanic(CarMechanicDTO dto) {
        var carMechanic = new CarMechanic();
        carMechanic.setId(dto.getId());

        return carMechanic;
    }

    public CarMechanicDTO transferToDTO(CarMechanic carMechanic) {
        CarMechanicDTO dto = new CarMechanicDTO();
        dto.setId(carMechanic.getId());
        return dto;
    }

    public CarMechanicDTO addCarMechanic(CarMechanicDTO dto) {
        CarMechanic carMechanic = transferToCarMechanic(dto);
        carMechanicRepository.save(carMechanic);
        return transferToDTO(carMechanic);
    }

    public CarMechanicDTO updateCarMechanic(Long id, CarMechanicDTO carMechanicDTO) {

        if (carMechanicRepository.findById(id).isPresent()) {

            CarMechanic carMechanic = carMechanicRepository.findById(id).get();

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

    public void assignImageToCarMechanic(Long id, String nameOfImage) {
        Optional<CarMechanic> carMechanic = carMechanicRepository.findById(id);
        Optional<ImageData> imageData = Optional.ofNullable(imageRepository.findByNameOfImage(nameOfImage));

        if (carMechanic.isPresent() && imageData.isPresent()) {
            CarMechanic carMechanic1 = carMechanic.get();
            ImageData imageData1 = imageData.get();

            carMechanic1.setImageData(imageData1);
            carMechanicRepository.save(carMechanic1);
        } else {
            throw new RecordNotFoundException();
        }
    }


    public void assignUserToCarMechanic(Long id, String username) {
        Optional<CarMechanic> carMechanic = carMechanicRepository.findById(id);
        Optional<User> nameOfUser = Optional.ofNullable(userRepository.findByUsername(username));

        if (nameOfUser.isPresent()) {
            CarMechanic carMechanic1 = carMechanic.get();
            User user = nameOfUser.get();
            carMechanic1.setUser(user);
            carMechanicRepository.save(carMechanic1);

        } else {
            throw new RecordNotFoundException();
        }


    }

}
