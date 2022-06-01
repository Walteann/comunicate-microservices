package br.com.walteann.productapi.modules.product.dto;

import lombok.Data;

@Data
public class ProductRequestDTO {

    private String name;
    private Integer quantityAvaliable;
    private Integer supplierId;
    private Integer categoryId;

}
