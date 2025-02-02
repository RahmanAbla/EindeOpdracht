package com.example.eindeopdrachtvanrahman.Services;

import com.example.eindeopdrachtvanrahman.dto.RecordNotFoundException;
import com.example.eindeopdrachtvanrahman.dto.RepairDTO;
import com.example.eindeopdrachtvanrahman.models.Repair;
import com.example.eindeopdrachtvanrahman.repository.RepairRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RepairService {
    private final RepairRepository repairRepository;

    public RepairService(RepairRepository repairRepository) {
        this.repairRepository = repairRepository;
    }

    public List<RepairDTO>getAllRepairs() {
        List<Repair>repairList = repairRepository.findAll();
        List<RepairDTO> repairDTOList = new ArrayList<>();
        for (Repair repair :repairList) {
            RepairDTO dto = transferToDTO(repair);
            repairDTOList.add(dto);
        }
        return repairDTOList;
    }


    public RepairDTO getRepairById(Long id) throws Exception {
        Optional<Repair> repairOptional = repairRepository.findById(id);
        if (repairOptional.isPresent()){

            Repair repair1 = repairOptional.get();
            return transferToDTO(repair1);
        } else {
            throw new Exception("no repair found");
        }
    }



    public Repair transferToRepair(RepairDTO dto){
            var repair=new Repair();
            repair.setId(dto.getId());
        repair.setDescription(dto.getDescription());
        repair.setTreatment(dto.getTreatment());
        repair.setStatus(dto.isStatus());
        return repair;
    }
    public RepairDTO transferToDTO(Repair repair) {
        RepairDTO dto = new RepairDTO();
        dto.setId(repair.getId());
        dto.setDescription(repair.getDescription());
        dto.setTreatment(repair.getTreatment());
        dto.setStatus(repair.isStatus());
        return dto;
    }
    public RepairDTO addRepair(RepairDTO dto) {
        Repair repair=transferToRepair(dto);
        repairRepository.save(repair);
        return transferToDTO(repair);
    }

    public RepairDTO updateRepair(Long id, RepairDTO repairDTO) throws RecordNotFoundException {

        if (repairRepository.findById(id).isPresent()) {

            Repair repair = repairRepository.findById(id).get();

            Repair repair1 = transferToRepair(repairDTO);
            repair1.setId(repair.getId());

            repairRepository.save(repair1);

            return transferToDTO(repair1);

        } else {

            throw new RecordNotFoundException();

        }

    }

    public void deleteRepair(@RequestBody Long id) {

        repairRepository.deleteById(id);

    }
}
