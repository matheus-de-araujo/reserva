package br.com.reserva.material.service;

import br.com.reserva.material.domain.Material;
import br.com.reserva.material.repository.MaterialRepository;
import br.com.reserva.category.domain.Category;
import br.com.reserva.category.repository.CategoryRepository;
import br.com.reserva.user.domain.User;
import br.com.reserva.user.repository.UserRepository;
import br.com.reserva.reserve.domain.Reserve;
import br.com.reserva.reserve.service.ReserveService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MaterialService {

    @Autowired
    MaterialRepository materialRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReserveService reserveService;

    @Autowired
    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public Iterable<Material> findAll(){
        Iterable<Material> materials = materialRepository.findAll();
        return materials;
    }


    public ResponseEntity<?> save(Material material){
        Material saved = materialRepository.save(material);
        return ResponseEntity.ok(saved);
    }

    public ResponseEntity<?> update(Material material, Long id){
        return materialRepository.findById(id)
        .map(
            record -> {
                record.setName(material.getName());
                record.setAvailable_qty(material.getAvailable_qty());
                record.setReserved_qty(material.getReserved_qty());
                record.setTotal_qty(material.getTotal_qty());
                Material updated = materialRepository.save(record);
                return ResponseEntity.ok(updated);
            }
        ).orElse(ResponseEntity.status(404).body(material));
    }


    public ResponseEntity<?> delete(Long id){
        materialRepository.deleteById(id);
        return ResponseEntity.ok("Excluído com Sucesso!");
    }

    public ResponseEntity<?> assignCategory(Long materialId, Long categoryId){
        Material material = materialRepository.findById(materialId).get();
        Category category = categoryRepository.findById(categoryId).get();

        material.setCategory(category);
        Material saved = materialRepository.save(material);

        return ResponseEntity.ok(saved);
    }

    public ResponseEntity<?> CreateReserve(Long materialId, Long userId, Reserve dateTime){
        ResponseEntity<?> validate = reserveService.validateDate(dateTime);
        if(validate.getStatusCode() != HttpStatus.OK) {
            return validate;
        }
        
        Material material = materialRepository.findById(materialId).get();
        User user = userRepository.findById(userId).get();

        Reserve saved = reserveService.CreateReserve(user, dateTime, null, null, material);

        material.setReserve(saved);

        materialRepository.save(material);

        return ResponseEntity.ok(saved);
    }
}
