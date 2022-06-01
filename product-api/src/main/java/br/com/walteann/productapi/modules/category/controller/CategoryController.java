package br.com.walteann.productapi.modules.category.controller;

import br.com.walteann.productapi.configs.exception.SuccessResponse;
import br.com.walteann.productapi.modules.category.dto.CategoryRequestDTO;
import br.com.walteann.productapi.modules.category.dto.CategoryResponseDTO;
import br.com.walteann.productapi.modules.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponseDTO save(@RequestBody CategoryRequestDTO request) {
        return categoryService.save(request);
    }

    @GetMapping("{id}")
    public CategoryResponseDTO findById(@PathVariable Integer id) {
        return categoryService.findByIdResponse(id);
    }


    @GetMapping("description/{description}")
    public List<CategoryResponseDTO> findByDescription(@PathVariable String description) {
        return categoryService.findByDescription(description);
    }

    @GetMapping
    public List<CategoryResponseDTO> findAll() {
        return categoryService.findAll();
    }

    @PutMapping("{id}")
    public CategoryResponseDTO update(@RequestBody CategoryRequestDTO request, @PathVariable Integer id) {
        return categoryService.update(request, id);
    }

    @DeleteMapping("{id}")
    public SuccessResponse delete(@PathVariable Integer id) {
        return categoryService.delete(id);
    }

}
