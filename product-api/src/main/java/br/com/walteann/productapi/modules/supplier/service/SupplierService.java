package br.com.walteann.productapi.modules.supplier.service;

import br.com.walteann.productapi.configs.exception.SuccessResponse;
import br.com.walteann.productapi.configs.exception.ValidationException;
import br.com.walteann.productapi.modules.product.service.ProductService;
import br.com.walteann.productapi.modules.supplier.dto.SupplierRequestDTO;
import br.com.walteann.productapi.modules.supplier.dto.SupplierResponseDTO;
import br.com.walteann.productapi.modules.supplier.model.Supplier;
import br.com.walteann.productapi.modules.supplier.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Lazy
    @Autowired
    private ProductService productService;

    public SupplierResponseDTO save(SupplierRequestDTO request) {
        validateSupplierNameInformed(request);
        var supplier = supplierRepository.save(Supplier.of(request));
        return SupplierResponseDTO.of(supplier);
    }
    public SupplierResponseDTO update(SupplierRequestDTO request, Integer id) {
        validateSupplierNameInformed(request);
        validateInformedId(id);
        var supplier = Supplier.of(request);
        supplier.setId(id);
        supplierRepository.save(supplier);
        return SupplierResponseDTO.of(supplier);
    }

    public SupplierResponseDTO findByIdResponse(Integer id) {
        return SupplierResponseDTO.of(findById(id));
    }

    public Supplier findById(Integer id) {
        validateInformedId(id);
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ValidationException("There's no supplier for the given ID."));
    }

    public List<SupplierResponseDTO> findByName(String name) {
        if (isEmpty(name)) {
            throw new ValidationException("The supplier name was not informed.");
        }

        return supplierRepository.findByNameIgnoreCaseContaining(name)
                .stream()
                .map(SupplierResponseDTO::of)
                .collect(Collectors.toList());
    }

    public List<SupplierResponseDTO> findAll() {
        return supplierRepository.findAll().stream()
                .map(SupplierResponseDTO::of)
                .collect(Collectors.toList());
    }

    public SuccessResponse delete(Integer id) {
        validateInformedId(id);

        if (productService.existsBySupplierId(id)) {
            throw new ValidationException("You cannot delete this supplier because it's already defined by a product.");
        }

        supplierRepository.deleteById(id);
        return SuccessResponse.create("The supplier was deleted.");
    }

    private void validateSupplierNameInformed(SupplierRequestDTO request) {
        if (isEmpty(request.getName())) {
            throw new ValidationException("the supplier name was not informed.");
        }
    }

    private void validateInformedId(Integer id) {
        if (isEmpty(id)) {
            throw new ValidationException("The supplier ID was not informed.");
        }
    }
}
