package br.com.walteann.productapi.modules.category.service;

import br.com.walteann.productapi.configs.exception.SuccessResponse;
import br.com.walteann.productapi.configs.exception.ValidationException;
import br.com.walteann.productapi.modules.category.dto.CategoryRequestDTO;
import br.com.walteann.productapi.modules.category.dto.CategoryResponseDTO;
import br.com.walteann.productapi.modules.category.model.Category;
import br.com.walteann.productapi.modules.category.repository.CategoryRepository;
import br.com.walteann.productapi.modules.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;


import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.util.ObjectUtils.isEmpty;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Lazy
    @Autowired
    private ProductService productService;

    public CategoryResponseDTO save(CategoryRequestDTO request) {
        validateCategoryDescriptionInformed(request);
        var category = categoryRepository.save(Category.of(request));
        return CategoryResponseDTO.of(category);
    }
    public CategoryResponseDTO update(CategoryRequestDTO request, Integer id) {
        validateCategoryDescriptionInformed(request);
        validateInformedId(id);
        var category = Category.of(request);
        category.setId(id);
        categoryRepository.save(category);
        return CategoryResponseDTO.of(category);
    }


    public CategoryResponseDTO findByIdResponse(Integer id) {
        return CategoryResponseDTO.of(findById(id));
    }

    public Category findById(Integer id) {
        validateInformedId(id);
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ValidationException("There's no category for the given ID."));
    }

    public List<CategoryResponseDTO> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryResponseDTO::of)
                .collect(toList());
    }

    public List<CategoryResponseDTO> findByDescription(String description) {

        if (isEmpty(description)) {
            throw new ValidationException("The category description must be informed.");
        }

        return categoryRepository.findByDescriptionIgnoreCaseContaining(description)
                .stream()
                .map(CategoryResponseDTO::of)
                .collect(toList());
    }

    public SuccessResponse delete(Integer id) {
        validateInformedId(id);

        if (productService.existsByCategoryId(id)) {
            throw new ValidationException("You cannot delete this category because it's already defined by a product.");
        }

        categoryRepository.deleteById(id);
        return SuccessResponse.create("The category was deleted.");
    }

    private void validateCategoryDescriptionInformed(CategoryRequestDTO request) {
        if (isEmpty(request.getDescription())) {
            throw new ValidationException("The category description was not informed.");
        }
    }

    private void validateInformedId(Integer id) {
        if (isEmpty(id)) {
            throw new ValidationException("The category ID must be informed.");
        }
    }

}
