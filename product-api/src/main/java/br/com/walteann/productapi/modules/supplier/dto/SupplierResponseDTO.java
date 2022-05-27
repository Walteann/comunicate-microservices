package br.com.walteann.productapi.modules.supplier.dto;

import br.com.walteann.productapi.modules.supplier.model.Supplier;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class SupplierResponseDTO {

    private Integer id;
    private String name;

    public static SupplierResponseDTO of(Supplier supplier) {
        var response = new SupplierResponseDTO();
        BeanUtils.copyProperties(supplier, response);
        return response;
    }
}
