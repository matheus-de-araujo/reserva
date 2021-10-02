package br.com.reserva.room.service;

import br.com.reserva.room.domain.Room;
import br.com.reserva.room.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Iterable<Room> findAll(){
        Iterable<Room> rooms = roomRepository.findAll();
        return rooms;
    }


    public ResponseEntity<?> save(Room room){
        Room saved = roomRepository.save(room);
        return ResponseEntity.ok(saved);
    }

    public ResponseEntity<?> update(Room room, Long id){
        return roomRepository.findById(id)
        .map(
            record -> {
                record.setRoom_number(room.getRoom_number());
                record.setFloor(room.getFloor());
                record.setType(room.getType());
                Room updated = roomRepository.save(record);
                return ResponseEntity.ok(updated);
            }
        ).orElse(ResponseEntity.status(404).body(room));
    }


    public ResponseEntity<?> delete(Long id){
        roomRepository.deleteById(id);
        return ResponseEntity.ok("Excluído com Sucesso!");
    }
}