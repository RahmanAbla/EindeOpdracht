package com.example.eindeopdrachtvanrahman.Services;

import com.example.eindeopdrachtvanrahman.models.GarageReceptionist;
import com.example.eindeopdrachtvanrahman.dto.RecordNotFoundException;
import com.example.eindeopdrachtvanrahman.dto.GarageReceptionistDTO;
import com.example.eindeopdrachtvanrahman.repository.GarageReseptionistRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GarageReceptionistService {
    private final GarageReseptionistRepository garageReseptionistRepository;

    public GarageReceptionistService(GarageReseptionistRepository garageReseptionistRepository) {
        this.garageReseptionistRepository = garageReseptionistRepository;
    }
    public List<GarageReceptionistDTO>getAllGarageReceptionists(){
        List<GarageReceptionist>garageReceptionistList=garageReseptionistRepository.findAll();
        List<GarageReceptionistDTO>garageReceptionistDTOList=new ArrayList<>();
        for (GarageReceptionist garageReceptionist:garageReceptionistList){
            GarageReceptionistDTO dto=transferToDTO(garageReceptionist);
            garageReceptionistDTOList.add(dto);
        } return garageReceptionistDTOList;

    }
    public GarageReceptionistDTO getGarageReceptionistById(Long id)throws Exception{
        Optional<GarageReceptionist>garageReceptionistOptional=garageReseptionistRepository.findById(id);
        if (garageReceptionistOptional.isPresent()){
            GarageReceptionist garageReceptionist1=garageReceptionistOptional.get();
            return transferToDTO(garageReceptionist1);
        }else{throw new Exception("no garage receptionist found");

        }
    }


    public GarageReceptionist transferToGarageReceptionist(GarageReceptionistDTO dto){
        var garageReceptionist=new GarageReceptionist();
        garageReceptionist.setId(dto.getId());
        garageReceptionist.setEmail(dto.getEmail());
        garageReceptionist.setName(dto.getName());
        garageReceptionist.setPhoneNumber(dto.getPhoneNumber());
        return garageReceptionist;
   }
   public GarageReceptionistDTO transferToDTO(GarageReceptionist garageReceptionist){
      GarageReceptionistDTO dto=new GarageReceptionistDTO();
      dto.setId(garageReceptionist.getId());
      dto.setEmail(garageReceptionist.getEmail());
      dto.setName(garageReceptionist.getName());
      dto.setPhoneNumber(garageReceptionist.getPhoneNumber());
      return dto;

   }
    public GarageReceptionistDTO addGarageReceptionist(GarageReceptionistDTO dto) {
        GarageReceptionist garageReceptionist=transferToGarageReceptionist(dto);
        garageReseptionistRepository.save(garageReceptionist);
        return  transferToDTO(garageReceptionist);
    }
    public GarageReceptionistDTO updateGarageReceptionist(Long id, GarageReceptionistDTO garageReceptionistDTO) throws RecordNotFoundException {

        if (garageReseptionistRepository.findById(id).isPresent()) {

            GarageReceptionist garageReceptionist = garageReseptionistRepository.findById(id).get();

            GarageReceptionist garageReceptionist1 = transferToGarageReceptionist(garageReceptionistDTO);
            garageReceptionist1.setId(garageReceptionist.getId());

            garageReseptionistRepository.save(garageReceptionist1);

            return transferToDTO(garageReceptionist1);

        } else {

            throw new RecordNotFoundException();

        }

    }

    public void deletegarageReseptionist(@RequestBody Long id) {

        garageReseptionistRepository.deleteById(id);

    }

}
