package br.com.walteann.productapi.modules.supplier.controller;

import br.com.walteann.productapi.modules.supplier.dto.SupplierRequestDTO;
import br.com.walteann.productapi.modules.supplier.dto.SupplierResponseDTO;
import br.com.walteann.productapi.modules.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public SupplierResponseDTO save(@RequestBody SupplierRequestDTO supplierRequestDTO) {
        return supplierService.save(supplierRequestDTO);
    }
}
