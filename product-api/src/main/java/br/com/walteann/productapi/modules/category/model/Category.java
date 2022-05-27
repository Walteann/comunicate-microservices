package br.com.walteann.productapi.modules.category.model;

import br.com.walteann.productapi.modules.category.dto.CategoryRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    public static Category of(CategoryRequestDTO request) {
        var category = new Category();
        BeanUtils.copyProperties(request, category);
        return category;
    }
}
