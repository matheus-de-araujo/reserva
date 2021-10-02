package br.com.reserva.material.service;

import br.com.reserva.material.domain.Material;
import br.com.reserva.material.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;

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
}
