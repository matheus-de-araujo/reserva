package br.com.reserva.equipment.repository;

import br.com.reserva.equipment.domain.Equipment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "equipment")
public interface EquipmentRepository extends CrudRepository<Equipment, Long> {

}
