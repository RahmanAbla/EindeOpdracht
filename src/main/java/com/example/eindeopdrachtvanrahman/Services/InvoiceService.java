package com.example.eindeopdrachtvanrahman.Services;

import com.example.eindeopdrachtvanrahman.models.Invoice;
import com.example.eindeopdrachtvanrahman.dto.RecordNotFoundException;
import com.example.eindeopdrachtvanrahman.dto.InvoiceDTO;
import com.example.eindeopdrachtvanrahman.repository.InvoceRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class InvoiceService {
    private final InvoceRepository invoceRepository;

    public InvoiceService(InvoceRepository invoceRepository) {
        this.invoceRepository = invoceRepository;
    }

    public List<InvoiceDTO> getAllInvoces(){
            List<Invoice>invoiceList=invoceRepository.findAll();
        List<InvoiceDTO>invoiceDTOList=new ArrayList<>();
        for (Invoice invoice: invoiceList){
            InvoiceDTO dto=transferToDTO(invoice);
            invoiceDTOList.add(dto);
        }
        return invoiceDTOList;
    }
    public InvoiceDTO getInvoicesById(Long id) throws Exception {
            Optional <Invoice>invoiceOptional=invoceRepository.findById(id);
        if (invoiceOptional.isPresent()){

            Invoice invoice1 = invoiceOptional.get();
            return transferToDTO(invoice1);
        } else {
            throw new Exception("no invoice found");
        }
    }
    public Invoice transferToInvoce(InvoiceDTO dto){
            var invoce=new Invoice();

        invoce.setId(dto.getId());
        invoce.setDescription(dto.getDescription());
        invoce.setInspectionCost(dto.getInspectionCost());
        invoce.setPartCost(dto.getPartCost());
        invoce.setRepairCost(dto.getRepairCost());
        invoce.setTotalCost(dto.getTotalCost());
        return invoce;
    }
    public InvoiceDTO transferToDTO(Invoice invoice){
        InvoiceDTO dto=new InvoiceDTO();
        dto.setId(invoice.getId());
        dto.setDescription(invoice.getDescription());
        dto.setInspectionCost(invoice.getInspectionCost());
        dto.setPartCost(invoice.getPartCost());
        dto.setTotalCost(invoice.getTotalCost());
        dto.setRepairCost(invoice.getRepairCost());
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


    public InvoiceDTO addInvoice(InvoiceDTO dto) {

        Invoice invoice = transferToInvoce(dto);
        invoceRepository.save(invoice);

        return transferToDTO(invoice);
    }

    public InvoiceDTO updateInvoice(Long id, InvoiceDTO invoiceDTO) throws RecordNotFoundException {

        if (invoceRepository.findById(id).isPresent()){

            Invoice invoice = invoceRepository.findById(id).get();

            Invoice invoice1 = transferToInvoce(invoiceDTO);
            invoice1.setId(invoice.getId());

            invoceRepository.save(invoice1);

            return transferToDTO(invoice1);

        } else {

            throw new  RecordNotFoundException();

        }

    }
    public void deleteInvoice(@RequestBody Long id){
        invoceRepository.deleteById(id);
    }

}
