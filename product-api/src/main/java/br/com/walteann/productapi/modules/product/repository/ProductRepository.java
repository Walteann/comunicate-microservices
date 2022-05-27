package br.com.walteann.productapi.modules.product.repository;

import br.com.walteann.productapi.modules.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
