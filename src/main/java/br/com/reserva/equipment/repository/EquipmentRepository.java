package br.com.reserva.equipment.repository;

import br.com.reserva.equipment.domain.Equipment;
import org.springframework.data.repository.CrudRepository;

public interface EquipmentRepository extends CrudRepository<Equipment, Long> {

}
