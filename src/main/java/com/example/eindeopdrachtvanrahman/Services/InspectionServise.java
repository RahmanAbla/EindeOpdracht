package com.example.eindeopdrachtvanrahman.Services;

import com.example.eindeopdrachtvanrahman.exeptions.RecordNotFoundException;
import com.example.eindeopdrachtvanrahman.models.CarMechanic;
import com.example.eindeopdrachtvanrahman.models.Inspection;
import com.example.eindeopdrachtvanrahman.dto.InspectionDTO;
import com.example.eindeopdrachtvanrahman.repository.CarMechanicRepository;
import com.example.eindeopdrachtvanrahman.repository.InspectionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class InspectionServise {
    private final InspectionRepository inspectionRepository;
    private final CarMechanicRepository carMechanicRepository;


    public InspectionServise(InspectionRepository inspectionRepository, CarMechanicRepository carMechanicRepository) {
        this.inspectionRepository = inspectionRepository;
        this.carMechanicRepository = carMechanicRepository;
    }

    public List<InspectionDTO> getAllInspections() {
        List<Inspection> inspectionList = inspectionRepository.findAll();
        List<InspectionDTO> inspectionDTOList = new ArrayList<>();
        for (Inspection inspection : inspectionList) {
            InspectionDTO dto = transferToDTO(inspection);
            inspectionDTOList.add(dto);
        }
        return inspectionDTOList;
    }

    public InspectionDTO getInspectionById(Long id) {
        Optional<Inspection> inspectionOptional = inspectionRepository.findById(id);
        if (inspectionOptional.isPresent()) {

            Inspection inspection1 = inspectionOptional.get();
            return transferToDTO(inspection1);
        } else {
            throw new RecordNotFoundException("no inspection found");
        }
    }


    public Inspection transferToInspection(InspectionDTO dto) {
        var inspection = new Inspection();
        inspection.setId(dto.getId());
        inspection.setInspectionCost(dto.getInspectionCost());
        inspection.setReport(dto.getReport());
        inspection.setStatus(dto.isStatus());
        inspection.setCarMechanic(dto.getCarMechanic());
        return inspection;
    }

    public InspectionDTO transferToDTO(Inspection inspection) {
        InspectionDTO dto = new InspectionDTO();
        dto.setId(inspection.getId());
        dto.setInspectionCost(inspection.getInspectionCost());
        dto.setReport(inspection.getReport());
        dto.setStatus(inspection.isStatus());
        if (inspection.getCarMechanic()!=null){
            dto.setCarMechanic(inspection.getCarMechanic());
        }
        return dto;
    }

    public InspectionDTO addInspection(InspectionDTO dto) {

        Inspection inspection = transferToInspection(dto);
        inspectionRepository.save(inspection);

        return transferToDTO(inspection);
    }

    public InspectionDTO updateInspection(Long id, InspectionDTO inspectionDTO) {

        if (inspectionRepository.findById(id).isPresent()) {

            Inspection inspection = inspectionRepository.findById(id).get();

            Inspection inspection1 = transferToInspection(inspectionDTO);
            inspection1.setId(inspection.getId());

            inspectionRepository.save(inspection1);

            return transferToDTO(inspection1);

        } else {

            throw new RecordNotFoundException();

        }

    }
    public void deleteInspection(Long id) {

        inspectionRepository.deleteById(id);

    }
    public void assignCarMechanicToInspection(Long id, Long carMechanicId) {
        var optionalInspection = inspectionRepository.findById(id);
        var optionalCarMechanic = carMechanicRepository.findById(carMechanicId);

        if (optionalInspection.isPresent() &&optionalCarMechanic.isPresent()) {
            var inspection = optionalInspection.get();
            var carMechanic = optionalCarMechanic.get();

            inspection.setCarMechanic(carMechanic);
            inspectionRepository.save(inspection);
        } else {
            throw new RecordNotFoundException();
        }
    }


}