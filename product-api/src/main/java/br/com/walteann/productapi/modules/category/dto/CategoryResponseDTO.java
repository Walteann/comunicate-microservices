package br.com.walteann.productapi.modules.category.dto;

import br.com.walteann.productapi.modules.category.model.Category;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class CategoryResponseDTO {

    private Integer id;
    private String description;

    public static CategoryResponseDTO of(Category category) {
        var response = new CategoryResponseDTO();
        BeanUtils.copyProperties(category, response);
        return response;
    }

}
