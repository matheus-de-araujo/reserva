package br.com.reserva.user.repository;

import br.com.reserva.user.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface UserRepository extends CrudRepository<User, Long> {

}