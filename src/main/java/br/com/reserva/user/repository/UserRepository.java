package br.com.reserva.user.repository;

import br.com.reserva.user.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}