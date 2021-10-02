package br.com.reserva.equipment.service;

import br.com.reserva.equipment.domain.Equipment;
import br.com.reserva.equipment.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public Iterable<Equipment> findAll(){
        Iterable<Equipment> equipments = equipmentRepository.findAll();
        return equipments;
    }


    public ResponseEntity<?> save(Equipment equipment){
        Equipment saved = equipmentRepository.save(equipment);
        return ResponseEntity.ok(saved);
    }

    public ResponseEntity<?> update(Equipment equipment, Long id){
        return equipmentRepository.findById(id)
        .map(
            record -> {
                record.setName(equipment.getName());
                record.setName(equipment.getName());
                record.setStatus(equipment.getStatus());
                record.setModel(equipment.getModel());
                Equipment updated = equipmentRepository.save(record);
                return ResponseEntity.ok(updated);
            }
        ).orElse(ResponseEntity.status(404).body(equipment));
    }


    public ResponseEntity<?> delete(Long id){
        equipmentRepository.deleteById(id);
        return ResponseEntity.ok("Excluído com Sucesso!");
    }
}
