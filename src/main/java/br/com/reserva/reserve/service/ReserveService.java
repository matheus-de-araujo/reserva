package br.com.reserva.reserve.service;

import br.com.reserva.equipment.domain.Equipment;
import br.com.reserva.material.domain.Material;
import br.com.reserva.reserve.domain.Reserve;
import br.com.reserva.user.domain.User;
import br.com.reserva.room.domain.Room;
import br.com.reserva.reserve.repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReserveService {

    private final ReserveRepository reserveRepository;

    @Autowired
    public ReserveService(ReserveRepository reserveRepository) {
        this.reserveRepository = reserveRepository;
    }

    public Iterable<Reserve> findAll(){
        Iterable<Reserve> reserves = reserveRepository.findAll();
        return reserves;
    }

    public ResponseEntity<?> save(Reserve reserve){
        Reserve saved = reserveRepository.save(reserve);
        return ResponseEntity.ok(saved);
    }

    public ResponseEntity<?> update(Reserve reserve, Long id){
        return reserveRepository.findById(id)
        .map(
            record -> {
                record.setBooking_date(reserve.getBooking_date());
                Reserve updated = reserveRepository.save(record);
                return ResponseEntity.ok(updated);
            }
        ).orElse(ResponseEntity.status(404).body(reserve));
    }

    public ResponseEntity<?> delete(Long id){
        reserveRepository.deleteById(id);
        return ResponseEntity.ok("Excluído com Sucesso!");
    }

    public Reserve CreateReserve(User user, Reserve dateTime, Room room, Equipment equipment, Material material){
        Reserve reserve = new Reserve();
        reserve.setBooking_date(dateTime.getBooking_date());
        reserve.setBooking_hour(dateTime.getBooking_hour());
        reserve.setUser(user);

        if(room != null) {
            reserve.setRoom(room);
        }

        if(equipment != null) {
            reserve.setEquipment(equipment);
        }

        if(material != null) {
            reserve.setMaterial(material);
        }

        return reserveRepository.save(reserve);
    }

}