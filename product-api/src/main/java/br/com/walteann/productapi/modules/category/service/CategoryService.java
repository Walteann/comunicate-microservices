package br.com.walteann.productapi.modules.category.service;

import br.com.walteann.productapi.configs.exception.ValidationException;
import br.com.walteann.productapi.modules.category.dto.CategoryRequestDTO;
import br.com.walteann.productapi.modules.category.dto.CategoryResponseDTO;
import br.com.walteann.productapi.modules.category.model.Category;
import br.com.walteann.productapi.modules.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import static org.springframework.util.ObjectUtils.isEmpty;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryResponseDTO save(CategoryRequestDTO request) {
        validateCategoryDescriptionInformed(request);
        var category = categoryRepository.save(Category.of(request));
        return CategoryResponseDTO.of(category);
    }

    private void validateCategoryDescriptionInformed(CategoryRequestDTO request) {
        if (isEmpty(request.getDescription())) {
            throw new ValidationException("The category description was not informed.");
        }
    }

}
