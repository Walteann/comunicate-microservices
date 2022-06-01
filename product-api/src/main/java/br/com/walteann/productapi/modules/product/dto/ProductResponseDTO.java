package br.com.walteann.productapi.modules.product.dto;

import br.com.walteann.productapi.modules.category.dto.CategoryResponseDTO;
import br.com.walteann.productapi.modules.product.model.Product;
import br.com.walteann.productapi.modules.supplier.dto.SupplierResponseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {

    private Integer id;
    private String name;
    private Integer quantityAvaliable;

    @JsonProperty("created_at")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    private SupplierResponseDTO supplier;
    private CategoryResponseDTO category;

    public static ProductResponseDTO of(Product product) {
      return ProductResponseDTO.builder()
              .id(product.getId())
              .name(product.getName())
              .quantityAvaliable(product.getQuantityAvaliable())
              .createdAt(product.getCreatedAt())
              .supplier(SupplierResponseDTO.of(product.getSupplier()))
              .category(CategoryResponseDTO.of(product.getCategory()))
              .build();
    }

}
