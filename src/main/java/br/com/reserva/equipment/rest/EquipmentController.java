package br.com.reserva.equipment.rest;

import br.com.reserva.equipment.domain.Equipment;
import br.com.reserva.equipment.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
@RequestMapping(path = "equipments")
public class EquipmentController {

    private final EquipmentService service;

    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.service = equipmentService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public @ResponseBody ResponseEntity<?> findAllEquipment(){
        try {
            Iterable<Equipment> equipments = service.findAll();
            return ResponseEntity.ok(equipments);
        } catch (Exception e) {
            return (ResponseEntity<?>) ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping(path = "/add")
    public @ResponseBody ResponseEntity<?> saveEquipment(@RequestBody Equipment equipment){
        try {
            return service.save(equipment);
        } catch (Exception e) {
            return (ResponseEntity<?>) ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping(path = "/edit/{id}")
    public @ResponseBody ResponseEntity<?> update(@RequestBody Equipment equipment, @PathVariable(name = "id") Long id){
        try {
            return service.update(equipment, id);
        } catch (Exception e) {
            return (ResponseEntity<?>) ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
        try {
            return service.delete(id);
        } catch (Exception e) {
            return (ResponseEntity<?>) ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping(path = "/{equipmentId}/categories/{categoryId}")
    public @ResponseBody ResponseEntity<?> AssignCategoryToEquipment(
            @PathVariable Long equipmentId, @PathVariable Long categoryId)
        {
        try {
            return service.assignCategory(equipmentId, categoryId);
        } catch (Exception e) {
            return (ResponseEntity<?>) ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
