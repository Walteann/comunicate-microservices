package br.com.walteann.productapi.modules.category.repository;

import br.com.walteann.productapi.modules.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByDescriptionIgnoreCaseContaining(String description);
}
