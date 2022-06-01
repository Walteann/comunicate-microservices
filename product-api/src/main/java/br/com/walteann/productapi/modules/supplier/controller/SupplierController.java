package br.com.walteann.productapi.modules.supplier.controller;

import br.com.walteann.productapi.configs.exception.SuccessResponse;
import br.com.walteann.productapi.modules.supplier.dto.SupplierRequestDTO;
import br.com.walteann.productapi.modules.supplier.dto.SupplierResponseDTO;
import br.com.walteann.productapi.modules.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public SupplierResponseDTO save(@RequestBody SupplierRequestDTO supplierRequestDTO) {
        return supplierService.save(supplierRequestDTO);
    }

    @GetMapping
    public List<SupplierResponseDTO> findAll() {
        return supplierService.findAll();
    }

    @GetMapping("{id}")
    public SupplierResponseDTO findById(@PathVariable Integer id) {
        return supplierService.findByIdResponse(id);
    }

    @GetMapping("name/{name}")
    public List<SupplierResponseDTO> findByName(@PathVariable String name) {
        return supplierService.findByName(name);
    }

    @PutMapping("{id}")
    public SupplierResponseDTO update(@RequestBody SupplierRequestDTO request, @PathVariable Integer id) {
        return supplierService.update(request, id);
    }

    @DeleteMapping("{id}")
    public SuccessResponse delete(@PathVariable Integer id) {
        return supplierService.delete(id);
    }
}
