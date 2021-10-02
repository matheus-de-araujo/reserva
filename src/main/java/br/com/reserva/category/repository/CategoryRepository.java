package br.com.reserva.category.repository;

import br.com.reserva.category.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    
}
