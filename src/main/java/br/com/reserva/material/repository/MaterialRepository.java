package br.com.reserva.material.repository;

import br.com.reserva.material.domain.Material;
import org.springframework.data.repository.CrudRepository;

public interface MaterialRepository extends CrudRepository<Material, Long> {

}