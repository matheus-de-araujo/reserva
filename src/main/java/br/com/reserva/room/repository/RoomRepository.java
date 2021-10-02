package br.com.reserva.room.repository;

import br.com.reserva.room.domain.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Long> {

}