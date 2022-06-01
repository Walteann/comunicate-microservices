package br.com.walteann.productapi.modules.product.controller;

import br.com.walteann.productapi.configs.exception.SuccessResponse;
import br.com.walteann.productapi.modules.product.dto.ProductRequestDTO;
import br.com.walteann.productapi.modules.product.dto.ProductResponseDTO;
import br.com.walteann.productapi.modules.product.service.ProductService;
import br.com.walteann.productapi.modules.supplier.dto.SupplierRequestDTO;
import br.com.walteann.productapi.modules.supplier.dto.SupplierResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ProductResponseDTO save(@RequestBody ProductRequestDTO request) {
        return productService.save(request);
    }

    @GetMapping
    public List<ProductResponseDTO> findAll() {
        return productService.findAll();
    }

    @GetMapping("{id}")
    public ProductResponseDTO findById(@PathVariable Integer id) {
        return productService.findByIdResponse(id);
    }

    @GetMapping("name/{name}")
    public List<ProductResponseDTO> findByName(@PathVariable String name) {
        return productService.findByName(name);
    }

    @GetMapping("supplier/{supplierId}")
    public List<ProductResponseDTO> findBySupplierId(@PathVariable Integer supplierId) {
        return productService.findBySupplierId(supplierId);
    }

    @GetMapping("category/{categoryId}")
    public List<ProductResponseDTO> findByCategoryId(@PathVariable Integer categoryId) {
        return productService.findByCategoryId(categoryId);
    }

    @PutMapping("{id}")
    public ProductResponseDTO update(@RequestBody ProductRequestDTO request, @PathVariable Integer id) {
        return productService.update(request, id);
    }

    @DeleteMapping("{id}")
    public SuccessResponse delete(@PathVariable Integer id) {
        return productService.delete(id);
    }

}
