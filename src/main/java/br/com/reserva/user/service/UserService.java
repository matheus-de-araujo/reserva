package br.com.reserva.user.service;

import br.com.reserva.user.domain.User;
import br.com.reserva.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> findAll(){
        Iterable<User> users = userRepository.findAll();
        return users;
    }


    public ResponseEntity<?> save(User user){
        User saved = userRepository.save(user);
        return ResponseEntity.ok(saved);
    }

    public ResponseEntity<?> update(User user, Long id){
        return userRepository.findById(id)
        .map(
            record -> {
                record.setName(user.getName());
                record.setEmail(user.getEmail());
                User updated = userRepository.save(record);
                return ResponseEntity.ok(updated);
            }
        ).orElse(ResponseEntity.status(404).body(user));
    }


    public ResponseEntity<?> delete(Long id){
        userRepository.deleteById(id);
        return ResponseEntity.ok("Excluído com Sucesso!");
    }

}
