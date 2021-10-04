package br.com.reserva.room.service;

import br.com.reserva.room.domain.Room;
import br.com.reserva.room.repository.RoomRepository;
import br.com.reserva.category.domain.Category;
import br.com.reserva.category.repository.CategoryRepository;
import br.com.reserva.user.domain.User;
import br.com.reserva.user.repository.UserRepository;
import br.com.reserva.reserve.domain.Reserve;
import br.com.reserva.reserve.repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReserveRepository ReserveRepository;

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

    public ResponseEntity<?> assignCategory(Long roomId, Long categoryId){
        Room room = roomRepository.findById(roomId).get();
        Category category = categoryRepository.findById(categoryId).get();

        room.setCategory(category);
        Room saved = roomRepository.save(room);

        return ResponseEntity.ok(saved);
    }

    public ResponseEntity<?> CreateReserve(Long roomId, Long userId){
        Room room = roomRepository.findById(roomId).get();
        User user = userRepository.findById(userId).get();
        Reserve reserve = new Reserve();

        reserve.setUser(user);
        reserve.setRoom(room);
        Reserve saved = ReserveRepository.save(reserve);

        room.setReserve(saved);

        roomRepository.save(room);

        return ResponseEntity.ok(saved);
    }
}
