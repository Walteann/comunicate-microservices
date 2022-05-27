package br.com.walteann.productapi.modules.supplier.service;

import br.com.walteann.productapi.configs.exception.ValidationException;
import br.com.walteann.productapi.modules.supplier.dto.SupplierRequestDTO;
import br.com.walteann.productapi.modules.supplier.dto.SupplierResponseDTO;
import br.com.walteann.productapi.modules.supplier.model.Supplier;
import br.com.walteann.productapi.modules.supplier.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public SupplierResponseDTO save(SupplierRequestDTO request) {
        validateSupplierNameInformed(request);
        var supplier = supplierRepository.save(Supplier.of(request));
        return SupplierResponseDTO.of(supplier);
    }

    private void validateSupplierNameInformed(SupplierRequestDTO request) {
        if (isEmpty(request.getName())) {
            throw new ValidationException("the supplier name was not informed.");
        }
    }
}
