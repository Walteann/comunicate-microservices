package br.com.walteann.productapi.modules.product.service;

import br.com.walteann.productapi.configs.exception.SuccessResponse;
import br.com.walteann.productapi.configs.exception.ValidationException;
import br.com.walteann.productapi.modules.category.service.CategoryService;
import br.com.walteann.productapi.modules.product.dto.ProductRequestDTO;
import br.com.walteann.productapi.modules.product.dto.ProductResponseDTO;
import br.com.walteann.productapi.modules.product.model.Product;
import br.com.walteann.productapi.modules.product.repository.ProductRepository;
import br.com.walteann.productapi.modules.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class ProductService {

    private static final Integer ZERO = 0;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private CategoryService categoryService;

    public ProductResponseDTO save(ProductRequestDTO request) {
        validateProductDataInformed(request);
        validateCategoryAndSupplierIdInformed(request);
        var category = categoryService.findById(request.getCategoryId());
        var supplier = supplierService.findById(request.getSupplierId());
        var product = productRepository.save(Product.of(request, supplier, category));

        return ProductResponseDTO.of(product);
    }
    public ProductResponseDTO update(ProductRequestDTO request, Integer id) {
        validateProductDataInformed(request);
        validateCategoryAndSupplierIdInformed(request);
        validateInformedId(id);

        var category = categoryService.findById(request.getCategoryId());
        var supplier = supplierService.findById(request.getSupplierId());
        var product = Product.of(request, supplier, category);
        product.setId(id);
        productRepository.save(product);

        return ProductResponseDTO.of(product);
    }

    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductResponseDTO::of)
                .collect(Collectors.toList());
    }

    public List<ProductResponseDTO> findByName(String name) {
        return productRepository.findByNameIgnoreCaseContaining(name)
                .stream()
                .map(ProductResponseDTO::of)
                .collect(Collectors.toList());
    }

    public ProductResponseDTO findByIdResponse(Integer id) {
        return ProductResponseDTO.of(findById(id));
    }

    public Product findById(Integer id) {
        validateInformedId(id);

        return productRepository.findById(id).orElseThrow(() -> new ValidationException("There's no product for the given ID."));
    }

    public List<ProductResponseDTO> findBySupplierId(Integer supplierId) {
        if (isEmpty(supplierId)) {
            throw new ValidationException("The product supplier ID was not informed.");
        }

        return productRepository.findBySupplierId(supplierId)
                .stream()
                .map(ProductResponseDTO::of)
                .collect(Collectors.toList());
    }
    public List<ProductResponseDTO> findByCategoryId(Integer categoryId) {
        if (isEmpty(categoryId)) {
            throw new ValidationException("The product category ID was not informed.");
        }

        return productRepository.findByCategoryId(categoryId)
                .stream()
                .map(ProductResponseDTO::of)
                .collect(Collectors.toList());
    }

    public Boolean existsByCategoryId(Integer categoryId) {
        return productRepository.existsByCategoryId(categoryId);
    }

    public SuccessResponse delete(Integer id) {
        validateInformedId(id);
        productRepository.deleteById(id);
        return SuccessResponse.create("The product was deleted.");
    }

    public Boolean existsBySupplierId(Integer supplierId) {
        return productRepository.existsBySupplierId(supplierId);
    }

    private void validateProductDataInformed(ProductRequestDTO request) {

        if (isEmpty(request.getName())) {
            throw new ValidationException("The product name was not informed.");
        }

        if (isEmpty(request.getQuantityAvaliable())) {
            throw new ValidationException("The product quantityAvaliable was not informed.");
        }

        if (request.getQuantityAvaliable() <= ZERO) {
            throw new ValidationException("The quantity should not be less or equal to zero.");
        }

    }

    private void validateCategoryAndSupplierIdInformed(ProductRequestDTO request) {
        if (isEmpty(request.getCategoryId())) {
            throw new ValidationException("The product categoryId was not informed.");
        }

        if (isEmpty(request.getSupplierId())) {
            throw new ValidationException("The product supplierId was not informed.");
        }
    }

    private void validateInformedId(Integer id) {
        if (isEmpty(id)) {
            throw new ValidationException("The product id was not informed.");
        }
    }
}
