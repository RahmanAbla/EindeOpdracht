package com.example.eindeopdrachtvanrahman.Services;

import com.example.eindeopdrachtvanrahman.dto.InvoiceDTO;
import com.example.eindeopdrachtvanrahman.exeptions.RecordNotFoundException;
import com.example.eindeopdrachtvanrahman.models.Invoice;
import com.example.eindeopdrachtvanrahman.repository.CarMechanicRepository;
import com.example.eindeopdrachtvanrahman.repository.CarRepository;
import com.example.eindeopdrachtvanrahman.repository.ClientRepository;
import com.example.eindeopdrachtvanrahman.repository.InvoceRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    private final InvoceRepository invoceRepository;
    private final CarMechanicRepository carMechanicRepository;
    private final ClientRepository clientRepository;
    private final CarRepository carRepository;

    public InvoiceService(InvoceRepository invoceRepository, CarMechanicRepository carMechanicRepository, ClientRepository clientRepository, CarRepository carRepository) {
        this.invoceRepository = invoceRepository;
        this.carMechanicRepository = carMechanicRepository;
        this.clientRepository = clientRepository;
        this.carRepository = carRepository;
    }


    public List<InvoiceDTO> getAllInvoces() {
        List<Invoice> invoiceList = invoceRepository.findAll();
        List<InvoiceDTO> invoiceDTOList = new ArrayList<>();
        for (Invoice invoice : invoiceList) {
            InvoiceDTO dto = transferToDTO(invoice);
            invoiceDTOList.add(dto);
        }
        return invoiceDTOList;
    }

    public InvoiceDTO getInvoicesById(Long id) {
        Optional<Invoice> invoiceOptional = invoceRepository.findById(id);
        if (invoiceOptional.isPresent()) {

            Invoice invoice1 = invoiceOptional.get();
            return transferToDTO(invoice1);
        } else {
            throw new RecordNotFoundException("no invoice found");
        }
    }

    public Invoice transferToInvoce(InvoiceDTO dto) {
        var invoce = new Invoice();

        invoce.setId(dto.getId());
        invoce.setDescription(dto.getDescription());
        invoce.setInspectionCost(dto.getInspectionCost());
        invoce.setPartCost(dto.getPartCost());
        invoce.setRepairCost(dto.getRepairCost());
        invoce.setTotalCost(dto.getTotalCost());
        return invoce;
    }

    public InvoiceDTO transferToDTO(Invoice invoice) {
        InvoiceDTO dto = new InvoiceDTO();
        dto.setId(invoice.getId());
        dto.setDescription(invoice.getDescription());
        dto.setInspectionCost(invoice.getInspectionCost());
        dto.setPartCost(invoice.getPartCost());
        dto.setTotalCost(invoice.getTotalCost());
        dto.setRepairCost(invoice.getRepairCost());
        dto.setDate(invoice.getDate());
        if (invoice.getClient() != null) {
            dto.setClientId(invoice.getClient());
        }
        if (invoice.getCarMechanic() != null) {
            dto.setCarMechanic(invoice.getCarMechanic());
        }
        if (invoice.getCar() != null) {
            dto.setCar(invoice.getCar());
        }
        return dto;
    }

    public InvoiceDTO getInvoce(Long id) {
        return null;
    }

    public List<InvoiceDTO> getAllInvoices() {
        List<Invoice> invoiceList = invoceRepository.findAll();
        List<InvoiceDTO> invoiceDTOList = new ArrayList<>();
        for (Invoice invoice : invoiceList) {
            InvoiceDTO dto = transferToDTO(invoice);
            invoiceDTOList.add(dto);
        }
        return invoiceDTOList;
    }


    public InvoiceDTO addInvoice(InvoiceDTO dto, Double repairCost, Double partCost, Double inspectionCost) {

        Invoice invoice = transferToInvoce(dto);
        invoice.setRepairCost(repairCost);
        Double repair = invoice.getRepairCost();
        invoice.setPartCost(partCost);
        Double part = invoice.getPartCost();
        invoice.setInspectionCost(inspectionCost);
        Double inspection = invoice.getInspectionCost();
        Double total = repair + part + inspection;
        invoice.setDate();
        invoice.setTotalCost(total);
        invoceRepository.save(invoice);

        return transferToDTO(invoice);
    }

    public InvoiceDTO updateInvoice(Long id, InvoiceDTO invoiceDTO) {

        if (invoceRepository.findById(id).isPresent()) {

            Invoice invoice = invoceRepository.findById(id).get();

            Invoice invoice1 = transferToInvoce(invoiceDTO);
            invoice1.setId(invoice.getId());

            invoceRepository.save(invoice1);

            return transferToDTO(invoice1);

        } else {

            throw new RecordNotFoundException();

        }

    }

    public void deleteInvoice(@RequestBody Long id) {
        invoceRepository.deleteById(id);
    }

    public void assignCarMechanicToInvoice(Long id, Long carMechanicId) {
        var optionalInvoice = invoceRepository.findById(id);
        var optionalCarMechanic = carMechanicRepository.findById(carMechanicId);

        if (optionalInvoice.isPresent() && optionalCarMechanic.isPresent()) {
            var invoice = optionalInvoice.get();
            var carMechanic = optionalCarMechanic.get();

            invoice.setCarMechanic(carMechanic);
            invoice.setDate();
            invoceRepository.save(invoice);
        } else {
            throw new RecordNotFoundException();
        }
    }

    public void assignClientToInvoice(Long id, Long clientId) {
        var optionalInvoice = invoceRepository.findById(id);
        var optionalClient = clientRepository.findById(clientId);

        if (optionalInvoice.isPresent() && optionalClient.isPresent()) {
            var invoice = optionalInvoice.get();
            var client = optionalClient.get();

            invoice.setClient(client);
            invoice.setDate();
            invoceRepository.save(invoice);
        } else {
            throw new RecordNotFoundException();
        }
    }

    public void assignCarToInvoice(Long id, Long carId) {
        var optionalInvoice = invoceRepository.findById(id);
        var optionalCar = carRepository.findById(carId);

        if (optionalInvoice.isPresent() && optionalCar.isPresent()) {
            var invoice = optionalInvoice.get();
            var car = optionalCar.get();

            invoice.setCar(car);
            invoice.setDate();
            invoceRepository.save(invoice);
        } else {
            throw new RecordNotFoundException();
        }

    }
}
