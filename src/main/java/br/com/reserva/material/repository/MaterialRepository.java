package br.com.reserva.material.repository;

import br.com.reserva.material.domain.Material;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "materials")
public interface MaterialRepository extends CrudRepository<Material, Long> {

}