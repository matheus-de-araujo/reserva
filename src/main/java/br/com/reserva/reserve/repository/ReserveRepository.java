package br.com.reserva.reserve.repository;

import br.com.reserva.reserve.domain.Reserve;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "reserve")
public interface ReserveRepository extends CrudRepository<Reserve, Long> {

}