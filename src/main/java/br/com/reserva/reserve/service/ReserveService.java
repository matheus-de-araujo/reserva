package br.com.reserva.reserve.service;

import br.com.reserva.equipment.domain.Equipment;
import br.com.reserva.material.domain.Material;
import br.com.reserva.material.service.MaterialService;
import br.com.reserva.reserve.domain.Reserve;
import br.com.reserva.user.domain.User;
import br.com.reserva.room.domain.Room;
import br.com.reserva.reserve.repository.ReserveRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReserveService {

    @Autowired
    ReserveRepository reserveRepository;

    @Autowired
    MaterialService materialService;

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

    public ResponseEntity<?> updateDate(Reserve reserve, Long id){
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
        Reserve reserve = reserveRepository.findById(id).get();
        if(reserve.getMaterial() != null){

        }
        reserveRepository.deleteById(id);
        return ResponseEntity.ok("Excluído com Sucesso!");
    }

    public Reserve CreateReserve(User user, Reserve requestBody, Room room, Equipment equipment, Material material){
        Reserve reserve = new Reserve();
        reserve.setBooking_date(requestBody.getBooking_date());
        reserve.setBooking_hour(requestBody.getBooking_hour());
        reserve.setUser(user);

        if(room != null) {
            reserve.setRoom(room);
        }

        if(equipment != null) {
            if(equipment.getStatus().equals("ok")){
                reserve.setEquipment(equipment);
            }else{
                return null;
            }
        }

        if(material != null) {
            if(requestBody.getQty_material() <= material.getAvailable_qty()){
                reserve.setMaterial(material);
                materialService.setReservedQty(requestBody.getQty_material(), material);
                reserve.setQty_material(requestBody.getQty_material());
            }else{
                return null;
            }
        }

        return reserveRepository.save(reserve);
    }

    public ResponseEntity<?> validateDate(Reserve dateTime){
        LocalDateTime minimum = LocalDateTime.now().plusDays(1);
        LocalDateTime maximum = LocalDateTime.now().plusDays(31);
        LocalDateTime reserve = LocalDateTime(dateTime);

        if(!reserve.isAfter(minimum)){
            return ResponseEntity.status(400).body("A data da reserva precisa ser feita com pelo menos 1 dia de antecedência!");
        }if(!reserve.isBefore(maximum)) {
            return ResponseEntity.status(400).body("A data da reserva não pode ultrapassar 30 dias de antecedência!");
        }else{
            return ResponseEntity.ok("Tudo certo com a data!");
        }
    }

    public LocalDateTime LocalDateTime(Reserve reserve){
        LocalDateTime date = LocalDateTime.of(
        reserve.getBooking_date().getYear(), 
        reserve.getBooking_date().getMonth(), 
        reserve.getBooking_date().getDayOfMonth(),
        reserve.getBooking_hour().getHour(),
        reserve.getBooking_hour().getMinute());

        return date;
    }

    public void scheduleTime(Reserve reserve){

        if(!reserve.isRetired()){
            LocalDateTime dateReserve = LocalDateTime(reserve);
            LocalDateTime deadline = LocalDateTime.now().plusMinutes(10);

            if(dateReserve.isBefore(deadline)){
                reserve.setExpired(true);
                if(reserve.getMaterial() != null){
                    materialService.setExpiredQty(reserve.getQty_material(), reserve.getMaterial());
                }
                reserveRepository.save(reserve);
            }
        }
    }
}