package br.com.reserva.equipment.service;

import br.com.reserva.equipment.domain.Equipment;
import br.com.reserva.equipment.repository.EquipmentRepository;
import br.com.reserva.category.domain.Category;
import br.com.reserva.category.repository.CategoryRepository;
import br.com.reserva.user.domain.User;
import br.com.reserva.user.repository.UserRepository;
import br.com.reserva.reserve.domain.Reserve;
import br.com.reserva.reserve.service.ReserveService;
import br.com.reserva.reserve.repository.ReserveRepository;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService {

    private final 

    @Autowired
    EquipmentRepository equipmentRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReserveService reserveService;

    @Autowired
    ReserveRepository reserveRepository;

    @Autowired 
    JavaMailSender mailSender;

    @Autowired
    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public Iterable<Equipment> findAll(){
        Iterable<Equipment> equipments = equipmentRepository.findAll();
        return equipments;
    }


    public ResponseEntity<?> save(Equipment equipment){
        equipment.setStatus("ok");
        Equipment saved = equipmentRepository.save(equipment);
        return ResponseEntity.ok(saved);
    }

    public ResponseEntity<?> update(Equipment equipment, Long id){
        return equipmentRepository.findById(id)
        .map(
            record -> {
                record.setName(equipment.getName());
                record.setStatus(equipment.getStatus());
                record.setModel(equipment.getModel());
                Equipment updated = equipmentRepository.save(record);
                return ResponseEntity.ok(updated);
            }
        ).orElse(ResponseEntity.status(404).body(equipment));
    }


    public ResponseEntity<?> delete(Long id){
        equipmentRepository.deleteById(id);
        return ResponseEntity.ok("Excluído com Sucesso!");
    }

    public ResponseEntity<?> assignCategory(Long equipmentId, Long categoryId){
        Equipment equipment = equipmentRepository.findById(equipmentId).get();
        Category category = categoryRepository.findById(categoryId).get();

        equipment.setCategory(category);
        Equipment saved = equipmentRepository.save(equipment);

        return ResponseEntity.ok(saved);
    }

    public ResponseEntity<?> CreateReserve(Long equipmentId, Long userId, Reserve dateTime){
        ResponseEntity<?> validate = reserveService.validateDate(dateTime);
        if(validate.getStatusCode() != HttpStatus.OK) {
            return validate;
        }
        
        Equipment equipment = equipmentRepository.findById(equipmentId).get();
        User user = userRepository.findById(userId).get();

        Reserve saved = reserveService.CreateReserve(user, dateTime, null, equipment, null);

        if(saved == null){
            return ResponseEntity.status(400).body("O Equipamento não tem status Ok!");
        }

        equipment.setReserve(saved);

        equipmentRepository.save(equipment);

        return ResponseEntity.ok(saved);
    }

    public ResponseEntity<?> ChangeStatusEquipment(Long equipmentId, Equipment requestBody){
        Equipment equipment = equipmentRepository.findById(equipmentId).get();
        equipment.setStatus(requestBody.getStatus());

        if(requestBody.getStatus().equals("danificado") || requestBody.getStatus().equals("manutenção")){
            if(equipment.getReserve() != null){
                Reserve reserve = equipment.getReserve();
                reserve.setExpired(true);
                reserveRepository.save(reserve);
                sendEmail();
            }
        }

        Equipment saved = equipmentRepository.save(equipment);

        return ResponseEntity.ok(saved);
    }

    public void sendEmail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Olá, o equipamento da sua reserva não está com status ok!!!");
        message.setTo("matheus.encode@gmail.com");
        message.setFrom("matheus.unialfa@gmail.com");
        mailSender.send(message);
    }
}
