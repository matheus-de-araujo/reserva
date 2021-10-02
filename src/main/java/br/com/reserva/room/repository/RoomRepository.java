package br.com.reserva.room.repository;

import br.com.reserva.room.domain.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "rooms")
public interface RoomRepository extends CrudRepository<Room, Long> {

}