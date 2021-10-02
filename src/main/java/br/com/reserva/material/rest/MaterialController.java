package br.com.reserva.material.rest;

import br.com.reserva.material.domain.Material;
import br.com.reserva.material.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
@RequestMapping(path = "materials")
public class MaterialController {

    @Autowired
    MaterialService service;

    @Autowired
    public MaterialController(MaterialService materialService) {
        this.service = materialService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public @ResponseBody ResponseEntity<?> findAllMaterial(){
        try {
            Iterable<Material> materials = service.findAll();
            return ResponseEntity.ok(materials);
        } catch (Exception e) {
            return (ResponseEntity<?>) ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping(path = "/add")
    public @ResponseBody ResponseEntity<?> saveMaterial(@RequestBody Material material){
        try {
            return service.save(material);
        } catch (Exception e) {
            return (ResponseEntity<?>) ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping(path = "/edit/{id}")
    public @ResponseBody ResponseEntity<?> update(@RequestBody Material material, @PathVariable(name = "id") Long id){
        try {
            return service.update(material, id);
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

    @PutMapping(path = "/{materialId}/category/{categorysId}")
    public @ResponseBody ResponseEntity<?> AssignCategoryToMaterial(
            @PathVariable(name = "MaterialId") Long materialId,
            @PathVariable(name = "categoriesId") Long categorysId)
        {
        try {
            return service.assignCategory(materialId, categorysId);
        } catch (Exception e) {
            return (ResponseEntity<?>) ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
