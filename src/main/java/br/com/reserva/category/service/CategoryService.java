package br.com.reserva.category.service;

import br.com.reserva.category.domain.Category;
import br.com.reserva.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Iterable<Category> findAll(){
        Iterable<Category> categorys = categoryRepository.findAll();
        return categorys;
    }


    public ResponseEntity<?> save(Category category){
        Category saved = categoryRepository.save(category);
        return ResponseEntity.ok(saved);
    }

    public ResponseEntity<?> update(Category category, Long id){
        return categoryRepository.findById(id)
        .map(
            record -> {
                record.setName(category.getName());
                record.setName(category.getName());
                Category updated = categoryRepository.save(record);
                return ResponseEntity.ok(updated);
            }
        ).orElse(ResponseEntity.status(404).body(category));
    }


    public ResponseEntity<?> delete(Long id){
        categoryRepository.deleteById(id);
        return ResponseEntity.ok("Excluído com Sucesso!");
    }
}
